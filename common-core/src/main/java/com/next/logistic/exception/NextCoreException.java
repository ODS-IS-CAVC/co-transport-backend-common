package com.next.logistic.exception;

import java.util.UUID;

public class NextCoreException extends RuntimeException{

    private static final long serialVersionUID = -8948513508032373914L;

    private final String eid = UUID.randomUUID().toString();

    public NextCoreException() {
    }

    public NextCoreException(String message) {
        super(message);
    }

    public NextCoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public NextCoreException(Throwable cause) {
        super(cause);
    }

    public NextCoreException(String message, Throwable cause, boolean enableSuppression,
                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getEid() {
        return eid;
    }
}
