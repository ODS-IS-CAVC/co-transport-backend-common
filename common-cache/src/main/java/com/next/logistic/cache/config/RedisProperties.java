package com.next.logistic.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "next.redis")
public class RedisProperties {

    private String host; //Endpoint by AWS
    private int port;
    private String password; //Default not use password or token of AWS
    private boolean ssl; //Default not use SSL
    private List<String> cacheNames;

    private int timeToLive;
}
