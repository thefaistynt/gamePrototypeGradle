package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Serializer<T> {
    public void serialize(List<T> objects, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(objects);
        File file = new File(filePath);
        objectMapper.writeValue(file, objects);
        System.out.println("Objects serialized in " + filePath);
    }
}
