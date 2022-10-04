package com.example.c195task1.controller;

import com.example.c195task1.model.*;
import javafx.collections.FXCollections;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerAddUpdate implements Initializable {
    public Label lblAddUpdateCust;
    public Label lblCustId;
    public Label lblCustName;
    public Label lblCustAddress;
    public Label lblCustPostCode;
    public Label lblCustPhone;
    public Label lblCustCountry;
    public Label lblCustDivision;
    public Button btnCustSave;
    public Button btnCustCancel;
    public TextField txtCustId;
    public TextField txtCustName;
    public TextField txtCustAddress;
    public TextField txtCustPostCode;
    public TextField txtCustPhone;
    public ComboBox cmbCountry;
    public ComboBox cmbDivision;
    private boolean isUpdateMode;
    private ObservableList<Country> allCountries;
    private ObservableList<FirstLevelDivision> allFLDs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allCountries = CountryDAO.getAllCountries();
            allFLDs = FirstLevelDivisionDAO.getAllFLDs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cmbCountry.setItems(allCountries);
    }
    public void onBtnCustSave(ActionEvent actionEvent) throws IOException, SQLException {
        if (!validateData()) {
            return;
        }
        FirstLevelDivision fld = (FirstLevelDivision) cmbDivision.getSelectionModel().getSelectedItem();
        Customer customer = new Customer(
                Integer.parseInt(txtCustId.getText()),
                txtCustName.getText(),
                txtCustAddress.getText(),
                txtCustPostCode.getText(),
                txtCustPhone.getText(),
                fld.getDivisionId(),
                fld.getDivision(), fld.getCountryId(), "");
        CustomerDAO.addUpdateCustomer(customer, isUpdateMode);
        backToMainScreen();
    }

    public void onBtnCustCancel(ActionEvent actionEvent) throws IOException {
        backToMainScreen();
    }
    public void setModeAdd() throws SQLException {
        isUpdateMode = false;
        txtCustId.setText(String.valueOf(CustomerDAO.generateCustomerId()));
        // cmbCountry.setPromptText("Select a country");
    }
    public void setModeUpdate(Customer customer) {
        isUpdateMode = true;
        txtCustId.setText(String.valueOf(customer.getId()));
        txtCustName.setText(customer.getName());
        txtCustAddress.setText(customer.getAddress());
        txtCustPostCode.setText(customer.getPostCode());
        txtCustPhone.setText(customer.getPhone());
        int countryId = customer.getCountryId();
        // set the country
        for (Country c: allCountries) {
            if (c.getCountryId() == countryId) {
                cmbCountry.getSelectionModel().select(c);
            }
        }
        //filter the divisions
        ObservableList<FirstLevelDivision> newFLDList = allFLDs.stream()
                .filter(fld -> fld.getCountryId() == countryId)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        cmbDivision.setItems(newFLDList);
        // set the division
        int fldId = customer.getDivisionId();
        for (FirstLevelDivision f: newFLDList) {
            if (f.getDivisionId() == fldId) {
                cmbDivision.getSelectionModel().select(f);
            }
        }
    }

    public void onCmbCountry(ActionEvent actionEvent) {
        int countryId = ((Country) cmbCountry.getSelectionModel().getSelectedItem()).getCountryId();
        cmbDivision.getSelectionModel().clearSelection();
        ObservableList<FirstLevelDivision> newFLDList = allFLDs.stream()
                .filter(fld -> fld.getCountryId() == countryId)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        cmbDivision.setItems(newFLDList);
    }
    private boolean validateData() {
        if (txtCustName.getText().isEmpty()
         || txtCustAddress.getText().isEmpty()
         || txtCustPhone.getText().isEmpty()
         || txtCustPostCode.getText().isEmpty()) {
            return false;
        }
        if (cmbCountry.getSelectionModel().getSelectedItem() == null ||
            cmbDivision.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        return true;
    }
    private void backToMainScreen() throws IOException {
        Stage stage = (Stage) btnCustSave.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/MainScreen.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
