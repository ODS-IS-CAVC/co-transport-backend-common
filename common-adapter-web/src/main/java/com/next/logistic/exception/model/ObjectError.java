package com.next.logistic.exception.model;


import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ObjectError {
    private String field;
    private String issue;

}
