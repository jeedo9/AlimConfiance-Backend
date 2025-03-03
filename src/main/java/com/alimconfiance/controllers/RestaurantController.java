package com.alimconfiance.controllers;

import com.alimconfiance.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alimconfiance.dtos.Restaurant;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam(name = "limit", defaultValue = "6") final int limit, @RequestParam(name = "offset", defaultValue = "0")final  int offset) {
        return this.restaurantService.getRestaurants(limit, offset);
    }

    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> getFilteredRestaurants(
            @RequestParam("location") String location,
            @RequestParam("hygieneLevel") String hygieneLevel,
            @RequestParam("sortFilter") String sortFilter,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    ) {
        return this.restaurantService.getFilteredRestaurants(location, hygieneLevel, sortFilter, limit, offset);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> getSpecificRestaurant(@RequestParam("query") String userInput) {
        return this.restaurantService.getSpecificRestaurant(userInput);
    }
}