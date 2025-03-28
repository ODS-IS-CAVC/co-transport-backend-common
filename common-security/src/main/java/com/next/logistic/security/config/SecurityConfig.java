package com.next.logistic.security.config;

import com.next.logistic.security.whitelist.AuthWhitelistProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
public class SecurityConfig {

    private final AuthWhitelistProvider authWhitelistProvider;

    private final SecurityProblemSupport problemSupport;

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    private final AccessDeniedHandler accessDeniedHandler ;

    public SecurityConfig(AuthWhitelistProvider authWhitelistProvider,
                          SecurityProblemSupport problemSupport, CustomAuthenticationEntryPoint authenticationEntryPoint,
                          CustomAccessDeniedHandler accessDeniedHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        log.debug("Initializing SecurityConfiguration...");
        log.debug("AuthWhitelistProvider class: {}", authWhitelistProvider.getClass().getName());
        this.authWhitelistProvider = authWhitelistProvider;
        this.problemSupport = problemSupport;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        log.debug("Whitelist URLs: {}", Arrays.toString(authWhitelistProvider.authWhitelist()));
        log.debug("Using AuthWhitelistProvider: {}", authWhitelistProvider.getClass().getName());

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers(authWhitelistProvider.authWhitelist()).disable())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                //.authenticationEntryPoint(problemSupport)
                                .accessDeniedHandler(accessDeniedHandler)
                                .authenticationEntryPoint(authenticationEntryPoint)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(authWhitelistProvider.authWhitelist()).permitAll() // Dynamic whitelist URL for each module
                                .requestMatchers("/api/authenticate").permitAll()
                                .requestMatchers("/api/auth-info").permitAll()
                                .requestMatchers("/api/**").authenticated()
                                .requestMatchers("/**").authenticated()
                                .requestMatchers("/management/health").permitAll()
                                .requestMatchers("/management/health/**").permitAll()
                                .requestMatchers("/management/info").permitAll()
                                .requestMatchers("/management/prometheus").permitAll()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2
                                .authenticationEntryPoint(problemSupport)
                                .accessDeniedHandler(problemSupport)
                                .jwt(jwt ->
                                        jwt.jwtAuthenticationConverter(authenticationConverter())
                                )
                );
//                .oauth2Client(Customizer.withDefaults());

        return http.build();
        // @formatter:on
    }


    Converter<Jwt, AbstractAuthenticationToken> authenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new JwtGrantedAuthorityConverter());
        return jwtAuthenticationConverter;
    }

//    @Bean
//    JwtDecoder jwtDecoder() {
//        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri);
//
////        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(jHipsterProperties.getSecurity().getOauth2().getAudience());
//        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
//        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(
//                withIssuer/*, audienceValidator*/);
//
//        jwtDecoder.setJwtValidator(withAudience);
//
//        return jwtDecoder;
//    }

    @Bean("jwtDecoder")
    JwtDecoder jwtDecoder(){

        return new CustomJwtDecoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); 
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization", "Link", "X-Total-Count", "X-nlj-auth-alert", "X-nlj-auth-error", "X-nlj-auth-params"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
