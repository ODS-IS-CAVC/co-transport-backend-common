package com.next.logistic.model;

import com.next.logistic.exception.model.AbstractAPIError;
import com.next.logistic.exception.model.ObjectError;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
public class ResponseError extends AbstractAPIError {

    private @NotNull int status = 200; //2xx success, !2xx error
    private String code;
    private String requestId; //client message id
    private String message; // message description
    private List<ObjectError> details;
    private String dateTime;
}
