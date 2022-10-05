package com.example.c195task1.model;

import com.example.c195task1.helper.DBConnection;
import com.example.c195task1.helper.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AppointmentDAO {
    private static LocalDateTime localDateTimeToEst(LocalDateTime ldt) {
        return ZonedDateTime.ofInstant(ZonedDateTime.of(ldt, ZoneId.systemDefault()).toInstant(), ZoneId.of("Etc/GMT+5")).toLocalDateTime();
    }
    private static LocalDateTime estLocalDateTimeToLocal(LocalDateTime est) {
        return ZonedDateTime.ofInstant(ZonedDateTime.of(est, ZoneId.of("Etc/GMT+5")).toInstant(), ZoneId.systemDefault()).toLocalDateTime();
    }
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
    public static int generateAppointmentId() throws SQLException {
        DBConnection.openConnection();
        String sql1 = "SELECT Count(*) FROM appointments;";
        Statement st1 = DBConnection.connection.createStatement();
        ResultSet rs1 = st1.executeQuery(sql1);
        rs1.next();
        if (rs1.getInt(1) == 0) {
            DBConnection.closeConnection();
            return 1;
        }
        String sql2 = "SELECT MAX(Appointment_ID) FROM appointments;";
        Statement st2 = DBConnection.connection.createStatement();
        ResultSet rs2 = st2.executeQuery(sql2);
        rs2.next();
        int result = rs2.getInt(1) + 1;
        DBConnection.closeConnection();
        return result;
    }
    public static void addUpdateAppointment(Appointment appointment, boolean isAnUpdate) {
        String sql;
        if (isAnUpdate) {
            sql = String.format("UPDATE appointments\n" +
                            "SET `Title` = '%s', `Description` = '%s', Location = '%s', `Type` = '%s', `Start` = %s, `End` = %s, Last_Update = NOW(), Last_Updated_By = '%s', Customer_ID = %s, User_ID = %s, Contact_ID = %s\n" +
                            "WHERE Appointment_ID = 3",
                    appointment.getTitle(),
                    appointment.getDescription(),
                    appointment.getLocation(),
                    appointment.getType(),
                    Timestamp.valueOf(appointment.getStart()),
                    Timestamp.valueOf(appointment.getEnd()),
                    UserData.username,
                    appointment.getCustomerId(),
                    appointment.getUserId(),
                    appointment.getContactId()
                    );
        }
        else {
            sql = String.format("INSERT INTO appointments (Appointment_ID, `Title`, `Description`, Location, `Type`, `Start`, `End`, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)\n" +
                    "VALUES (%d, '%s', '%s', '%s', '%s', %s, %s, NOW(), '%s', NOW(), '%s', %d, %d, %d)",
                    appointment.getAppointmentId(),
                    appointment.getTitle(),
                    appointment.getDescription(),
                    appointment.getLocation(),
                    appointment.getType(),
                    Timestamp.valueOf(appointment.getStart()),
                    Timestamp.valueOf(appointment.getEnd()),
                    UserData.username,
                    UserData.username,
                    appointment.getCustomerId(),
                    appointment.getUserId(),
                    appointment.getContactId()
                    );
        }
        System.out.println(sql);
    }
    public static void deleteAppointment(Appointment appointment) throws SQLException {
        String sql = "DELETE FROM appointments\n" +
                "WHERE Appointment_ID = " + String.valueOf(appointment.getAppointmentId());
        DBConnection.openConnection();
        Statement statement = DBConnection.connection.createStatement();
        statement.executeUpdate(sql);
        DBConnection.closeConnection();
    }
}
