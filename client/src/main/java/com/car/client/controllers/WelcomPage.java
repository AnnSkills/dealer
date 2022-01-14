package com.car.client.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.car.client.ClientInf;
import com.car.client.information.Info;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class WelcomPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button enterButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Label dataLost;

    @FXML
    private Label isAccountExist;

    @FXML
    void toEnter(ActionEvent event) throws IOException, ClassNotFoundException {
            if (login.getText().equals("") || password.getText().equals("")) {
                dataLost.setText("Введены не все данные");
            }
            Info info = Info.getInstance();
            info.getClientInf().writeInt(1);
            info.getClientInf().writeLine(login.getText());
        info.getClientInf().writeLine(password.getText());
        User user = (User) info.getClientInf().getObject();
            if (user.getIdUser() == 0) {
                isAccountExist.setText("Такого пользователя не существует");
            } else {
                info.setUser(user);
                enterButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();
                if (Objects.equals(user.getRole(), "Admin")) {
                    fxmlLoader.setLocation(getClass().getResource("/fxml/admin-page.fxml"));
                } else {
                    fxmlLoader.setLocation(getClass().getResource("/fxml/user-page.fxml"));
                }
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

            dataLost.setText("");
    }

    @FXML
    void toRegistration(ActionEvent event) {
        registrationButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/registration-page.fxml"));
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
    public void initialize() throws UnknownHostException {
        InetAddress address = InetAddress.getByName(null);
        Info info = Info.getInstance();
        if(info.getClientInf() == null){
            ClientInf clientInf = new ClientInf(address,8020);
            info.setClientInf(clientInf);
            System.out.println("Connected to Server");
        }

    }
   /* @FXML
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert enterButton != null : "fx:id=\"enterButton\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert registrationButton != null : "fx:id=\"registrationButton\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert dataLost != null : "fx:id=\"dataLost\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert isAccountExist != null : "fx:id=\"isAccountExist\" was not injected: check your FXML file 'welcom-page.fxml'.";

    }*/
}

/*


    @FXML
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert enterButton != null : "fx:id=\"enterButton\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert registrationButton != null : "fx:id=\"registrationButton\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert dataLost != null : "fx:id=\"dataLost\" was not injected: check your FXML file 'welcom-page.fxml'.";
        assert isAccountExist != null : "fx:id=\"isAccountExist\" was not injected: check your FXML file 'welcom-page.fxml'.";

    }*/

