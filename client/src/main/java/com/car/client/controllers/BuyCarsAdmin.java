package com.car.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.car.client.information.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Car;

public class BuyCarsAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labl;

    @FXML
    private TextField vinInput;

    @FXML
    private TextField modelInput;

    @FXML
    private TextField colorInput;

    @FXML
    private TextField priceInput;

    @FXML
    private Button buyAdminCarButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> allModels;

    @FXML
    void toBuyCarAdmin(ActionEvent event) throws IOException, ClassNotFoundException {
        if (isFilled()) {
            Car car = new Car();
            car.setVinCar(vinInput.getText());

            car.setModelCar(allModels.getValue());
            car.setColorCar(colorInput.getText());
            car.setPriceCar(Integer.parseInt(priceInput.getText()));


            Info info = Info.getInstance();
            info.getClientInf().writeInt(5);
            info.getClientInf().writeObject("Car");
            info.getClientInf().writeObject(car);
            int id =(int) info.getClientInf().getObject();



            buyAdminCarButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            if (Objects.equals(info.getUser().getRole(), "Admin")) {
                fxmlLoader.setLocation(getClass().getResource("/fxml/adminCatalog-page.fxml"));
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

    }
    private boolean isFilled() {
        boolean answer = true;
        if (vinInput.getText().equals("")||colorInput.getText().equals("")||priceInput.getText().equals("")) {
            labl.setText("Заполните все поля!");
            answer = false;
        }
        if(allModels.getValue().equals("марка")){
            labl.setText("Выберите марку авто!");
            answer = false;
        }
        else {
            labl.setText("");
        }
        return answer;
    }

    @FXML
    void toGoBack(ActionEvent event) {
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/admin-page.fxml"));
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
    void toReadColor(ActionEvent event) {

    }

    @FXML
    void toReadModel(ActionEvent event) {

    }

    @FXML
    void toReadPrice(ActionEvent event) {

    }

    @FXML
    void toReadVIN(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList("марка", "BMW", "Reno", "Kia");
        allModels.setItems(langs);
        allModels.setValue("марка");
    }
}
