package com.car.client.controllers;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Car;

import java.io.IOException;
import java.util.ArrayList;

public class AdminCatalogPage {

        @FXML
        private Button backButton;

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
        private Button editButton;

        @FXML
        private Button deleteCar;

        @FXML
        private TextField vinEdit;

        @FXML
        private TextField modelEdit;

        @FXML
        private TextField colorEdit;

        @FXML
        private TextField priceEdit;
    @FXML
    private Label lableName;

        private int nowId = 0;

        @FXML
        void toDeleteCar(ActionEvent event) throws IOException, ClassNotFoundException {
            if (nowId != 0) {
                Info info = Info.getInstance();
                info.getClientInf().writeInt(6);
                info.getClientInf().writeObject("Car");
                info.getClientInf().writeObject(nowId);

                refreshTable();
            }
        }

        @FXML
        void toEditCar(ActionEvent event) throws IOException, ClassNotFoundException {
            if (nowId != 0) {
                if (isFilled()) {

                    Car car = new Car();
                    car.setIdCar(nowId);
                    car.setVinCar(vinEdit.getText());
                    car.setModelCar(modelEdit.getText());
                    car.setColorCar(colorEdit.getText());
                    car.setPriceCar(Integer.parseInt(priceEdit.getText()));

                    Info info = Info.getInstance();
                    info.getClientInf().writeInt(7);
                    info.getClientInf().writeObject("Car");
                    info.getClientInf().writeObject(car);
                    info.getClientInf().writeObject(nowId);

                    refreshTable();

                    vinEdit.setText("");
                    modelEdit.setText("");
                    colorEdit.setText("");
                    priceEdit.setText("");
                }
            }
        }
    private boolean isFilled() {
        boolean answer = true;
        if (vinEdit.getText().equals("")||modelEdit.getText().equals("")||colorEdit.getText().equals("")||priceEdit.getText().equals("")) {
            lableName.setText("Заполните поле корректно");
            answer = false;
        } else {
            lableName.setText("");
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
    public void initialize() throws IOException, ClassNotFoundException {
        idColumn.setCellValueFactory(new PropertyValueFactory<Car,Integer>("idCar"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("vinCar"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("modelCar"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("colorCar"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car,Integer>("priceCar"));

        refreshTable();

        Info info = Info.getInstance();
        info.getClientInf().writeInt(2);
        info.getClientInf().writeObject("Car");
        ArrayList<Car> list = (ArrayList<Car>) info.getClientInf().getObject();


        TableView.TableViewSelectionModel<Car> selectionModel = catalogTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Car>() {

            @Override
            public void changed(ObservableValue<? extends Car> observableValue, Car car, Car t1) {
                if (t1 != null) {
                    nowId = t1.getIdCar();
                    vinEdit.setText(t1.getVinCar());
                    modelEdit.setText(t1.getModelCar());
                    colorEdit.setText(t1.getColorCar());
                    priceEdit.setText(String.valueOf(t1.getPriceCar()));
                }
            }

        });
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



        catalogTable.setItems(arrayList);
        nowId=0;

    }
}

