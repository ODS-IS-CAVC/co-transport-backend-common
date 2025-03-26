package com.next.logistic.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "next.auth-white")
@Component
public class AuthWhiteProperties {

    private String[] apis;
}
