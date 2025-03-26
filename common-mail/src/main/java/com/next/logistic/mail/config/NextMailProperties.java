package com.next.logistic.mail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "next.mail")
public class NextMailProperties {

    private String host;
    private int port;
    private String password;
    private String username;
    private String from;

    private String protocol;
    private String auth;
    private String startTLS;
}
