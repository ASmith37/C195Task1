package com.example.c195task1.model;

import java.time.LocalDateTime;

/** Class that contains information related to the class table in the database
 */
public class Customer {
    int id;
    String name;
    String address;
    String postCode;
    String phone;
    int divisionId;
    String division;
    int countryId;
    String country;

    /**
     * Returns a string representation of the customer
     * @return the string describing the customer
     */
    @Override
    public String toString() {
        return String.format("%d - %s", this.id, this.name);
    }

    /**
     * Constructor for the Customer class
     * @param id the unique ID of the customer
     * @param name the customer's name
     * @param address the customer's address
     * @param postCode the customer's post code
     * @param phone the customer's phone number
     * @param divisionId the ID of the customer's division
     * @param division the name of the customer's sub-national division
     * @param countryId the ID of the customer's country
     * @param country the name of customer's country
     */
    public Customer(int id, String name, String address, String postCode, String phone, int divisionId, String division, int countryId, String country) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postCode = postCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Get the ID of the customer
     * @return the integer ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of the customer
     * @return a string of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the address of the customer
     * @return a string of the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the postal code of the customer
     * @return a string of the postal code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Get the phone number of the customer
     * @return a string containing the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get the division ID for the customer
     * @return an integer referring to the ID of the division
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Get the division name for the customer
     * @return the string name of the customer's sub-national division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Get the country ID for the customer
     * @return an integer ID of the country
     */
    public int getCountryId() {
        return countryId;
    }

}
