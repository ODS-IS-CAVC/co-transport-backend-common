package com.next.logistic.convert;

import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;


@Converter
@Slf4j
public class LongArrayToStringConverter extends GenericListToStringConverter<Long> {


    @Override
    protected String convertElementToString(Long element) {
        return element.toString();
    }

    @Override
    protected Long convertStringToElement(String value) {
        return Long.valueOf(value);
    }
}
