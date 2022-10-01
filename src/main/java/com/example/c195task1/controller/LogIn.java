package com.example.c195task1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.c195task1.model.UserDAO;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public void onBtnLogIn(ActionEvent actionEvent) throws SQLException, IOException {
        boolean validLogin = UserDAO.checkCredentials(txtUsername.getText(), txtPassword.getText());
        if (validLogin) {
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
