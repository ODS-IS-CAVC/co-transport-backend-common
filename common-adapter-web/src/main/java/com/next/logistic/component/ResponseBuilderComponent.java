package com.next.logistic.component;


import com.next.logistic.model.ResponseData;
import com.next.logistic.model.ResponseError;
import com.next.logistic.model.SoaResponsePool;
import com.next.logistic.util.BaseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

import static com.next.logistic.util.Constants.XHTTP.ATTRIBUTE_REQUEST_ID;

@Component
public class ResponseBuilderComponent {

    private final MessageSource messageSource;

    public ResponseBuilderComponent(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public <T> void enrich(ResponseData<T> responseData, HttpServletRequest httpServletRequest){

        ResponseError error = Objects.nonNull(responseData.getError()) ? responseData.getError() : new ResponseError();
        error.setRequestId(MDC.get(ATTRIBUTE_REQUEST_ID));
        error.setMessage(messageSource.getMessage(SoaResponsePool.get(error.getCode()).getSoaErrorMsg(), null,
                LocaleContextHolder.getLocale()));
        error.setDateTime(BaseUtil.dateToString(Instant.now()));
        responseData.setError(error);
    }

    public <T> ResponseData<T>  buildResponse(T data, HttpServletRequest httpServletRequest){
        ResponseData<T> responseData = new ResponseData<>();

//        ResponseError error = new ResponseError();
//        error.setCode("soa.code.000");
        responseData.setData(data);
//        error.setRequestId(MDC.get(ATTRIBUTE_REQUEST_ID));
//        error.setMessage(messageSource.getMessage(SoaResponsePool.get(error.getCode()).getSoaErrorMsg(), null,
//                LocaleContextHolder.getLocale()));
//        error.setDateTime(BaseUtil.dateToString(Instant.now()));
//
//        responseData.setError(error);

        return responseData;
    }

}
