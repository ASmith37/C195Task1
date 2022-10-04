package com.example.c195task1.controller;

import com.example.c195task1.model.Appointment;
import com.example.c195task1.model.AppointmentDAO;
import com.example.c195task1.model.Customer;
import com.example.c195task1.model.CustomerDAO;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;

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
    private ObservableList<Appointment> allAppointments;
    private boolean showConfirmationAlert(String message) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Please confirm");
        confirmAlert.setHeaderText("Please confirm");
        confirmAlert.setContentText(message);
        Optional<ButtonType> result = confirmAlert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void onRdApptWeek(ActionEvent actionEvent) {
    }

    public void onRdApptMonth(ActionEvent actionEvent) {
    }

    public void onRdApptAll(ActionEvent actionEvent) {
    }

    public void onBtnRptTotalAppt(ActionEvent actionEvent) {
    }

    public void onBtnRptSchedule(ActionEvent actionEvent) {
    }

    public void onBtnRptOther(ActionEvent actionEvent) {
    }

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

    public void OnBtnDeleteAppt(ActionEvent actionEvent) {
    }

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

    public void onBtnDeleteCust(ActionEvent actionEvent) throws SQLException {
        if (showConfirmationAlert("Are you sure you want to delete this customer?")) {
            CustomerDAO.deleteCustomer((Customer) tblCustomers.getSelectionModel().getSelectedItem());
            refreshCustomers();
        }
    }
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
    private void refreshAppointments() throws SQLException {
        allAppointments = AppointmentDAO.getAllAppointments();
        tblAppointments.setItems(allAppointments);
        colApptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colApptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colApptDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colApptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colApptLocation.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colApptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colApptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colApptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colApptCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colApptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colApptContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshCustomers();
            refreshAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
