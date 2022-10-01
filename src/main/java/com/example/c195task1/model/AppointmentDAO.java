package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentDAO {
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sql = "SELECT Appointment_ID, `Title`, `Description`, Location, `Type`, `Start`, `End`, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID, Contact_Name\n" +
                "FROM appointments\n" +
                "LEFT JOIN contacts\n" +
                "USING (Contact_ID);";
        Statement s = DBConnection.connection.createStatement();
        ResultSet r;
        r = s.executeQuery(sql);
        while (r.next()) {
            // add to list
            allAppointments.add(new Appointment(
                    r.getInt(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getTimestamp(6).toLocalDateTime(),
                    r.getTimestamp(7).toLocalDateTime(),
                    r.getTimestamp(8).toLocalDateTime(),
                    r.getString(9),
                    r.getTimestamp(10).toLocalDateTime(),
                    r.getString(11),
                    r.getInt(12),
                    r.getInt(13),
                    r.getInt(14),
                    r.getString(15)
            ));
        }
        DBConnection.closeConnection();

        return allAppointments;
    }
}
