package com.next.logistic.proxy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.next.logistic.exception"})
@EnableNextCore
public class AdapterExceptionConfig {
}
