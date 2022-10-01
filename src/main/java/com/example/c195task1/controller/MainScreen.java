package com.example.c195task1.controller;

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
    private ObservableList<Customer> allCustomers;

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
