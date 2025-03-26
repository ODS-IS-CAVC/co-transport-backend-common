package com.next.logistic.security.config;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomJwtDecoder implements JwtDecoder {

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            // Remove 'Bearer ' prefix if present
            String accessToken = token.startsWith("Bearer ") ? token.substring(7) : token;

            // Parse the JWT
            JWT jwt = JWTParser.parse(accessToken);
            if (jwt instanceof SignedJWT) {
                SignedJWT signedJWT = (SignedJWT) jwt;

                // Validate expiration time
//                Instant expirationTime = getClaim(signedJWT, "exp", Date.class).toInstant();
//                if (expirationTime.isBefore(Instant.now())) {
//                    throw new JwtException("Token expired");
//                }

                // Create Jwt object
                Map<String, Object> headers = signedJWT.getHeader().toJSONObject();
                Map<String, Object> claims = signedJWT.getJWTClaimsSet().getClaims();
                Map<String, Object> mutableClaims = new HashMap<>(claims);
                if (claims.get("exp") != null){

                    Instant expirationTime = getClaim(signedJWT, "exp", Date.class).toInstant();
                    mutableClaims.put("exp", expirationTime);
                }

                if (claims.get("iat") != null){
                    Instant iaTime = getClaim(signedJWT, "iat", Date.class).toInstant();
                    mutableClaims.put("iat", iaTime);
                }


                return Jwt.withTokenValue(accessToken)
                        .headers(h -> h.putAll(headers))
                        .claims(c -> c.putAll(mutableClaims))
                        .build();
            } else {
                throw new JwtException("Unsupported JWT type");
            }
        } catch (Exception e) {
            throw new JwtException("Failed to decode JWT", e);
        }
    }

    private <T> T getClaim(SignedJWT signedJWT, String claimName, Class<T> claimType) {
        try {
            Object claimValue = signedJWT.getJWTClaimsSet().getClaim(claimName);
            if (claimType.isInstance(claimValue)) {
                return claimType.cast(claimValue);
            }
            throw new IllegalArgumentException("Invalid claim type");
        } catch (Exception e) {
            throw new JwtException("Failed to retrieve claim: " + claimName, e);
        }
    }
}