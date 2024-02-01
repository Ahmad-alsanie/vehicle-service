package com.sanie.ahmad.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanie.ahmad.model.PolygonZone;
import com.sanie.ahmad.service.PolygonService;
import com.sanie.ahmad.service.VehicleDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Configuration
public class PolygonServiceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(PolygonServiceConfig.class);

    @Bean
    public PolygonService polygonService(List<PolygonZone> polygonZones, VehicleDataService vehicleService) {
        return new PolygonService(polygonZones, vehicleService);
    }

    @Bean
    public List<PolygonZone> polygonZones() {
        try {
            InputStream inputStream = new ClassPathResource("polygons.json").getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, new TypeReference<>() {});
        } catch (Exception e) {
            LOGGER.error("Exception while loading polygons: {}", e);
            return Collections.emptyList();
        }
    }
}
