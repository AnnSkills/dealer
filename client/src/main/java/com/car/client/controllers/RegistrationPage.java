package com.car.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private Button backButton;

    @FXML
    private Button regButton;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelName;

    @FXML
    private Label labelSurname;

    @FXML
    void toBack(ActionEvent event) {

    }

    @FXML
    void torReg(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert surname != null : "fx:id=\"surname\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert regButton != null : "fx:id=\"regButton\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert labelLogin != null : "fx:id=\"labelLogin\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert labelPassword != null : "fx:id=\"labelPassword\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert labelName != null : "fx:id=\"labelName\" was not injected: check your FXML file 'registration-page.fxml'.";
        assert labelSurname != null : "fx:id=\"labelSurname\" was not injected: check your FXML file 'registration-page.fxml'.";

    }
}
