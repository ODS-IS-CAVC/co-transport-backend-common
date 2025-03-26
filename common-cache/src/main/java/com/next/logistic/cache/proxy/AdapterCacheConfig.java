package com.next.logistic.cache.proxy;

import com.next.logistic.proxy.EnableNextCore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.next.logistic.cache")
@EnableNextCore
public class AdapterCacheConfig {
}
