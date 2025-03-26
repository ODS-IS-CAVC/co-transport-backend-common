package com.next.logistic.exception.model;

import com.next.logistic.model.SoaResponse;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class NextAPIError extends AbstractAPIError{

    private HttpStatus status;

    private SoaResponse soaResponse;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;
    private String debugMessage;

    private List<ObjectError> fieldError;

    private Object[] parameters;

    private NextAPIError(@NotNull SoaResponse soaResponse) {
        Assert.notNull(soaResponse, "SOA error code must not be null");
        timestamp = LocalDateTime.now();
        fieldError = new ArrayList<>();
        this.soaResponse = soaResponse;
    }

    public NextAPIError(HttpStatus status, SoaResponse soaResponse, Object...parameters) {
        this(soaResponse);
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status;
        this.parameters = parameters;
    }

    public NextAPIError(@NotNull HttpStatus status, Throwable ex, SoaResponse soaResponse, Object...parameters) {
        this(status, soaResponse, parameters);
        this.debugMessage = ex != null ? ex.getLocalizedMessage() : null;
    }

    public NextAPIError(@NotNull HttpStatus status, String debugMessage,
                     SoaResponse soaResponse, Object...parameters) {
        this(status, soaResponse, parameters);
        this.debugMessage = debugMessage;
    }
}
