package com.next.logistic.cache.retry;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RetryTemplateProvider {

    public Retry getRetryTemplate(String retryName) {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(2))
                .build();
        return Retry.of(retryName, retryConfig);
    }
}
