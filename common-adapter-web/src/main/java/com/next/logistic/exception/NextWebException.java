package com.next.logistic.exception;

import com.next.logistic.exception.model.NextAPIError;
import org.springframework.util.Assert;

import jakarta.validation.constraints.NotNull;

public class NextWebException extends NextCoreException{

    private static final long serialVersionUID = 1487246939799332061L;

    public NextAPIError getApiError() {
        return apiError;
    }

    private final NextAPIError apiError;

    public NextWebException(@NotNull NextAPIError apiError) {
        super();
        Assert.notNull(apiError, "API error must not be null");
        this.apiError = apiError;
    }
}
