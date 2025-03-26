package com.next.logistic.cache.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class CacheUtils {
    private static final Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
