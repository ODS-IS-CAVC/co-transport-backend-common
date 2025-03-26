package com.next.logistic.exception;

import com.next.logistic.util.NextError;

public class NextUserServiceException extends RuntimeException{

    private final NextError dataAuthorizationError;

    public NextUserServiceException(NextError dataAuthorizationError) {
        super(dataAuthorizationError.getCode()+"-"+dataAuthorizationError.getDescription());
        this.dataAuthorizationError=dataAuthorizationError;
    }
    public NextUserServiceException(NextError dataAuthorizationError, Throwable cause) {
        super(cause);
        this.dataAuthorizationError=dataAuthorizationError;
    }
    public NextUserServiceException(NextError dataAuthorizationError, String traceMessage) {
        super(traceMessage);
        this.dataAuthorizationError=dataAuthorizationError;
    }
}
