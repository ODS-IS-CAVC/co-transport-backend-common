package com.next.logistic.exception;

public class NextMapperException extends NextCoreException{

    private static final long serialVersionUID = 7305523302855697078L;

    public NextMapperException(String message) {
        super(message);
    }

    public NextMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
