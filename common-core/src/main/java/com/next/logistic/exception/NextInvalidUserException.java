package com.next.logistic.exception;

import com.next.logistic.util.NextError;

public class NextInvalidUserException extends NextCoreException{

    private static final long serialVersionUID = -1274117479670333584L;

    private final NextError nextError;

    public NextInvalidUserException(NextError xError) {
        this.nextError = xError;
    }

    public NextInvalidUserException(NextError xError, Throwable cause) {
        super(cause);
        this.nextError = xError;
    }

    public NextInvalidUserException(NextError xError, String traceMessage) {
        super(traceMessage);
        this.nextError = xError;
    }


    public NextError getNextError() {
        return nextError;
    }
}
