package com.car.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.car.client.information.Info;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Car;

public class UserCatalogPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, Integer> idColumn;

    @FXML
    private TableColumn<Car, String> vinColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, String> colorColumn;

    @FXML
    private TableColumn<Car, Integer> priceColumn;
    @FXML
    private ComboBox<String> combBxPrice;

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

    private int nowId = 0;
    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        idColumn.setCellValueFactory(new PropertyValueFactory<Car,Integer>("idCar"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("vinCar"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("modelCar"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("colorCar"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car,Integer>("priceCar"));

        refreshTable();

        ObservableList<String> observableList = FXCollections.observableArrayList("марка", "BMW", "Reno", "Kia");

        combBxPrice.setValue("марка");
        combBxPrice.setItems(observableList);

        combBxPrice.setOnAction(event -> {
            try {
                filterByModels(combBxPrice.getValue());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void filterByModels(String value) throws IOException, ClassNotFoundException {
        if (!value.equals("марка")) {
            Info info = Info.getInstance();
            info.getClientInf().writeInt(9);
            info.getClientInf().writeObject("Car");
            info.getClientInf().writeObject(value);

            ArrayList<Car> list = (ArrayList<Car>) info.getClientInf().getObject();
            ObservableList<Car> arrayList = FXCollections.observableArrayList(list);

            carTable.setItems(arrayList);
            nowId = 0;
        } else {
            refreshTable();
        }
    }

    private void refreshTable() throws IOException, ClassNotFoundException {
        Info info = Info.getInstance();
        info.getClientInf().writeInt(2);
        info.getClientInf().writeObject("Car");
        ArrayList<Car> listOne = (ArrayList<Car>) info.getClientInf().getObject();


        ObservableList<Car> arrayList = FXCollections.observableArrayList(listOne);

        idColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("idCar"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("vinCar"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("modelCar"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("colorCar"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("priceCar"));

        carTable.setItems(arrayList);
        nowId=0;

    }
}
