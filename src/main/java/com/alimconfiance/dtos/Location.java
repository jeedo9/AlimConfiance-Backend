package com.alimconfiance.dtos;
public class Location {

    private String city;
    private String depCode;

    public Location(final String city, final String depCode) {
        this.city = city;
        this.depCode = depCode;
    }
    public String getCity() {
        return this.city;
    }
    public String getDepCode() {
        return this.depCode;
    }
}