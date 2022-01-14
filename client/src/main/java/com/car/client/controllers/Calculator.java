package com.car.client.controllers;

import com.car.client.information.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static java.lang.Integer.valueOf;

public class Calculator {

    @FXML
    public Label notFilledLabel;

    private Integer in;
    private Integer proc;


    @FXML
    private TextField priceInput;

    @FXML
    private TextField procentInput;

    @FXML
    private TextField totalPrice;

    @FXML
    private Button backButton;

    @FXML
    void toGoBack(ActionEvent event) {
        Info info = Info.getInstance();
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        if (Objects.equals(info.getUser().getRole(), "User")) {
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

    @FXML
    void initialize() {

    }
    private boolean isFilled() {
        boolean answer = true;
        if (priceInput.getText().equals("")||procentInput.getText().equals("")) {
            notFilledLabel.setText("Заполните поля!");
            answer = false;
        }
        else {
            notFilledLabel.setText("");
        }
        return answer;
    }

    @FXML
    public void toCountPrice(ActionEvent actionEvent) {
        if(isFilled()) {
            in = Integer.valueOf(priceInput.getText());
            proc = Integer.valueOf(procentInput.getText());
            Integer totally = 0;
            Integer procentFrom = (in * proc/ 100 );
            totally = in+ procentFrom;
            totalPrice.setText(String.valueOf(totally));
        }
    }
}
