package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactDAO {
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sql = "SELECT Contact_ID, Contact_Name, Email\n" +
                     "FROM contacts;";
        Statement s = DBConnection.connection.createStatement();
        ResultSet r;
        r = s.executeQuery(sql);
        while (r.next()) {
            // add to list
            allContacts.add(new Contact(
                    r.getInt(1),
                    r.getString(2),
                    r.getString(3)
            ));
        }
        DBConnection.closeConnection();

        return allContacts;
    }
}
