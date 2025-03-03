package com.alimconfiance.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alimconfiance.dtos.Location;
@Service
public class LocationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base-location-url}")
    private String baseLocationUrl;
     public ResponseEntity<List<Location>> getLocations(final String userInput) {
        String url =  this.baseLocationUrl + "/catalog/datasets/georef-france-commune/records?limit=7&where=com_name like \"" + userInput + "\" or dep_code like \"" +
                userInput + "\" or reg_name like \"" + userInput + "\" or dep_name like \"" + userInput + "\"";
        System.out.println(url);
        try {
            Map<String, Object> apiResponse = restTemplate.getForObject( url, Map.class);

            if (apiResponse != null && apiResponse.containsKey("results")) {
                List<Map<String, Object>> results = (List<Map<String, Object>>) apiResponse.get("results");
    
                List<Location> locations = results.stream()
                        .map(result -> {
                            String city = ( (List<String>) result.get("com_name")).get(0);
                            String depCode = ( (List<String>) result.get("dep_code")).get(0);
    
                            return new Location(city, depCode,"Location");
                        })
                        .toList();

                System.out.println(locations);
    
                return ResponseEntity.ok(locations);
            }
    
            return ResponseEntity.status(404).build();  
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}


