package com.sanie.ahmad.util;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class CustomCoordinatesDeserializer extends JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

        if (node.isArray()) {
            // Check if the node is an array of arrays (Polygon)
            if (node.get(0).isArray()) {
                return mapper.convertValue(node, new TypeReference<List<List<List<Double>>>>() {});
            }
            // Handle as a simple list if the node is an array of numbers (Point)
            else {
                return mapper.convertValue(node, new TypeReference<List<Double>>() {});
            }
        }
        return null;
    }
}

