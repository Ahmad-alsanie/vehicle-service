package com.sanie.ahmad.integration;

import com.sanie.ahmad.service.VehicleDataService;
import com.sanie.ahmad.dto.VehicleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VehicleServiceIntTest {

    @Autowired
    private VehicleDataService vehicleDataService;

    @Test
    public void testFetchVehicles() {
        List<VehicleDTO> vehicles = vehicleDataService.fetchVehicles();

        assertNotNull(vehicles, "The list of vehicles should not be null");
    }
}
