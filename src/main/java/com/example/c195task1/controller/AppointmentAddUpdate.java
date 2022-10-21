package com.example.c195task1.controller;

import com.example.c195task1.helper.UserData;
import com.example.c195task1.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/** This class controls the appointments screen. This screen allows for adding and updating appointments **/
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

    /** The initialize method populates the screen's tableviews with data on customers and appointments **/
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

    /** This is called when the Save button is clicked.
     * It checks the validity of all the inputs in the form.
     * If inputs are invalid, it will show a relevant error message.
     * If the input sare valid, it calls the DAO to add or update the appointment.
     * @param actionEvent The ActionEvent of the button press**/
    public void onBtnApptSave(ActionEvent actionEvent) throws IOException, SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        int apptId;
        String title = txtApptTitle.getText();
        String description = txtApptDescription.getText();
        String location = txtApptLocation.getText();
        String type = txtApptType.getText();
        LocalDateTime start, end;
        try {
            apptId = Integer.parseInt(txtApptId.getText());
        }
        catch (NumberFormatException e) {
            showError("Invalid appointment ID");
            return;
        }
        if (title.isEmpty()) {
            showError("You must enter a title");
            return;
        }
        if (description.isEmpty()) {
            showError("You must enter a description");
            return;
        }
        if (location.isEmpty()) {
            showError("You must enter a location");
            return;
        }
        if (type.isEmpty()) {
            showError("You must enter a type");
            return;
        }
        try {
            start = LocalDateTime.parse(txtApptStartDateTime.getText(), dtf);
        }
        catch (DateTimeParseException e) {
            showError("Invalid start time");
            return;
        }
        try {
            end = LocalDateTime.parse(txtApptEndDateTime.getText(), dtf);
        }
        catch (DateTimeParseException e) {
            showError("Invalid end time");
            return;
        }
        Customer customer = (Customer) cmbApptCustomerId.getSelectionModel().getSelectedItem();
        if (customer == null) {
            showError("Invalid customer selection");
            return;
        }
        Contact contact = (Contact) cmbApptContact.getSelectionModel().getSelectedItem();
        if (contact == null) {
            showError("Invalid contact");
            return;
        }
        if (!isValidAppointmentTime(start, end)) {
            showError("That is not a valid appointment time within business hours.");
            return;
        }
        Appointment appointment = new Appointment(
                apptId,
                title,
                description,
                location,
                type,
                start,
                end,
                LocalDateTime.now(),
                UserData.username,
                LocalDateTime.now(),
                UserData.username,
                customer.getId(),
                UserData.userId,
                contact.getId(),
                contact.getName()
        );
        if (AppointmentDAO.isThereOverlappingAppointment(appointment)) {
            showError("There is an overlapping appointment for that customer.");
            return;
        }
        AppointmentDAO.addUpdateAppointment(appointment, isUpdateMode);
        backToMainScreen();
    }

    /** This is called when the cancel button is pressed.
     * It returns the application to the main screen.
     * @param actionEvent The ActionEvent of the button press
     * **/
    public void onBtnApptCancel(ActionEvent actionEvent) throws IOException {
        backToMainScreen();
    }
    /** This checks if the inputs in the form are valid.
     * @return boolean indicating if all fields have valid data **/
    private boolean validateData() {
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        if (!isValidAppointmentTime(LocalDateTime.parse(txtApptStartDateTime.getText(), dtf),
                                    LocalDateTime.parse(txtApptEndDateTime.getText(), dtf))) {
            return false;
        }
        return true;
    }
    /** Sets values appropriate for adding a new appointment.
     * This is called by the main screen when the 'add' button is pressed. **/
    public void setModeAdd() throws SQLException {
        isUpdateMode = false;
        lblAddUpdateAppt.setText("Add Appointment");
        txtApptId.setText(String.valueOf(AppointmentDAO.generateAppointmentId()));
        txtApptUserId.setText(String.valueOf(UserData.userId));
        cmbApptCustomerId.setDisable(false);
    }
    /** Sets values appropriate for modifying and existing appointment.
     * Also loads values of the existing appointment into the form for modification.
     * This is called by the main screen when 'edit' is pressed. **/
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
    /** Exits the appointment screen and goes back to the main screen. **/
    private void backToMainScreen() throws IOException {
        Stage stage = (Stage) btnApptSave.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/MainScreen.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Converts between the computer's DateTime and the date/time of EST, which is the timezone of the business. **/
    public static LocalDateTime localDateTimeToEst(LocalDateTime ldt) {
        return ZonedDateTime.ofInstant(ZonedDateTime.of(ldt, ZoneId.systemDefault()).toInstant(), ZoneId.of("Etc/GMT+5")).toLocalDateTime();
    }
    /** Converts an EST time, the time of the business, to a time local to the computer **/
    public static LocalDateTime estLocalDateTimeToLocal(LocalDateTime est) {
        return ZonedDateTime.ofInstant(ZonedDateTime.of(est, ZoneId.of("Etc/GMT+5")).toInstant(), ZoneId.systemDefault()).toLocalDateTime();
    }
    /** Checks if the appointment time given is valid.
     * @param start The start time of the appointment
     * @param end The end time of the appointment
     * @return boolean indicating if the appointment time is valid. **/
    public static boolean isValidAppointmentTime(LocalDateTime start, LocalDateTime end) {
        LocalDateTime estStart = localDateTimeToEst(start);
        System.out.println(estStart);
        LocalDateTime estEnd = localDateTimeToEst(end);
        System.out.println(estEnd);
        LocalTime openTime = LocalTime.of(8, 0);
        LocalTime closeTime = LocalTime.of(22, 0);
        if (!estStart.toLocalDate().isEqual(estEnd.toLocalDate())) {
            // appointment does not start and end on the same day
            return false;
        }
        if (estStart.isAfter(estEnd) || estStart.isEqual(estEnd)) {
            // invalid start time or appointment length
            return false;
        }
        if (estStart.toLocalTime().isBefore(openTime) ||
                estStart.toLocalTime().isAfter(closeTime) ||
                estEnd.toLocalTime().isBefore(openTime) ||
                estEnd.toLocalTime().isAfter(closeTime)) {
            // Appointment is outside of business hours
            return false;
        }
        return true;
    }
    /** This is used to display an error message to the user.
     * Typically is used to say a form field has invalid data.
     * @param message The message to display to the user in the error message box. **/
    private void showError(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText("Error");
        error.setContentText(message);
        error.showAndWait();
    }
}
