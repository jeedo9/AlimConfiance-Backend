package com.alimconfiance.dtos;

public class Location {

    private String city;
    private String depCode;
    private String type;


    public Location(final String city, final String depCode, final String type) {
        this.city = city;
        this.depCode = depCode;
        this.type = type;
    }
    public String getCity() {
        return this.city;
    }
    public String getDepCode() {
        return this.depCode;
    }

    public String getType() {
        return this.type;
    }
}