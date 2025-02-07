package com.javarush.stepanov.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.stepanov.exception.AppException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.ERROR_JSON_TO_MAP;
import static com.javarush.stepanov.constants.ConstantsCommon.ERROR_MAP_TO_JSON;

@Converter(autoApply = true)
public class MapToJsonConverter implements AttributeConverter<Map<String, Integer>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Integer> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new AppException(ERROR_MAP_TO_JSON, e);
        }
    }

    @Override
    public Map<String, Integer> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Map<String, Integer>>() {});
        } catch (JsonProcessingException e) {
            throw new AppException(ERROR_JSON_TO_MAP, e);
        }
    }
}