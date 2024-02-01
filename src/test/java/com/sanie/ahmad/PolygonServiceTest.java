package com.sanie.ahmad;

import com.sanie.ahmad.service.PolygonService;
import com.sanie.ahmad.service.VehicleDataService;
import com.sanie.ahmad.dto.PolygonDTO;
import com.sanie.ahmad.dto.VehicleDTO;
import com.sanie.ahmad.model.Geometry;
import com.sanie.ahmad.model.PolygonZone;
import com.sanie.ahmad.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PolygonServiceTest {

    @Mock
    private VehicleDataService vehicleDataService;

    @InjectMocks
    private PolygonService polygonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<PolygonZone> mockPolygons = Collections.singletonList(createMockPolygonZoneWithGeometry());
        polygonService = new PolygonService(mockPolygons, vehicleDataService);
    }

    @Test
    public void testGetPolygonById_ValidId() {
        // Assuming there is a PolygonZone with ID "1" in the loaded polygons
        PolygonDTO result = polygonService.getPolygonById("1");
        assertNotNull(result);
        assertEquals("1", result.getId());
        // Additional assertions based on expected PolygonDTO properties
    }

    @Test
    public void testGetPolygonById_InvalidId() {
        PolygonDTO result = polygonService.getPolygonById("invalid-id");
        assertNull(result);
    }

    @Test
    public void testIsVehicleInsidePolygon_VehicleInside() {
        VehicleDTO vehicle = new VehicleDTO();
        vehicle.setPosition(new Position(48.5, 9.5)); // Sample position inside the polygon

        PolygonZone polygonZone = createMockPolygonZoneWithGeometry();

        boolean result = polygonService.isVehicleInsidePolygon(vehicle, polygonZone);
        assertTrue(result);
    }

    @Test
    public void testIsVehicleInsidePolygon_VehicleOutside() {
        VehicleDTO vehicle = new VehicleDTO();
        vehicle.setPosition(new Position(0.0, 0.0)); // Position outside the polygon

        PolygonZone polygonZone = createMockPolygonZoneWithGeometry();

        boolean result = polygonService.isVehicleInsidePolygon(vehicle, polygonZone);
        assertFalse(result);
    }

    @Test
    public void testConvertToPolygonDTO_WithVehicles() {

        VehicleDTO vehicleInside = new VehicleDTO();
        vehicleInside.setPosition(new Position(48.5, 9.5)); // Inside the polygon

        VehicleDTO vehicleOutside = new VehicleDTO();
        vehicleOutside.setPosition(new Position(0.0, 0.0)); // Outside the polygon

        when(vehicleDataService.fetchVehicles()).thenReturn(Arrays.asList(vehicleInside, vehicleOutside));

        // Call a public method that uses convertToPolygonDTO internally
        PolygonDTO result = polygonService.getPolygonById("1");

        assertNotNull(result);
        assertEquals(1, result.getVehicles().size()); // Expecting 1 vehicle inside the polygon
    }

    private PolygonZone createMockPolygonZoneWithGeometry() {
        PolygonZone polygonZone = new PolygonZone();
        polygonZone.setId("1");
        Geometry geometry = new Geometry();
        geometry.setType("Polygon");
        List<List<List<Double>>> coordinates = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(9.0, 48.0),
                        Arrays.asList(10.0, 48.0),
                        Arrays.asList(10.0, 49.0),
                        Arrays.asList(9.0, 49.0),
                        Arrays.asList(9.0, 48.0)
                )
        );
        geometry.setCoordinates(coordinates);
        polygonZone.setGeometry(geometry);
        return polygonZone;
    }
}
