package com.next.logistic.cache.config;

import com.next.logistic.util.BaseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import software.amazon.awssdk.services.elasticache.endpoints.internal.Value;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableConfigurationProperties({RedisProperties.class})
@RequiredArgsConstructor
public class CacheConfig {

    private final RedisProperties redisProperties;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)) // Set TTL for cache entries
                .disableCachingNullValues(); // Don't cache null values

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        if (BaseUtil.isEmpty(redisProperties.getCacheNames(), null, 1)){
            redisProperties.getCacheNames().forEach(cacheName->{

                cacheConfigurations.put(cacheName, cacheConfig.entryTtl(Duration.ofMinutes(redisProperties.getTimeToLive())));
            });
        }

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}
