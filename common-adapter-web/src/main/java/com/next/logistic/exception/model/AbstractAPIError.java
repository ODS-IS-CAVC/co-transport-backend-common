package com.next.logistic.exception.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.next.logistic.util.ObjectMapperUtil;

public class AbstractAPIError {

    public static ObjectMapper mapper = ObjectMapperUtil.getInstance();

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public String convertToJson() throws JsonProcessingException {

        return mapper.writeValueAsString(this);
    }
}
