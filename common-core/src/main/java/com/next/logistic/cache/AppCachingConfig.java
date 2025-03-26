package com.next.logistic.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class AppCachingConfig<T> {

    private final Cache<String, T> cache;

    public AppCachingConfig(int expiryDuration, TimeUnit timeUnit) {
        cache = CacheBuilder.newBuilder().expireAfterWrite(expiryDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors()).build();
    }

    public T get(String key) {
        if (key == null) {
            return null;
        }
        return cache.getIfPresent(key);
    }

    public void invalidate(String key) {
        cache.invalidate(key);
    }

    public void add(String key, T value) {
        if (key != null && value != null) {
            cache.put(key, value);
            log.info(String.format("A %s object stored in Cache with key: %s",
                    value.getClass().getSimpleName(), key));
        }
    }
}
