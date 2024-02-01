package com.sanie.ahmad;

import com.sanie.ahmad.service.VehicleDataService;
import com.sanie.ahmad.dto.VehicleDTO;
import com.sanie.ahmad.dto.VehicleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class VehicleDataServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private VehicleDataService vehicleDataService;

    private final String apiUrl = "https://web-chapter-coding-challenge-api-eu-central-1.dev.architecture.ridedev.io/api/architecture/web-chapter-coding-challenge-api/vehicles/Stuttgart";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchVehicles_SuccessfulResponse() {
        VehicleResponse mockResponse = new VehicleResponse();
        // Populate mockResponse with test data

        ResponseEntity<VehicleResponse> entity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(apiUrl, VehicleResponse.class)).thenReturn(entity);

        List<VehicleDTO> result = vehicleDataService.fetchVehicles();

        assertNotNull(result);
        // Further assertions based on the mock data
    }

    @Test
    public void testFetchVehicles_NoBodyInResponse() {
        ResponseEntity<VehicleResponse> entity = new ResponseEntity<>(null, HttpStatus.OK);
        when(restTemplate.getForEntity(apiUrl, VehicleResponse.class)).thenReturn(entity);

        List<VehicleDTO> result = vehicleDataService.fetchVehicles();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFetchVehicles_NonSuccessfulResponse() {
        ResponseEntity<VehicleResponse> entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.getForEntity(apiUrl, VehicleResponse.class)).thenReturn(entity);

        List<VehicleDTO> result = vehicleDataService.fetchVehicles();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFetchVehicles_ClientErrorException() {
        when(restTemplate.getForEntity(apiUrl, VehicleResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        List<VehicleDTO> result = vehicleDataService.fetchVehicles();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFetchVehicles_OtherException() {
        when(restTemplate.getForEntity(apiUrl, VehicleResponse.class))
                .thenThrow(new RuntimeException("Unexpected error"));

        List<VehicleDTO> result = vehicleDataService.fetchVehicles();

        assertTrue(result.isEmpty());
    }
}

