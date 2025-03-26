package com.next.logistic.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class SoaResponse {

    public static final String PREFIX_MESSAGE_CODE = "soa.code.";

    private final String soaErrorCode;
    private final String soaErrorMsg;

    protected SoaResponse(@NonNull String soaErrorCode) {
        Assert.notNull(soaErrorCode, "SoaErrorCode must not be null");
        this.soaErrorCode = soaErrorCode;
        this.soaErrorMsg = soaErrorCode.startsWith(PREFIX_MESSAGE_CODE) ? soaErrorCode
                : PREFIX_MESSAGE_CODE + soaErrorCode;
    }

}
