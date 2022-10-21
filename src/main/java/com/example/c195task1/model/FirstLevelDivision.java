package com.example.c195task1.model;

/**
 * A class to hold information about sub-national divisions from the database
 */
public class FirstLevelDivision {
    int divisionId;
    String division;
    int countryId;

    /**
     * Constructor for the FirstLevelDivision class
     * @param divisionId the ID of the division
     * @param division the name of the division
     * @param countryId the ID of the country that contains this division
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Get the ID of the division
     * @return integer division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Get the name of the division
     * @return string containing division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * Get the ID of the country that contains this division
     * @return integer ID of related country
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Create a string representation of the first-level division
     * @return string describing the first-level division
     */
    @Override
    public String toString() {
        return division;
    }
}
