package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class containing data access methods related to the FirstLevelDivision class
 */
public class FirstLevelDivisionDAO {
    /**
     * Get a list of all first-level division in the database
     * @return list of FirstLevelDivision objects
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> getAllFLDs() throws SQLException {
        ObservableList allFLDs = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sql = "SELECT Division_ID, Division, Country_ID\n" +
                     "FROM first_level_divisions;";
        Statement s = DBConnection.connection.createStatement();
        ResultSet r;
        r = s.executeQuery(sql);

        while (r.next()) {
            allFLDs.add(new FirstLevelDivision(
                    r.getInt(1),
                    r.getString(2),
                    r.getInt(3)
            ));
        }

        DBConnection.closeConnection();
        return allFLDs;
    }
}
