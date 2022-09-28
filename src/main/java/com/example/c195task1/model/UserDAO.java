package com.example.c195task1.model;

import com.example.c195task1.helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserDAO {
    JDBC db;

    public UserDAO() {
        this.db = new JDBC();
    }

    public ObservableList<User> getAllUsers() {
        return FXCollections.observableArrayList();
    }
}
