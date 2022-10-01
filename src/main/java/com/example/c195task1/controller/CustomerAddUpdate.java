package com.example.c195task1.controller;

import com.example.c195task1.model.Country;
import com.example.c195task1.model.CountryDAO;
import com.example.c195task1.model.FirstLevelDivision;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private boolean isUpdateMode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Country> allCountries = CountryDAO.getAllCountries();
            ObservableList<FirstLevelDivision> allFLDs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void onBtnCustSave(ActionEvent actionEvent) {
    }

    public void onBtnCustCancel(ActionEvent actionEvent) {
    }
    public void setModeAdd() {
        isUpdateMode = false;
    }
    public void setModeUpdate() {
        isUpdateMode = true;
    }

}
