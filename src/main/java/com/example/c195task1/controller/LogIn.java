package com.example.c195task1.controller;

import com.example.c195task1.helper.Logger;
import com.example.c195task1.helper.UserData;
import com.example.c195task1.model.Appointment;
import com.example.c195task1.model.AppointmentDAO;
import com.example.c195task1.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.c195task1.model.UserDAO;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** Implements the login screen **/
public class LogIn implements Initializable {
    public Label lblLogIn;
    public Label lblZoneId;
    public Label lblZoneIdOut;
    public Label lblUsername;
    public TextField txtUsername;
    public Label lblPassword;
    public PasswordField txtPassword;
    public Label lblLogInError;
    public Label lblLogInErrorMsg;
    public Button btnLogIn;
    ResourceBundle rb = ResourceBundle.getBundle("/com/example/c195task1/Translations", Locale.getDefault());

    /** Called when the login screen is initialized.
     * Prepares the screen, and translates it to the correct language**/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblLogInError.setVisible(false);
        lblLogInErrorMsg.setVisible(false);

        if (Locale.getDefault().getLanguage().equals("fr") ||
        Locale.getDefault().getLanguage().equals("en")) {
            lblLogIn.setText(rb.getString("LogIn"));
            btnLogIn.setText(rb.getString("LogIn"));
            lblZoneId.setText(rb.getString("ZoneID"));
            lblZoneIdOut.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
            //lblZoneIdOut.setText(TimeZone.getDefault().getDisplayName());
            lblUsername.setText(rb.getString("Username"));
            lblPassword.setText(rb.getString("Password"));
            lblLogInError.setText(rb.getString("ErrorLabel"));
            lblLogInErrorMsg.setText((rb.getString("ErrorMessage")));
        }
    }
    /** Shows and informational message to the user.
     * This is used to display information about upcoming appointments
     * @param title The title of the message box
     * @param message The message to display to the user. **/
    private void showAlert(String title, String message) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle(title);
        error.setHeaderText(title);
        error.setContentText(message);
        error.showAndWait();
    }
    /** Functionality for the log in button
     * Check if the user is valid, and displays information about any upcoming appointments.
     * @param actionEvent The ActionEvent of the button press **/
    public void onBtnLogIn(ActionEvent actionEvent) throws SQLException, IOException {
        User user = UserDAO.checkCredentials(txtUsername.getText(), txtPassword.getText());
        if (user != null) {
            Logger.log(String.format("Login for user %s was successful", user.getUserName()));
            Appointment appointment = AppointmentDAO.checkForAppointmentSoon();
            if (appointment != null) {
                String message1 = "Upcoming appointment";
                String message2 = "There is an upcoming appointment.\nAppointment ID: %d\nDate: %s\nTime: %s";
                message1 = rb.getString("Appointment1");
                message2 = rb.getString("Appointment2");

                showAlert(message1, String.format(message2,
                        appointment.getAppointmentId(),
                        appointment.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ,
                        appointment.getStart().format(DateTimeFormatter.ofPattern("h:mm a"))));
            }
            else {
                String message1 = "No upcoming appointments";
                String message2 = "There are no appointments starting soon";
                message1 = rb.getString("NoAppointment1");
                message2 = rb.getString("NoAppointment2");
                showAlert(message1, message2);
            }

            UserData.username = user.getUserName();
            UserData.userId = user.getUserId();
            // load next screen
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml")); // fails
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("/c195task1/MainScreen.fxml")); // fails
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("c195task1/MainScreen.fxml")); // fails
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/c195task1/MainScreen.fxml")); // fails
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/c195task1/MainScreen.fxml")); // fails
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/MainScreen.fxml")); // javafx.fxml.LoadException
            Parent root = loader.load();

            stage.setTitle("Main Screen");
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            Logger.log(String.format("Login for user %s was unsuccessful", txtUsername.getText()));
            lblLogInError.setVisible(true);
            lblLogInErrorMsg.setVisible(true);
        }
    }
}
