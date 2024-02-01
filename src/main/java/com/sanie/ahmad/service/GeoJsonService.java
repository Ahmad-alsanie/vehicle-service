package com.sanie.ahmad.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanie.ahmad.model.PolygonZone;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class GeoJsonService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<PolygonZone> readPolygons() {
        try (InputStream is = getClass().getResourceAsStream("/polygons.json")) {
            return objectMapper.readValue(is, objectMapper.getTypeFactory().constructCollectionType(List.class, PolygonZone.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
