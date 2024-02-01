package com.sanie.ahmad.dto;

import java.util.List;

public class VehicleResponse {
    private List<VehicleDTO> data;

    public List<VehicleDTO> getData() {
        return data;
    }

    public void setData(List<VehicleDTO> data) {
        this.data = data;
    }
}
