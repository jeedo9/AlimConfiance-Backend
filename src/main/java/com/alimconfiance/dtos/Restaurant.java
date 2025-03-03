package com.alimconfiance.dtos;
import com.alimconfiance.enums.Rating;
import java.util.Optional;

public class Restaurant {
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private String inspectionDate;
    private String activity;
    private Rating rating;
    private String type;

    public Restaurant(final String name, final String address, final String postalCode,
                      final String city, final String inspectionDate,
                      final String activity, final Rating rating, final String type) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.inspectionDate = inspectionDate;
        this.activity = activity;
        this.rating = rating;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public String getPostalCode() {
        return this.postalCode;
    }
    public String getCity() {
        return this.city;
    }
    public String getInspectionDate() {
        return this.inspectionDate;
    }
    public String getActivity() {
        return this.activity;
    }
    public Rating getRating() {
        return this.rating;
    }
    public String getType(){
        return this.type;
    }
}