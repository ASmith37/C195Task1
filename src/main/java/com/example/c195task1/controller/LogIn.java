package com.example.c195task1.controller;

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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblLogInError.setVisible(false);
        lblLogInErrorMsg.setVisible(false);
    }
    private void showAlert(String title, String message) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle(title);
        error.setHeaderText(title);
        error.setContentText(message);
        error.showAndWait();
    }
    public void onBtnLogIn(ActionEvent actionEvent) throws SQLException, IOException {
        User user = UserDAO.checkCredentials(txtUsername.getText(), txtPassword.getText());
        if (user != null) {

            Appointment appointment = AppointmentDAO.checkForAppointmentSoon();
            if (appointment != null) {
                showAlert("Upcoming appointment", String.format("There is an upcoming appointment.\nAppointment ID: %d\nDate: %s\nTime: %s",
                        appointment.getAppointmentId(),
                        appointment.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ,
                        appointment.getStart().format(DateTimeFormatter.ofPattern("h:mm a"))));
            }
            else {
                showAlert("No upcoming appointments", "There are no appointments starting soon");
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
            lblLogInError.setVisible(true);
            lblLogInErrorMsg.setVisible(true);
        }
    }
}
