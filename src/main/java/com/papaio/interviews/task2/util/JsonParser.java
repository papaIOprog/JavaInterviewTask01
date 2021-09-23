package com.papaio.interviews.task2.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.io.InputStream;

public class JsonParser<T> {
    private final ObjectMapper mapper;
    public JsonParser(PropertyNamingStrategy strategy) {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(strategy);
    }

    public T parse(InputStream is, Class<T> valueType) throws IOException {
        return mapper.readValue(is, valueType);
    }
}