package com.car.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.car.client.information.Info;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountButton;

    @FXML
    private Button catalogButton;

    @FXML
    private Button orderSpisokButton;

    @FXML
    private Button commentButtom;

    @FXML
    private Button creditButton;

    @FXML
    private Button buyCar;

    @FXML
    private Button orderInsuranceButton;

    @FXML
    private Button contactsButton;

    @FXML
    private Button backButton;

    @FXML
    void toBuyCar(ActionEvent event) {
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/buyCar-user.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toGoAccount(ActionEvent event) {
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/personal-account.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toGoBack(ActionEvent event) {

        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Info info = Info.getInstance();
        info.setUser(null);
      //  data.setEditUser(null);
        fxmlLoader.setLocation(getClass().getResource("/fxml/welcom-page.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toGoCatalog(ActionEvent event) {
        catalogButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/userCatalog-page.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void toOrderInsurance(ActionEvent event) {
        orderInsuranceButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/addInsurance.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toShowClientOrders(ActionEvent event) {
        orderSpisokButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/oneClientOrders.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void toShowContacts(ActionEvent event) {
        contactsButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/contacts-page.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toShowCounting(ActionEvent event) {
        creditButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/calculator.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toWriteComment(ActionEvent event) {
        commentButtom.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/makeComment-user.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

//    @FXML
//    void initialize() {
//        assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert catalogButton != null : "fx:id=\"catalogButton\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert orderSpisokButton != null : "fx:id=\"orderSpisokButton\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert commentButtom != null : "fx:id=\"commentButtom\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert creditButton != null : "fx:id=\"creditButton\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert buyCar != null : "fx:id=\"buyCar\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert orderInsuranceButton != null : "fx:id=\"orderInsuranceButton\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert contactsButton != null : "fx:id=\"contactsButton\" was not injected: check your FXML file 'user-page.fxml'.";
//        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'user-page.fxml'.";
//
//    }
}
