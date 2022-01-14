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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;

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
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/user-page.fxml"));
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
    void initialize() throws IOException, ClassNotFoundException {

        Info info = Info.getInstance();
        if(info.getUser()!=null){
            login.setText(info.getUser().getLogin());
            info.getClientInf().writeInt(8);
            info.getClientInf().writeObject("Client");
            info.getClientInf().writeObject(info.getUser().getIdClient());
        }
        Client client = (Client) info.getClientInf().getObject();
        name.setText(client.getClientName());
        surname.setText(client.getClientSurname());

    }
}
