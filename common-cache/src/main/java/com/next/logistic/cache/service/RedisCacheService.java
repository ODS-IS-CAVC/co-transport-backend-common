package com.next.logistic.cache.service;

import io.github.resilience4j.retry.Retry;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisCacheService implements CacheService {

    private final CacheManager cacheManager;
    private final Retry retry;

    public RedisCacheService(CacheManager cacheManager, Retry retry) {
        this.cacheManager = cacheManager;
        this.retry = retry;
    }

    @Override
    public <T> Optional<T> get(String key, Class<T> type) {
        return (Optional<T>) Retry.decorateSupplier(retry, () -> {
            Cache cache = cacheManager.getCache("default");
            if (cache != null) {
                return Optional.ofNullable(cache.get(key, type));
            }
            return Optional.empty();
        }).get();
    }

    @Override
    public <T> void put(String key, T value) {
        Retry.decorateRunnable(retry, () -> {
            Cache cache = cacheManager.getCache("default");
            if (cache != null) {
                cache.put(key, value);
            }
        }).run();
    }

    @Override
    public void evict(String key) {
        Retry.decorateRunnable(retry, () -> {
            Cache cache = cacheManager.getCache("default");
            if (cache != null) {
                cache.evict(key);
            }
        }).run();
    }
}