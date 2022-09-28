package com.example.c195task1.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomerAddUpdate {
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
