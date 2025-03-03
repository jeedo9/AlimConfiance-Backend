package com.alimconfiance.controllers;

import com.alimconfiance.dtos.Location;
import com.alimconfiance.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping()
    public ResponseEntity<List<Location>> getLocations(@RequestParam(value = "query") final String userInput) {
        return this.locationService.getLocations(userInput);
    }
}
