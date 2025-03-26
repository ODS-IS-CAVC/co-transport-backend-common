package com.next.logistic.exception;

import com.next.logistic.util.NextError;
import lombok.Getter;

@Getter
public class NextSystemException extends NextCoreException{

    private static final long serialVersionUID = 2348560027499331636L;
    private final NextError nextError;

    public NextSystemException(NextError xError) {
        super(xError.getCode()+"-"+ xError.getDescription());
        this.nextError = xError;
    }
    public NextSystemException(NextError xError, Throwable cause) {
        super(cause);
        this.nextError = xError;
    }
    public NextSystemException(NextError xError, String traceMessage) {
        super(traceMessage);
        this.nextError = xError;
    }
}
