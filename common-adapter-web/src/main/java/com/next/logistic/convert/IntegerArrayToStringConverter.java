package com.next.logistic.convert;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Converter
@Slf4j
public class IntegerArrayToStringConverter extends GenericListToStringConverter<Integer> {
    @Override
    protected String convertElementToString(Integer element) {
        return element.toString();
    }

    @Override
    protected Integer convertStringToElement(String value) {
        return Integer.valueOf(value);
    }
}
