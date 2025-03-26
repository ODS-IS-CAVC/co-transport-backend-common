package com.next.logistic.cache;

import com.next.logistic.authorization.User;
import com.next.logistic.config.AppCacheUserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AppCacheUserProperties.class})
public class AppCacheUser {

    private final AppCacheUserProperties appCacheUserProperties;

    public AppCacheUser(AppCacheUserProperties appCacheUserProperties) {
        this.appCacheUserProperties = appCacheUserProperties;
    }

    @Bean(name = "userCacheStore")
    public AppCachingConfig<User> userCacheStore() {
        return new AppCachingConfig<>(appCacheUserProperties.getExpiryUserDuration(),
                appCacheUserProperties.getTimeUnit());
    }

    @Bean(name = "m2mAppCacheStore")
    public AppCachingConfig<String> m2mAppCacheStore() {
        return new AppCachingConfig<>(appCacheUserProperties.getExpiryM2mDuration(),
                appCacheUserProperties.getTimeUnit());
    }
}
