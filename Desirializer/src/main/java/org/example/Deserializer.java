package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Deserializer<T> {
    public List<T> deserialize(String filePath, Class<T> objectType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        List<T> objects = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, objectType));
        return objects;
    }
}
