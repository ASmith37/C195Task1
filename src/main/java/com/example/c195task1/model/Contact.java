package com.example.c195task1.model;

/**
 * Class that holds data for the contact table in the database
 */
public class Contact {
    private int id;
    private String name;
    private String email;

    /**
     * Constructor for the Contact class
     * @param id the contact ID
     * @param name the contact's name
     * @param email the contact's email address
     */
    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Get the ID of the contact
     * @return integer of ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of the contact
     * @return string containing contact name
     */
    public String getName() {
        return name;
    }

    /**
     * Convert the contact to a string to display
     * @return a string containing a representation of the contact
     */
    @Override
    public String toString() {
        return this.name;
    }
}
