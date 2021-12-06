package com.car.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PersonalAccount {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField login;

    @FXML
    private Button backButton;

    @FXML
    void toGoBack(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'personal-account.fxml'.";
        assert surname != null : "fx:id=\"surname\" was not injected: check your FXML file 'personal-account.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'personal-account.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'personal-account.fxml'.";

    }
}
