package com.next.logistic.elastic.proxy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.next.logistic.elastic"})
public class AdapterElasticConfig {
}
