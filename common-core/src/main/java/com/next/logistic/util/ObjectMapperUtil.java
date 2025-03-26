package com.next.logistic.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperUtil {

    private static ObjectMapper instance;

    private ObjectMapperUtil(){

    }

    public static ObjectMapper getInstance(){

        if (instance == null){
            synchronized (ObjectMapperUtil.class){
                if (instance == null){
                    instance = new ObjectMapper();
                    configure(instance);
                }
            }
        }
        return instance;
    }

    private static void configure(ObjectMapper objectMapper){

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
    }
}
