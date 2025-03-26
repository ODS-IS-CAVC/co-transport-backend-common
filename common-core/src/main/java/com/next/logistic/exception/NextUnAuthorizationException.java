package com.next.logistic.exception;

import com.next.logistic.util.NextError;
import lombok.Getter;

@Getter
public class NextUnAuthorizationException extends NextCoreException{

    private static final long serialVersionUID = 3288476038628435268L;
    private final NextError authorizationError;

    public NextUnAuthorizationException(NextError authorizationError) {
        super(authorizationError.getCode()+"-"+authorizationError.getDescription());
        this.authorizationError=authorizationError;
    }
    public NextUnAuthorizationException(NextError authorizationError, Throwable cause) {
        super(cause);
        this.authorizationError=authorizationError;
    }
    public NextUnAuthorizationException(NextError authorizationError, String traceMessage) {
        super(traceMessage);
        this.authorizationError=authorizationError;
    }
}
