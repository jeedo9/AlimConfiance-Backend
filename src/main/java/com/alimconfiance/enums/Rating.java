package com.alimconfiance.enums;

public enum Rating {
    EXCELLENT("Très satisfaisant"),
    GOOD("Satisfaisant"),
    AVERAGE("A améliorer"),
    POOR("A corriger de manière urgente");

    private String description;

    Rating(final String description) {
        this.description = description;
    }

  
    public String getDescription() {
        return this.description;
    }

    public static Rating fromString(final String stringRating) {
        for (Rating rating : Rating.values()) {
            if (rating.getDescription().equalsIgnoreCase(stringRating)) return rating;
        }
        throw new IllegalArgumentException("Unknown rating: " + stringRating);
    }
}