package com.example.c195task1.controller;

import com.example.c195task1.model.Appointment;
import com.example.c195task1.model.AppointmentDAO;
import com.example.c195task1.model.Customer;
import com.example.c195task1.model.CustomerDAO;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/** Implements the main screen of the application **/
public class MainScreen implements Initializable {
    public Button btnDeleteAppt;
    public Button btnEditAppt;
    public Button btnAddAppt;
    public RadioButton rdApptWeek;
    public RadioButton rdApptMonth;
    public RadioButton rdApptAll;
    public Button btnRptTotalAppt;
    public Button btnRptSchedule;
    public Button btnRptOther;
    public Button btnDeleteCust;
    public Button btnEditCust;
    public Button btnAddCust;
    public TableView tblCustomers;
    public TableView tblAppointments;
    public TableColumn colCustId;
    public TableColumn colCustName;
    public TableColumn colCustAddress;
    public TableColumn colCustPostCode;
    public TableColumn colCustPhone;
    public TableColumn colCustCountry;
    public TableColumn colCustDivision;
    public TableColumn colApptId;
    public TableColumn colApptTitle;
    public TableColumn colApptDesc;
    public TableColumn colApptLocation;
    public TableColumn colApptType;
    public TableColumn colApptStart;
    public TableColumn colApptEnd;
    public TableColumn colApptCustId;
    public TableColumn colApptUserId;
    public TableColumn colApptContact;
    private ObservableList<Customer> allCustomers;
    private FilteredList<Appointment> allAppointments;
    /** Shows a message to the user asking for confirmation
     * @param message The message to display to the user
     * @return boolean indicating if the user confirmed **/
    private boolean showConfirmationAlert(String message) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Please confirm");
        confirmAlert.setHeaderText("Please confirm");
        confirmAlert.setContentText(message);
        Optional<ButtonType> result = confirmAlert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /** Functionality when the week radio button is pressed.
     * Uses a lambda to implement the logic to determine if an appointment is within the week
     * @param actionEvent The ActionEvent of the button press **/
    public void onRdApptWeek(ActionEvent actionEvent) {
        LocalDateTime windowStart = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(0, 0));
        LocalDateTime windowEnd = windowStart.plusWeeks(1);
        allAppointments.setPredicate(appt -> {
            return appt.getStart().isAfter(windowStart) && appt.getEnd().isBefore(windowEnd);
        });
    }

    /** Functionality when the month radio button is pressed.
     * Uses a lambda to implement the logic to determine if an appointment is within the month
     * @param actionEvent The ActionEvent of the button press **/
    public void onRdApptMonth(ActionEvent actionEvent) {
        LocalDateTime windowStart = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(0, 0));
        LocalDateTime windowEnd = windowStart.plusMonths(1);
        allAppointments.setPredicate(appt -> {
            return appt.getStart().isAfter(windowStart) && appt.getEnd().isBefore(windowEnd);
        });
    }

    /** Functionality when the all radio button is pressed.
     * Shows all the appointments
     * @param actionEvent The ActionEvent of the button press **/
    public void onRdApptAll(ActionEvent actionEvent) {
        allAppointments.setPredicate(appt -> {return true;});
    }

    /** Functionality of the total appointments report button
     * Loads the reports for and tells it to load this report
     * @param actionEvent The ActionEvent of the button press **/
    public void onBtnRptTotalAppt(ActionEvent actionEvent) throws IOException, SQLException {
        Stage stage = (Stage) btnRptTotalAppt.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/Report.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();

        Report report = loader.getController();
        report.setModeReport1();

        stage.setTitle("Report 1");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Functionality of the schedule report button
     * Loads the reports for and tells it to load this report
     * @param actionEvent The ActionEvent of the button press **/
    public void onBtnRptSchedule(ActionEvent actionEvent) throws SQLException, IOException {
        Stage stage = (Stage) btnRptTotalAppt.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/Report.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();

        Report report = loader.getController();
        report.setModeReport2();

        stage.setTitle("Report 2");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Functionality of the 3rd report button
     * Loads the reports for and tells it to load this report
     * @param actionEvent The ActionEvent of the button press **/
    public void onBtnRptOther(ActionEvent actionEvent) throws IOException, SQLException {
        Stage stage = (Stage) btnRptTotalAppt.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/Report.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();

        Report report = loader.getController();
        report.setModeReport3();

        stage.setTitle("Report 3");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Functionality for the add appointment button
     * Loads the appointment form and selects the add functionality.
     * @param actionEvent The ActionEvent of the button press **/
    public void onBtnAddAppt(ActionEvent actionEvent) throws IOException, SQLException {
        Stage stage = (Stage) btnAddAppt.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/AppointmentAddUpdate.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();

        AppointmentAddUpdate appointmentScreen = loader.getController();
        appointmentScreen.setModeAdd();

        stage.setTitle("Add Appointment");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Functionality for the edit appointment button
     * Loads the appointment form, selects the edit functionality, and passes in the selected appointment.
     * @param actionEvent The ActionEvent of the button press **/
    public void onBtnEditAppt(ActionEvent actionEvent) throws IOException {
        Appointment appointment = (Appointment) tblAppointments.getSelectionModel().getSelectedItem();
        if (appointment != null) {
            Stage stage = (Stage) btnAddAppt.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/AppointmentAddUpdate.fxml")); // javafx.fxml.LoadException
            Parent root = loader.load();

            AppointmentAddUpdate appointmentScreen = loader.getController();
            appointmentScreen.setModeModify(appointment);

            stage.setTitle("Edit Appointment");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /** Functionality for the delete appointment button.
     * Deletes the selected appointment.
     * @param actionEvent The ActionEvent of the button press **/
    public void OnBtnDeleteAppt(ActionEvent actionEvent) throws SQLException {
        Appointment appointment = (Appointment) tblAppointments.getSelectionModel().getSelectedItem();
        if (appointment == null) {
            return;
        }
        if (showConfirmationAlert("Are you sure you want to delete this appointment?")) {
            AppointmentDAO.deleteAppointment(appointment);
        }
        refreshAppointments();
    }

    /** Functionality for the add customer button.
     * Loads the customer form and select the add mode.
     * @param actionEvent The ActionEvent of the button press
     * **/
    public void onBtnAddCust(ActionEvent actionEvent) throws IOException, SQLException {
        Stage stage = (Stage) btnAddCust.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/CustomerAddUpdate.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();

        CustomerAddUpdate customerScreen = loader.getController();
        customerScreen.setModeAdd();

        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /** Functionality for the edit customer button.
     * Loads the customer form and select the edit mode.
     * @param actionEvent The ActionEvent of the button press
     * **/
    public void onBtnEditCust(ActionEvent actionEvent) throws IOException {
        Customer customer = (Customer) tblCustomers.getSelectionModel().getSelectedItem();
        if (customer != null) {
            Stage stage = (Stage) btnAddCust.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/CustomerAddUpdate.fxml")); // javafx.fxml.LoadException
            Parent root = loader.load();

            CustomerAddUpdate customerScreen = loader.getController();
            customerScreen.setModeUpdate(customer);

            stage.setTitle("Edit Customer");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /** Functionality for the delete customer button.
     * Deletes the selected customer.
     * @param actionEvent The ActionEvent of the button press **/
    public void onBtnDeleteCust(ActionEvent actionEvent) throws SQLException {
        Customer customer = (Customer) tblCustomers.getSelectionModel().getSelectedItem();
        if (customer == null) {
            return;
        }
        if (showConfirmationAlert("Are you sure you want to delete this customer?")) {
            boolean result = CustomerDAO.deleteCustomer((Customer) tblCustomers.getSelectionModel().getSelectedItem());
            if (!result) {
                showMessage("Delete failed. You must delete all appointments first.");
            }
            else {
                refreshCustomers();
            }
        }
    }
    /** Reloads the list of customers from the database **/
    private void refreshCustomers() throws SQLException {
        allCustomers = CustomerDAO.getAllCustomers();
        tblCustomers.setItems(allCustomers);
        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustPostCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        colCustPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colCustDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
        colCustCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
    }
    /** Reloads the list if appointments from the database **/
    private void refreshAppointments() throws SQLException {
        allAppointments = new FilteredList<>(AppointmentDAO.getAllAppointments());
        tblAppointments.setItems(allAppointments);
        colApptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colApptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colApptDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colApptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colApptContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colApptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colApptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colApptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colApptCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colApptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    /** The method called when the controller is initialized.
     * Loads lists of customers and appointments from the database. **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshCustomers();
            refreshAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        rdApptAll.setSelected(true);
        onRdApptAll(null);
    }
    /** Displays an error message to the user
     * @param message The message to display.
     * **/
    private void showMessage(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText("Error");
        error.setContentText(message);
        error.showAndWait();
    }
}
