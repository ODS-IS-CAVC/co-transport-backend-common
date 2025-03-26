package com.next.logistic.cache.service;

import java.util.Optional;

public interface CacheService {
    <T> Optional<T> get(String key, Class<T> type);
    <T> void put(String key, T value);
    void evict(String key);
}
