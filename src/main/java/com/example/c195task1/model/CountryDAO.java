package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class to hold data access methods related to the Country class
 */
public class CountryDAO {
    /**
     * Get a list of all countries in the database
     * @return a list of Country objects
     * @throws SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList allCountries = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sql = "SELECT Country_ID, Country\n" +
                "FROM countries;";
        Statement s = DBConnection.connection.createStatement();
        ResultSet r;
        r = s.executeQuery(sql);

        while (r.next()) {
            allCountries.add(new Country(
                    r.getInt(1),
                    r.getString(2)
            ));
        }

        DBConnection.closeConnection();
        return allCountries;
    }
}
