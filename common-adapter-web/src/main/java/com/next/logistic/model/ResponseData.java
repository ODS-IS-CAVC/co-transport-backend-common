package com.next.logistic.model;


import com.fasterxml.jackson.annotation.JsonUnwrapped;

@lombok.Data
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
public class ResponseData<T> {

    private ResponseError error;

    @JsonUnwrapped
    private T data;
}
