package com.example.c195task1.controller;

import com.example.c195task1.model.*;
import javafx.collections.FXCollections;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/** This class controls the Customer screen.
 * It is used to add and update customer records **/
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

    /** This populates data inside the controller and populates the list of countries.
     * It is called when the controller is being initialized **/
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
    /** Displays error messages to the user.
     * @param message The error message to display **/
    private void showMessage(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText("Invalid Data");
        error.setContentText(message);
        error.showAndWait();
    }
    /** This is called by the save button.
     * Check if the data is valid. If so, add or update the customer records.
     * @param actionEvent The ActionEvent of the button press
     * **/
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

    /** Return the program to the main screen.
     * This is called when the cancel button is pressed.
     * @param actionEvent The ActionEvent of the button press
     * **/
    public void onBtnCustCancel(ActionEvent actionEvent) throws IOException {
        backToMainScreen();
    }
    /** Sets the screen in a manner suitable for adding a new customer **/
    public void setModeAdd() throws SQLException {
        isUpdateMode = false;
        lblAddUpdateCust.setText("Add Customer");
        txtCustId.setText(String.valueOf(CustomerDAO.generateCustomerId()));
        // cmbCountry.setPromptText("Select a country");
    }
    /** Sets the screen in a manner suitable for updating a customer.
     * This includes loading the existing customer data into the form.
     * @param customer The Customer object that will be modified.
     * **/
    public void setModeUpdate(Customer customer) {
        isUpdateMode = true;
        lblAddUpdateCust.setText("Modify Customer");
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

    /** Called when the country combobox is modified.
     * This then updates the list of divisions with a list appropriate to the selected country **/
    public void onCmbCountry(ActionEvent actionEvent) {
        int countryId = ((Country) cmbCountry.getSelectionModel().getSelectedItem()).getCountryId();
        cmbDivision.getSelectionModel().clearSelection();
        ObservableList<FirstLevelDivision> newFLDList = allFLDs.stream()
                .filter(fld -> fld.getCountryId() == countryId)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        cmbDivision.setItems(newFLDList);
    }
    /** Check if the data in the form is valid
     * @return a boolean indicating if the data is valid **/
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
    /** Return the program to the main screen **/
    private void backToMainScreen() throws IOException {
        Stage stage = (Stage) btnCustSave.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/c195task1/MainScreen.fxml")); // javafx.fxml.LoadException
        Parent root = loader.load();
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
