package com.next.logistic.security.config;

import static com.next.logistic.model.SoaResponse.PREFIX_MESSAGE_CODE;
import static com.next.logistic.security.constant.AuthConstant.*;
import static com.next.logistic.util.Constants.KEY_CLAIM_GROUP;

import com.next.logistic.authorization.User;
import com.next.logistic.authorization.UserContext;
import com.next.logistic.config.NljUrlProperties;
import com.next.logistic.exception.NextInvalidUserException;
import com.next.logistic.exception.NextWebException;
import com.next.logistic.exception.model.NextAPIError;
import com.next.logistic.model.ResponseError;
import com.next.logistic.model.SoaResponsePool;
import com.next.logistic.security.constant.AuthConstant;
import com.next.logistic.security.dto.VerifyTokenResponseDTO;
import com.next.logistic.security.filter.BaseAuthenticationFilter;
import com.next.logistic.security.whitelist.AuthWhitelistProvider;
import com.next.logistic.util.BaseUtil;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
@Component
public class NextAuthenticationFilter extends BaseAuthenticationFilter {

    @Resource(name = "userContext")
    private UserContext userContext;

    private final String OVERRIDE_OPERATOR_ID = "operator-id";
    private final String OVERRIDE_OPERATOR_CODE = "operator-code";
    private final String DEBUG = "debug";

    @Autowired
    private AuthWhitelistProvider authWhitelistProvider;
    @Autowired
    private NljAuthProperties authProperties;
    @Autowired
    private NljUrlProperties nljUrlProperties;
    @Autowired
    private MessageSource messageSource;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, RestClientException {
        String requestUri = request.getRequestURI();
        if (Arrays.asList(authWhitelistProvider.authWhitelist()).contains(requestUri)) {
            log.debug("Skipping authentication for whitelisted URL: {}", requestUri);
            filterChain.doFilter(request, response);
            return;
        }
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        userContext.setToken(token);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && token != null) {
            try {
                setUser(authentication, token, request.getHeader(OVERRIDE_OPERATOR_ID),
                        request.getHeader(OVERRIDE_OPERATOR_CODE),
                        request.getHeader(DEBUG), request.getHeader(ROLE));
            } catch (NextWebException e) {
                log.debug("Authorization token: {}", token);
                this.generateErrorResponse(response, e.getApiError());
                return;
            }
        }
        filterChain.doFilter(wrappedRequest, response);

    }

    private void setUser(Authentication authentication, String token, String overrideOperatorID,
                         String overrideCompnayCode, String debug, String role)
            throws IOException, NextInvalidUserException, NextWebException {
        if (token != null && token.startsWith("Bearer ")) {
            String url = nljUrlProperties.getVerifyToken() + API_SECURITIES;
            RestTemplate restTemplate = new RestTemplate();
            String accessToken = token.substring(7);  // Remove 'Bearer ' prefix

            try {
                // Parse the JWT
                JWT jwt = JWTParser.parse(accessToken);
                if (jwt instanceof SignedJWT) {
                    SignedJWT signedJWT = (SignedJWT) jwt;
                    //Verify token
                    HttpHeaders headers = setHeader(token);
                    String requestBody = API_PARAM + "=" + accessToken;
                    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
                    ResponseEntity<VerifyTokenResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST,requestEntity,VerifyTokenResponseDTO.class);
                    if (response.getBody() == null || !response.getBody().getActive()) {
                        throw new NextWebException(new NextAPIError(HttpStatus.FORBIDDEN, SoaResponsePool.get("403_expired")));
                    }
                    // Check expiration time (exp)
//                    Instant expirationTime = getClaim(signedJWT, "exp", Date.class).toInstant();
//                    if (expirationTime.isBefore(Instant.now())) {
//                        throw new NextWebException(new NextAPIError(HttpStatus.UNAUTHORIZED, SoaResponsePool.get("401")));
//                    }
                    // Extract claims from JWT
                    String sub = getClaim(signedJWT, AuthConstant.USER_ID, String.class);
                    String companyId = getClaim(signedJWT, AuthConstant.COMPANY_ID, String.class);
                    String username = signedJWT.getJWTClaimsSet().getStringClaim(AuthConstant.USERNAME);
                    if(companyId == null) {
                        companyId = getClaim(signedJWT, AuthConstant.COMPANY_SYSTEM_ID, String.class);
                    }

                    // Create and set User object
                    User user = new User();
                    user.setId(sub);
                    user.setUsername(username);
                    user.setPreferredUserName(username);
                    user.setCompanyId(overrideOperatorID != null ? overrideOperatorID : companyId);
                    user.setCompanyCode(overrideCompnayCode);
                    user.setDebug("true".equals(debug));
                    user.setShipper("shipper".equals(role));

                    // Update user context
                    this.userContext.setUser(user);
                } else {
                    throw new NextWebException(new NextAPIError(HttpStatus.INTERNAL_SERVER_ERROR, SoaResponsePool.get("500")));
                }
            } catch (ParseException e) {
                // Invalid JWT parsing error
                throw new NextWebException(new NextAPIError(HttpStatus.BAD_REQUEST, e, SoaResponsePool.get("token_error")));
            }
        } else {
            throw new NextWebException(new NextAPIError(HttpStatus.BAD_REQUEST, SoaResponsePool.get("token_missing")));
        }
    }
    private HttpHeaders setHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY, authProperties.getApiKey());
        headers.set(AUTHORIZATION, token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    /**
     * Set Organization for Customer User
     *
     * @param jwt
     * @param user
     * @return
     */
    private void setPartner(final Jwt jwt, final User user) {
        try {
            Map<String, Object> otherClaims = jwt.getClaims();
            if (!ObjectUtils.isEmpty(otherClaims)) {
                if (otherClaims.containsKey(KEY_CLAIM_GROUP)) {
                    List<String> groups = (List<String>) otherClaims.get(KEY_CLAIM_GROUP);
                    user.setGroups(groups);
                }
            }
        } catch (Exception ex) {
            log.info("AuthenticationFilterConfig.setGroup: {}", ex.getMessage());
        }
    }

    private void generateErrorResponse(HttpServletResponse response, NextAPIError apiError)
            throws IOException {
        response.setContentType("application/json");
        response.setStatus(apiError.getStatus().value());
        ResponseError responseError = new ResponseError();
        responseError.setStatus(apiError.getStatus().value());
//        responseError.setMessage(apiError.getStatus().getReasonPhrase());
        responseError.setMessage(messageSource.getMessage(PREFIX_MESSAGE_CODE + apiError.getSoaResponse().getSoaErrorCode(),new Object[]{},
                LocaleContextHolder.getLocale()));
        responseError.setCode(apiError.getSoaResponse().getSoaErrorCode());
        responseError.setDateTime(BaseUtil.dateToString(Instant.now()));
        response.getWriter().write(responseError.convertToJson());
    }

    /**
     * Get a claim from the JWT by its name and convert it to the specified type.
     * This method uses generics to allow conversion to any type.
     *
     * @param <T> the type to which the claim should be converted.
     * @param signedJWT the signed JWT object.
     * @param claimName the name of the claim to retrieve.
     * @param claimType the class type of the claim value.
     * @return the value of the claim, converted to the specified type, or null if not found.
     * @throws NextWebException if an error occurs while retrieving or converting the claim.
     */
    private <T> T getClaim(SignedJWT signedJWT, String claimName, Class<T> claimType) throws NextWebException {
        try {
            // Attempt to retrieve the claim value
            Object claimValue = signedJWT.getJWTClaimsSet().getClaim(claimName);

            // If claim value is null, return null (or handle in another way if needed)
            if (claimValue == null) {
                return null;
            }

            // Convert claim value to the desired type
            if (claimType.isInstance(claimValue)) {
                return claimType.cast(claimValue);
            } else {
                throw new NextWebException(new NextAPIError(HttpStatus.BAD_REQUEST, SoaResponsePool.get("claim_error")));
            }
        } catch (Exception e) {
            // Catch any exception that occurs while retrieving or converting the claim
            throw new NextWebException(new NextAPIError(HttpStatus.INTERNAL_SERVER_ERROR, e, SoaResponsePool.get("500")));
        }
    }
}
