package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing data access methods related to the User object
 */
public class UserDAO {
    /**
     * Check if the supplied username and password belong to a valid user
     * @param username the user's username
     * @param password the user's password
     * @return a User object, if one matches. otherwise null.
     * @throws SQLException
     */
    public static User checkCredentials(String username, String password)throws SQLException {
        String sql = "SELECT User_ID, User_Name\n" +
                     "FROM users\n" +
                     "WHERE User_Name = '" + username + "'\n" +
                     "AND Password = '" + password + "';";
        DBConnection.openConnection();
        Statement s = DBConnection.connection.createStatement();
        ResultSet r;
        r = s.executeQuery(sql);
        List<User> usersFound = new ArrayList<User>();
        while (r.next()) {
            usersFound.add(new User(
                    r.getInt(1),
                    r.getString(2)
            ));
        }
        DBConnection.closeConnection();
        if (usersFound.size() > 0) {
            return usersFound.get(0);
        }
        else {
            return null;
        }
    }
}
