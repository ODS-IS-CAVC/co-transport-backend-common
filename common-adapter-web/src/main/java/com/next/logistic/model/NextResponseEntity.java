package com.next.logistic.model;

import org.springframework.http.ResponseEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

public class NextResponseEntity<T> extends ResponseEntity<ResponseData<T>> {

    public NextResponseEntity() {
        super(HttpStatus.OK);
    }

    public NextResponseEntity(ResponseData<T> body) {
        super(body, HttpStatus.OK);

    }

    public NextResponseEntity(MultiValueMap<String, String> headers) {
        super(headers, HttpStatus.OK);
    }

    public NextResponseEntity(ResponseData<T> body, MultiValueMap<String, String> headers) {
        super(body, headers, HttpStatus.OK);
    }

    public NextResponseEntity(ResponseData<T> body, HttpStatus httpStatus) {
        super(body, httpStatus);
    }

    public static <T> NextResponseEntity<T> of(@NotNull ResponseData<T> nextResponseData) {

        Assert.notNull(nextResponseData, "Body must not be null");
        return new NextResponseEntity<>(nextResponseData);
    }
}
