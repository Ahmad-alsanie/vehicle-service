package com.sanie.ahmad.service;

import com.sanie.ahmad.dto.VehicleDTO;
import com.sanie.ahmad.dto.VehicleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Service
@EnableScheduling
public class VehicleDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDataService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://web-chapter-coding-challenge-api-eu-central-1.dev.architecture.ridedev.io/api/architecture/web-chapter-coding-challenge-api/vehicles/Stuttgart"; // Replace with actual API URL

    @Scheduled(fixedDelay = 60000) // Fetch data every 60 seconds
    public List<VehicleDTO> fetchVehicles() {
        try {
            ResponseEntity<VehicleResponse> response = restTemplate.getForEntity(apiUrl, VehicleResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                VehicleResponse body = response.getBody();
                if (body != null && body.getData() != null) {
                    return body.getData();
                }
            }
            LOGGER.error("Body is null or non-success status code: {}", response.getStatusCode());
            // For both null body and non-success status, return an empty list
            return List.of();
        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx)
            LOGGER.error("Client error: {}", e);
            return List.of();
        } catch (Exception e) {
            // Handle other errors
            LOGGER.error("Exception has been thrown: {}",e);
            return List.of();
        }
    }
}
