package com.next.logistic.exception;

import com.next.logistic.util.NextError;

public class NextTokenException extends RuntimeException{

    private final NextError dataAuthorizationError;

    public NextTokenException(NextError dataAuthorizationError) {
        super(dataAuthorizationError.getCode()+"-"+dataAuthorizationError.getDescription());
        this.dataAuthorizationError=dataAuthorizationError;
    }
    public NextTokenException(NextError dataAuthorizationError, Throwable cause) {
        super(cause);
        this.dataAuthorizationError=dataAuthorizationError;
    }
    public NextTokenException(NextError dataAuthorizationError, String traceMessage) {
        super(traceMessage);
        this.dataAuthorizationError=dataAuthorizationError;
    }
}
