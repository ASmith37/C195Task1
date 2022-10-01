package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
    // get all customers
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allUsers = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sql = "SELECT * FROM customers";
        Statement s = DBConnection.connection.createStatement();
        ResultSet r;
        r = s.executeQuery(sql);
        while (r.next()) {
            // add to list
            allUsers.add(new Customer(
                    r.getInt(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getTimestamp(6).toLocalDateTime(),
                    r.getString(7),
                    r.getTimestamp(8).toLocalDateTime(),
                    r.getString(9),
                    r.getInt(10)
                    ));
        }
        DBConnection.closeConnection();
        return allUsers;
    }
}
