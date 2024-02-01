package com.sanie.ahmad.service;
import com.sanie.ahmad.dto.PolygonDTO;
import com.sanie.ahmad.dto.VehicleDTO;
import com.sanie.ahmad.model.PolygonZone;
import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolygonService {

    private final List<PolygonZone> polygons;
    private final VehicleDataService vehicleService;

    public PolygonService(List<PolygonZone> polygons, VehicleDataService vehicleService) {
        this.polygons = polygons;
        this.vehicleService = vehicleService;
    }

    public PolygonDTO getPolygonById(String id) {
        // Find the polygon by ID and return it
        return polygons.stream()
                .filter(polygon -> polygon.getId().equals(id))
                .map(this::convertToPolygonDTO)
                .findFirst()
                .orElse(null);
    }

    private PolygonDTO convertToPolygonDTO(PolygonZone polygonZone) {
        // Convert PolygonZone to PolygonDTO, including vehicles inside this polygon
        List<VehicleDTO> vehiclesInside = vehicleService.fetchVehicles().stream()
                .filter(vehicle -> isVehicleInsidePolygon(vehicle, polygonZone))
                .collect(Collectors.toList());

        PolygonDTO polygonDTO = new PolygonDTO();
        polygonDTO.setId(polygonZone.getId());
        polygonDTO.setName(polygonZone.getName());
        polygonDTO.setVehicles(vehiclesInside);

        return polygonDTO;
    }

    public boolean isVehicleInsidePolygon(VehicleDTO vehicle, PolygonZone polygonZone) {
        GeometryFactory geometryFactory = new GeometryFactory();

        // Create a Point object for the vehicle's location
        Point vehiclePoint = geometryFactory.createPoint(new Coordinate(vehicle.getPosition().getLongitude(), vehicle.getPosition().getLatitude()));

        Geometry geometry = null;
        String geometryType = polygonZone.getGeometry().getType();

        if (geometryType.equalsIgnoreCase("Polygon")) {
            // Handle Polygon geometry
            List<List<List<Double>>> polygonCoords = (List<List<List<Double>>>) polygonZone.getGeometry().getCoordinates();
            Coordinate[] coords = polygonCoords.stream()
                    .flatMap(List::stream)
                    .map(coord -> new Coordinate(coord.get(0), coord.get(1)))
                    .toArray(Coordinate[]::new);
            LinearRing ring = geometryFactory.createLinearRing(coords);
            geometry = geometryFactory.createPolygon(ring, null);
        } else if (geometryType.equalsIgnoreCase("Point")) {
            // Handle Point geometry
            List<Double> pointCoords = (List<Double>) polygonZone.getGeometry().getCoordinates();
            geometry = geometryFactory.createPoint(new Coordinate(pointCoords.get(0), pointCoords.get(1)));
        }

        // If geometry is a Polygon and contains the vehicle point
        return geometry instanceof Polygon && geometry.contains(vehiclePoint);
    }
}
