package com.sanie.ahmad.integration;

import com.sanie.ahmad.service.PolygonService;
import com.sanie.ahmad.dto.PolygonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PolygonServiceIntTest {

    @Autowired
    private PolygonService polygonService;

    @Test
    public void testPolygonServiceFunctionality() {
        String knownPolygonId = "58a58bf6766d51540f779930";

        PolygonDTO polygon = polygonService.getPolygonById(knownPolygonId);

        assertNotNull(polygon, "Polygon should not be null");
        assertEquals(knownPolygonId, polygon.getId(), "Polygon ID should match");

        assertNotNull(polygon.getName(), "Polygon name should not be null");
    }
}
