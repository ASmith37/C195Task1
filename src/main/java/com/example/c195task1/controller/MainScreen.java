package com.example.c195task1.controller;

import com.example.c195task1.model.Appointment;
import com.example.c195task1.model.AppointmentDAO;
import com.example.c195task1.model.Customer;
import com.example.c195task1.model.CustomerDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
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
    private ObservableList<Customer> allCustomers;
    private ObservableList<Appointment> allAppointments;

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

    public void onBtnAddAppt(ActionEvent actionEvent) {
    }

    public void onBtnEditAppt(ActionEvent actionEvent) {
    }

    public void OnBtnDeleteAppt(ActionEvent actionEvent) {
    }

    public void onBtnAddCust(ActionEvent actionEvent) {
    }

    public void onBtnEditCust(ActionEvent actionEvent) {
    }

    public void onBtnDeleteCust(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allCustomers = CustomerDAO.getAllCustomers();
            tblCustomers.setItems(allCustomers);
            colCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

            allAppointments = AppointmentDAO.getAllAppointments();
            tblAppointments.setItems(allAppointments);
            colApptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
