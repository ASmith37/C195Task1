package com.example.c195task1.controller;

import com.example.c195task1.model.Appointment;
import com.example.c195task1.model.AppointmentDAO;
import com.example.c195task1.model.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AppointmentAddUpdate {
    public Label lblAddUpdateAppt;
    public Label lblApptId;
    public Label lblApptTitle;
    public Label lblApptDescription;
    public Label lblApptLocation;
    public Label lblApptContact;
    public Label lblApptType;
    public Label lblApptStartDateTime;
    public Label lblApptEndDateTime;
    public Label lblApptCustId;
    public Label lblApptUserId;
    public TextField txtApptId;
    public TextField txtApptTitle;
    public TextField txtApptDescription;
    public TextField txtApptLocation;
    public TextField txtApptContact;
    public TextField txtApptType;
    public TextField txtApptStartDateTime;
    public TextField txtApptEndDateTime;
    public TextField txtApptCustId;
    public TextField txtApptUserId;
    public Button btnApptSave;
    public Button btnApptCancel;
    private boolean isUpdateMode;

    public void onBtnApptSave(ActionEvent actionEvent) throws IOException {
        backToMainScreen();
    }

    public void onBtnApptCancel(ActionEvent actionEvent) throws IOException {
        backToMainScreen();
    }
    public void setModeAdd() throws SQLException {
        isUpdateMode = false;
        lblAddUpdateAppt.setText("Add Appointment");
        txtApptId.setText(String.valueOf(AppointmentDAO.generateAppointmentId()));
    }
    public void setModeModify(Appointment appointment) {
        isUpdateMode = true;
        lblAddUpdateAppt.setText("Modify Appointment");
        txtApptId.setText(String.valueOf(appointment.getAppointmentId()));
        txtApptTitle.setText(appointment.getTitle());
        txtApptDescription.setText(appointment.getDescription());
        txtApptContact.setText(appointment.getContact());
        txtApptLocation.setText(appointment.getLocation());
        txtApptType.setText(appointment.getType());
        // start
        // end
        txtApptCustId.setText(String.valueOf(appointment.getCustomerId()));
        txtApptUserId.setText(String.valueOf(appointment.getUserId()));

    }
    private void backToMainScreen() throws IOException {
        Stage stage = (Stage) btnApptSave.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/MainScreen.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
