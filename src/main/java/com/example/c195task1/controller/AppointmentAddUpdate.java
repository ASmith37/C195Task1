package com.example.c195task1.controller;

import com.example.c195task1.helper.UserData;
import com.example.c195task1.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentAddUpdate implements Initializable {
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
    public TextField txtApptType;
    public TextField txtApptStartDateTime;
    public TextField txtApptEndDateTime;
    public TextField txtApptUserId;
    public Button btnApptSave;
    public Button btnApptCancel;
    public ComboBox cmbApptCustomerId;
    public ComboBox cmbApptContact;
    private boolean isUpdateMode;
    private ObservableList<Customer> allCustomers;
    private ObservableList<Contact> allContacts;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allCustomers = CustomerDAO.getAllCustomers();
            cmbApptCustomerId.setItems(allCustomers);
            allContacts = ContactDAO.getAllContacts();
            cmbApptContact.setItems(allContacts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnApptSave(ActionEvent actionEvent) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        Customer customer = (Customer) cmbApptCustomerId.getSelectionModel().getSelectedItem();
        Contact contact = (Contact) cmbApptContact.getSelectionModel().getSelectedItem();
        Appointment appointment = new Appointment(
                Integer.parseInt(txtApptId.getText()),
                txtApptTitle.getText(),
                txtApptDescription.getText(),
                txtApptLocation.getText(),
                txtApptType.getText(),
                LocalDateTime.parse(txtApptStartDateTime.getText(), dtf),
                LocalDateTime.parse(txtApptEndDateTime.getText(), dtf),
                LocalDateTime.now(),
                UserData.username,
                LocalDateTime.now(),
                UserData.username,
                customer.getId(),
                UserData.userId,
                contact.getId(),
                contact.getName()
        );
    }

    public void onBtnApptCancel(ActionEvent actionEvent) throws IOException {
        backToMainScreen();
    }
    private boolean validateDate() {
        if (
                txtApptId.getText().isEmpty()
                || txtApptTitle.getText().isEmpty()
                || txtApptDescription.getText().isEmpty()
                || txtApptLocation.getText().isEmpty()
                || txtApptType.getText().isEmpty()
                || txtApptStartDateTime.getText().isEmpty()
                || txtApptEndDateTime.getText().isEmpty()
                || cmbApptCustomerId.getSelectionModel().getSelectedItem() == null
        ) {
            return false;
        }
        return true;
    }
    public void setModeAdd() throws SQLException {
        isUpdateMode = false;
        lblAddUpdateAppt.setText("Add Appointment");
        txtApptId.setText(String.valueOf(AppointmentDAO.generateAppointmentId()));
        txtApptUserId.setText(String.valueOf(UserData.userId));
        cmbApptCustomerId.setDisable(false);
    }
    public void setModeModify(Appointment appointment) {
        isUpdateMode = true;
        lblAddUpdateAppt.setText("Modify Appointment");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        txtApptId.setText(String.valueOf(appointment.getAppointmentId()));
        txtApptTitle.setText(appointment.getTitle());
        txtApptDescription.setText(appointment.getDescription());
        txtApptLocation.setText(appointment.getLocation());
        txtApptType.setText(appointment.getType());
        txtApptStartDateTime.setText(appointment.getStart().format(dtf));
        txtApptEndDateTime.setText(appointment.getEnd().format(dtf));
        txtApptUserId.setText(String.valueOf(appointment.getUserId()));
        for (Customer c: allCustomers) {
            if (c.getId() == appointment.getCustomerId()) {
                cmbApptCustomerId.getSelectionModel().select(c);
                break;
            }
        }
        cmbApptCustomerId.setDisable(true);
        for (Contact c: allContacts) {
            if (c.getId() == appointment.getContactId()) {
                cmbApptContact.getSelectionModel().select(c);
                break;
            }
        }

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
