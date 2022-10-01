package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class UserDAO {
    public static boolean checkCredentials(String username, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE User_Name = '" + username
                   + "' AND Password = '" + password + "';";
        DBConnection.openConnection();
        Statement s = DBConnection.connection.createStatement();
        ResultSet r;
        r = s.executeQuery(sql);
        r.next();
        boolean result = r.getInt(1) > 0; // Apparently columns start at 1
        DBConnection.closeConnection();
        return result;
    }
}
