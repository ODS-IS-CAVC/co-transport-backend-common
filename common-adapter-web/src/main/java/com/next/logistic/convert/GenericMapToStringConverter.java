package com.next.logistic.convert;

import com.next.logistic.util.ObjectMapperUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Converter
public abstract class GenericMapToStringConverter<T> implements AttributeConverter<Map<String, T>, String> {

    private static final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    @Override
    public String convertToDatabaseColumn(Map<String, T> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            log.error("Error converting Map to JSON: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Failed to convert Map to JSON string");
        }
    }

    @Override
    public Map<String, T> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return Collections.emptyMap();
        }
        try {
            return objectMapper.readValue(dbData, getTypeReference());
        } catch (Exception e) {
            log.error("Error converting JSON to Map: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Failed to convert JSON string to Map");
        }
    }

    /**
     * Provide the TypeReference for the Map to correctly deserialize JSON.
     * Example:
     * return new TypeReference<Map<String, Integer>>() {};
     */
    protected abstract com.fasterxml.jackson.core.type.TypeReference<Map<String, T>> getTypeReference();
}
