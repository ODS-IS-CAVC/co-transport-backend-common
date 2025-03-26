package com.next.logistic.proxy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"com.next.logistic"})

@EnableNextCore
public class AdapterWebConfig {

}
