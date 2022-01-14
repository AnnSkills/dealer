package com.car.client.controllers;

import com.car.client.information.ClientData;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Car;
import model.Client;
import model.OrderCar;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllCarOrders {
    @FXML
    private TableColumn<Order,String> modelColumn;
    @FXML
    private TableColumn<Order,String> nameColumn;
    @FXML
    private TableColumn<Order,String> surnameColumn;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Order> tableOrders;

    @FXML
    private TableColumn<Order, Integer> idOrderColumn;

    @FXML
    private TableColumn<Order, String> vinColumn;

    @FXML
    private TableColumn<Order, Integer> priceColumn;

    @FXML
    private TableColumn<Order, String> dateColumn;

    @FXML
    private Button backButton;


    private ArrayList<Order> list;
    private DirectoryChooser directoryChooser;
    private int nowidOrder;



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
    void initialize() throws IOException, ClassNotFoundException {
        refreshTable();

        TableView.TableViewSelectionModel<Order> selectionModel = tableOrders.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<Order>() {

            @Override
            public void changed(ObservableValue<? extends Order> observableValue, Order order, Order t1) {
                if (t1 != null) {
                    nowidOrder = t1.getIdOrder();
                }
            }

        });

    }
    private void refreshTable() throws IOException, ClassNotFoundException {
        Info info = Info.getInstance();
        info.getClientInf().writeInt(2);
        info.getClientInf().writeObject("OrderCar");
        ArrayList<OrderCar> listOne = (ArrayList<OrderCar>) info.getClientInf().getObject();
        list = new ArrayList<>();

        for(OrderCar obj:listOne){
            if (info.getUser().getIdClient() != 0) {
                Order order = new Order();
                order.setIdOrder(obj.getIdOrdCar());
                order.setCreated_at(String.valueOf(obj.getOrderDate()));
                info.getClientInf().writeInt(8);
                info.getClientInf().writeObject("Car");
                info.getClientInf().writeObject(obj.getIdCar());
                Car car = (Car) info.getClientInf().getObject();
                order.setModel(car.getModelCar());
                order.setVinCar(car.getVinCar());
                order.setPriceCar(car.getPriceCar());

                info.getClientInf().writeInt(8);
                info.getClientInf().writeObject("Client");
                info.getClientInf().writeObject(obj.getIdClient());
                Client client = (Client) info.getClientInf().getObject();
                order.setName(client.getClientName());
                order.setSurname(client.getClientSurname());

                list.add(order);
            }
        }

        ObservableList<Order> arrayList = FXCollections.observableArrayList(list);

        idOrderColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("idOrder"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("priceCar"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("surname"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("created_at"));


        tableOrders.setItems(arrayList);
        nowidOrder = 0;
    }
}
