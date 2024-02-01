package com.sanie.ahmad.dto;

import java.util.List;

public class PolygonDTO {
    private String id;
    private String name;
    private List<VehicleDTO> vehicles;

    public PolygonDTO() {
    }

    // Getters and Setters for id, name, and vehicles
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }
}

