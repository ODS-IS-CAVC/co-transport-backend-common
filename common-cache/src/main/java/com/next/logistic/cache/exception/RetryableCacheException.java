package com.next.logistic.cache.exception;

public class RetryableCacheException extends CacheException {
    public RetryableCacheException(String message, Throwable cause) {
        super(message, cause);
    }
}
