package com.alimconfiance.enums;

public enum Rating {
    EXCELLENT("Excellent", "Très satisfaisant"),
    GOOD("Good", "Satisfaisant"),
    AVERAGE("Average", "A améliorer"),
    POOR("Poor", "A corriger de manière urgente");

    private String name;
    private String description;

    private Rating(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
}