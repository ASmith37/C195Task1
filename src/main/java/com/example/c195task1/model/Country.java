package com.example.c195task1.model;

/**
 * Class that holds data related to the country table in the database
 */
public class Country {
    private int countryId;
    private String country;

    /**
     * Constructor for the Country class
     * @param countryId the ID of the country
     * @param country the name of the country
     */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Get the ID of the country
     * @return the integer ID of the country
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Gets the name of the country
     * @return the string name of the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Make a string representation of the country
     * @return a string of the country name
     */
    @Override
    public String toString() {
        return country;
    }
}
