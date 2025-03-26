package com.next.logistic.elastic.exception;

import com.next.logistic.exception.NextCoreException;

public class SearchException extends NextCoreException {

    private static final long serialVersionUID = -4016828079148489179L;
    private String errorCode;
    public SearchException() {
    }

    public SearchException(String message, String errorCode) {
        super(message);
        this.errorCode=errorCode;
    }

    public SearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SearchException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
