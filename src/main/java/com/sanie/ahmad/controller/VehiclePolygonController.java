package com.sanie.ahmad.controller;
import com.sanie.ahmad.service.VehicleDataService;
import com.sanie.ahmad.dto.PolygonDTO;
import com.sanie.ahmad.dto.VehicleDTO;
import com.sanie.ahmad.model.PolygonZone;
import com.sanie.ahmad.service.GeoJsonService;
import com.sanie.ahmad.service.PolygonService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehiclePolygonController {

    private final VehicleDataService vehicleService;
    private final PolygonService polygonService;
    private final GeoJsonService geoJsonService;

    public VehiclePolygonController(VehicleDataService vehicleService, PolygonService polygonService, GeoJsonService geoJsonService) {
        this.vehicleService = vehicleService;
        this.polygonService = polygonService;
        this.geoJsonService = geoJsonService;
    }


    @GetMapping("/vehicles")
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.fetchVehicles();
    }

    // Endpoint to fetch a specific polygon by ID
    @GetMapping("/polygons/{id}")
    public PolygonDTO getPolygon(@PathVariable String id) {
        return polygonService.getPolygonById(id);
    }

    @GetMapping("/polygons")
    public List<PolygonZone> getAllZones(){
        return geoJsonService.readPolygons();
    }
}

