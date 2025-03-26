package com.next.logistic.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "next.auth")
@Component
public class NljAuthProperties {

    private String apiKey;
    private String loginUser;
    private String loginSystem;
    private String verifyToken;
    private String refreshToken;
}
