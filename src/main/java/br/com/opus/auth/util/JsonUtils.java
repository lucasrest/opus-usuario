package br.com.opus.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static String objectToJson(Object objeto) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(objeto);

    }

    public static Object jsonToObject(String json, Class<?> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    public static <T> List<T> transformJsonToTypedList(Object objeto, Class<T> classe) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String valueAsString = mapper.writeValueAsString(objeto);
            JavaType type = mapper.getTypeFactory().
                    constructCollectionType(List.class, classe);
            return mapper.readValue(valueAsString, type);
        } catch (Exception e) {
            //Erro
        }
        return new ArrayList<>();
    }
}
