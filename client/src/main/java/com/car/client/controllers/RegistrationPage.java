package com.car.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
import model.Client;
import model.User;

public class RegistrationPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

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
        Info info = Info.getInstance();
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        if (info.getUser()==null){
            fxmlLoader.setLocation(getClass().getResource("/fxml/welcom-page.fxml"));
        }
        else if(Objects.equals(info.getUser().getRole(), "User")){
            fxmlLoader.setLocation(getClass().getResource("/fxml/user-page.fxml"));
        }
        else if(Objects.equals(info.getUser().getRole(), "Admin")){
            fxmlLoader.setLocation(getClass().getResource("/fxml/controlUsers.fxml"));
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

    @FXML
    void torReg(ActionEvent event) throws IOException, ClassNotFoundException {
        if (isFilled()) {
            User user = new User();
            user.setLogin(login.getText());
            user.setPassword(password.getText());
            user.setRole("User");


            Info info = Info.getInstance();
            info.getClientInf().writeInt(5);
            info.getClientInf().writeObject("User");
            info.getClientInf().writeObject(user);
            int id =(int) info.getClientInf().getObject();
            user.setIdUser(id);

            Client client = new Client();
            client.setClientName(name.getText());
            client.setClientSurname(surname.getText());
            client.setIdUser(id);

            info.getClientInf().writeInt(5);
            info.getClientInf().writeObject("Client");
            info.getClientInf().writeObject(client);
            int id2 =(int) info.getClientInf().getObject();
            user.setIdClient(id2);


            info.getClientInf().writeInt(7);
            info.getClientInf().writeObject("User");
            info.getClientInf().writeObject(user);
            info.getClientInf().writeObject(id);
            info.getClientInf().writeObject(id2);


            info.setUser(user);
            regButton.getScene().getWindow().hide();
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

    }
    private boolean isFilled() {
        boolean answer = true;
        if (login.getText().equals("")){
            labelLogin.setText("Заполните поле логин!");
            answer = false;
        }
        if (password.getText().equals("")){
            labelLogin.setText("Заполните поле пароль!");
            answer = false;
        }
        if (name.getText().equals("")){
            labelLogin.setText("Заполните поле имя!");
            answer = false;
        }
        if (surname.getText().equals("")){
            labelLogin.setText("Заполните поле фамилия!");
            answer = false;
        }
        return answer;
    }
}
