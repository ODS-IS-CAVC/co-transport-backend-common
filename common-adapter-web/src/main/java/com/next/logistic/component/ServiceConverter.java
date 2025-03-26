package com.next.logistic.component;

import com.next.logistic.exception.model.NextAPIError;
import com.next.logistic.model.ResponseData;
import com.next.logistic.model.ResponseError;
import com.next.logistic.model.SoaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ServiceConverter {

    public NextAPIError convertError(HttpStatus status, Throwable ex, SoaResponse soaResponse){

        return new NextAPIError(status, ex, soaResponse);
    }

    public <T> ResponseData<T> buildResponse(T data, String message, int status, boolean success){

        ResponseData<T> responseData = new ResponseData<>();
        ResponseError error = new ResponseError();
        responseData.setData(data);
        error.setStatus(status);
        error.setMessage(message);

        return responseData;
    }
}
