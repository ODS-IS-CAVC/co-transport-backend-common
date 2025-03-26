package com.next.logistic.config;

import java.util.concurrent.TimeUnit;

import com.next.logistic.cache.AppCacheUser;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConditionalOnClass(AppCacheUser.class)
@ConfigurationProperties(prefix = "next.app.cache.user")
public class AppCacheUserProperties {

    private int expiryUserDuration = 180;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    private int expiryM2mDuration = 1800;

}
