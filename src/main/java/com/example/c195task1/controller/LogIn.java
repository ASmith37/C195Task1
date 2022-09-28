package com.example.c195task1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onBtnLogIn(ActionEvent actionEvent) {
    }
}
