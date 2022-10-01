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
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID, Division, Country\n" +
                "FROM customers\n" +
                "LEFT JOIN first_level_divisions\n" +
                "USING (Division_ID)\n" +
                "LEFT JOIN countries\n" +
                "USING (Country_ID);";
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
                    r.getInt(6),
                    r.getString(7),
                    r.getString(8)
                    ));
        }
        DBConnection.closeConnection();
        return allUsers;
    }
}
