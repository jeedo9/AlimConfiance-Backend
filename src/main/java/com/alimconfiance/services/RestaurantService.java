package com.alimconfiance.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import com.alimconfiance.enums.Rating;
import com.alimconfiance.dtos.Restaurant;
import java.util.Optional;
import java.util.stream.Collectors;
import com.alimconfiance.utils.FormatFilterQueryString;


@Service
public class RestaurantService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base-restaurant-url}")
    private String baseRestaurantUrl;

    public ResponseEntity<List<Restaurant>> getSpecificRestaurant(final String userInput) {

        String url = this.baseRestaurantUrl + "/catalog/datasets/export_alimconfiance/records?limit=7&where=app_libelle_activite_etablissement=\"Restaurants\" and (com_name like \"" + userInput + "\" or dep_code like \"" +
                userInput + "\" or reg_name like \"" + userInput + "\" or dep_name like \"" + userInput + "\" or siret like \"" + userInput + "\" or app_libelle_etablissement like \"" + userInput + "\")";

        Map<String, Object> apiResponse = restTemplate.getForObject(url, Map.class);

        if (apiResponse != null && apiResponse.containsKey("results")) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) apiResponse.get("results");

            List<Restaurant> restaurants = results.stream()
                    .map(result -> {
                        String name = (String) result.get("app_libelle_etablissement");
                        String address = (String) result.get("adresse_2_ua");
                        String postalCode = (String) result.get("code_postal");
                        String city = (String) result.get("libelle_commune");
                        String inspectionDate = (String) result.get("date_inspection");
                        String activity = ((List<String>) result.get("app_libelle_activite_etablissement")).get(0);
                        Rating rating = Rating.fromString((String) result.get("synthese_eval_sanit")); 

                        return new Restaurant(name, address, postalCode, city, inspectionDate, activity, rating,"Restaurant");
                    })
                    .toList();


            return ResponseEntity.ok(restaurants);
        }

        return ResponseEntity.status(404).build();
    }
    public ResponseEntity<List<Restaurant>> getRestaurants(final int limit, final int offset) {
            String url = this.baseRestaurantUrl +  "/catalog/datasets/export_alimconfiance/records?limit=" + limit + "&offset=" + offset + "&where=app_libelle_activite_etablissement=\"Restaurants\"";
              
        
            try {

            Map<String, Object> apiResponse = restTemplate.getForObject( url, Map.class);

            if (apiResponse != null && apiResponse.containsKey("results")) {
                List<Map<String, Object>> results = (List<Map<String, Object>>) apiResponse.get("results");

                List<Restaurant> restaurants = results.stream()
                        .map(result -> {
                            String name = (String) result.get("app_libelle_etablissement");
                            String address = (String) result.get("adresse_2_ua");
                            String postalCode = (String) result.get("code_postal");
                            String city = (String) result.get("libelle_commune");
                            String inspectionDate = (String) result.get("date_inspection");
                            String activity = ((List<String>) result.get("app_libelle_activite_etablissement")).get(0);
                            Rating rating = Rating.fromString((String) result.get("synthese_eval_sanit"));

                            return new Restaurant(name, address, postalCode, city, inspectionDate, activity, rating, "Restaurant");
                        })
                        .toList();

                return ResponseEntity.ok(restaurants);
            }

            return ResponseEntity.status(404).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

       public ResponseEntity<Map<String, Object>> getFilteredRestaurants(final String location, final String hygieneLevel, final String sortFilter, final int limit, final int offset) {
        
        String queryString = FormatFilterQueryString.formatFilterQueryString(location, hygieneLevel, sortFilter);
        String url = this.baseRestaurantUrl + "/catalog/datasets/export_alimconfiance/records?limit=" + limit + "&offset=" + offset + "&where=app_libelle_activite_etablissement=\"Restaurants\"" + queryString;
        try {
        Map<String, Object> apiResponse = restTemplate.getForObject(url, Map.class);
        if (apiResponse != null && apiResponse.containsKey("results")) {

            List<Map<String, Object>> results = (List<Map<String, Object>>) apiResponse.get("results");
            int totalCount = (int) apiResponse.getOrDefault("total_count", 0);

            List<Restaurant> restaurants = results.stream()
                    .map(result -> {
                        String name = (String) result.get("app_libelle_etablissement");
                        String address = (String) result.get("adresse_2_ua");
                        String postalCode = (String) result.get("code_postal");
                        String city = (String) result.get("libelle_commune");
                        String inspectionDate = (String) result.get("date_inspection");
                        String activity = ((List<String>) result.get("app_libelle_activite_etablissement")).get(0);
                        Rating rating = Rating.fromString((String) result.get("synthese_eval_sanit"));

                        return new Restaurant(name, address, postalCode, city, inspectionDate, activity, rating,"Restaurant");
                    })
                    .toList();

            Map<String, Object> response = new HashMap<String, Object>();
            response.put("restaurants", restaurants);
            response.put("total_count", totalCount);
            System.out.println(response);
            System.out.println("hello");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(404).body(Collections.emptyMap());
    }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}