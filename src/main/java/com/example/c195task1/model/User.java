package com.example.c195task1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class to hold information related to users
 */
public class User {
    int userId;
    String userName;

    /**
     * Constructor for the User class
     * @param userId the user's ID
     * @param userName the name of the user
     */
    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * Get the ID of the user
     * @return integer of user's ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Get the name of the user
     * @return string containing user's name
     */
    public String getUserName() {
        return userName;
    }

}
