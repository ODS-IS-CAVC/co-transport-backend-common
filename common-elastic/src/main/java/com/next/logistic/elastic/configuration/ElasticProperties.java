package com.next.logistic.elastic.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "elastic")
public class ElasticProperties {

    private String host;
    private String username;
    private String password;
    private int connectionTimeout = 1000;
    private int socketTimeout = 30000;
}
