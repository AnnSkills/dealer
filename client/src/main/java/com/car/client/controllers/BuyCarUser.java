package com.car.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.car.client.information.Info;
import com.car.client.information.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Car;
import model.OrderCar;
import model.User;

public class BuyCarUser {

    @FXML
    public Label toYourOrders;
    @FXML
    public Button buttonGo;

    @FXML
    public Label coolLabel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TableView<Car> catalogTable;

    @FXML
    private ScrollBar scrollTable;

    @FXML
    private TextField idInput;

    @FXML
    private Button buyCarUser;

    @FXML
    private Button backButton;

    @FXML
    void toBuyCarUser(ActionEvent event) throws IOException, ClassNotFoundException {
        if(nowId!=0){
            Info info = Info.getInstance();
            OrderCar orderCar = new OrderCar();
            orderCar.setIdCar(nowId);
            orderCar.setIdClient(info.getUser().getIdClient());
            orderCar.setOrderDate(LocalDateTime.now());

            info.getClientInf().writeInt(5);
            info.getClientInf().writeObject("OrderCar");
            info.getClientInf().writeObject(orderCar);
            int id = (int) info.getClientInf().getObject();

            coolLabel.setText("Заказ на авто " + nowId + " оформлен!!!");
            toYourOrders.setText("Просмотреть заказы?");
            buttonGo.setVisible(true);

        }
    }

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
    void toReadIdInput(ActionEvent event) {

    }
private int nowId = 0;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        idColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("idCar"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("vinCar"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("modelCar"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("colorCar"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("priceCar"));

        refreshTable();

        TableView.TableViewSelectionModel<Car> selectionModel = catalogTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Car>() {

            @Override
            public void changed(ObservableValue<? extends Car> observableValue, Car shop, Car t1) {
                if (t1 != null) {
                    nowId = t1.getIdCar();
                }
            }
        });
    }

    private void refreshTable() throws IOException, ClassNotFoundException {

        Info info = Info.getInstance();
        info.getClientInf().writeInt(2);
        info.getClientInf().writeObject("Car");
        ArrayList<Car> cars = (ArrayList<Car>) info.getClientInf().getObject();

        ObservableList<Car> carObservableList = FXCollections.observableList(cars);
        catalogTable.setItems(carObservableList);

        nowId = 0;
    }

    @FXML
    public void toGoUserOrders(ActionEvent actionEvent) {
        buttonGo.getScene().getWindow().hide();
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
}
