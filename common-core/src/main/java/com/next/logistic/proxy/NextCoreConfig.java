package com.next.logistic.proxy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"com.next.logistic"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.next.logistic.aws.*")})
public class NextCoreConfig {
}
