package com.car.client.controllers;

import com.car.client.information.Info;
import com.car.client.information.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Car;
import model.Client;
import model.OrderCar;

import java.io.IOException;
import java.util.ArrayList;

public class Diagram {
    @FXML
    public Button backButton;
    @FXML
    public PieChart carChart;
    @FXML
    public Label caption;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        Info info = Info.getInstance();
        info.getClientInf().writeInt(2);
        info.getClientInf().writeObject("OrderCar");
        ArrayList<OrderCar> listOne = (ArrayList<OrderCar>) info.getClientInf().getObject();
        ArrayList<OrderCar> list = new ArrayList<>();

        String methodOne = "Reno";
        String methodTwo = "BMW";
        String methodThree = "Kia";
        int countOne = 0, countTwo = 0, countThree = 0;
        for (OrderCar obj : listOne) {
            info.getClientInf().writeInt(8);
            info.getClientInf().writeObject("Car");
            info.getClientInf().writeObject(obj.getIdCar());
            Car car = (Car) info.getClientInf().getObject();

            switch (car.getModelCar()) {
                case "Reno" -> {
                    countOne++;
                }
                case "BMW" -> {
                    countTwo++;
                }
                case "Kia" -> {
                    countThree++;
                }
            }
        }

        PieChart.Data slice1 = new PieChart.Data(methodOne, countOne);
        PieChart.Data slice2 = new PieChart.Data(methodTwo, countTwo);
        PieChart.Data slice3 = new PieChart.Data(methodThree, countThree);

        carChart.getData().add(slice1);
        carChart.getData().add(slice2);
        carChart.getData().add(slice3);

        carChart.setLegendSide(Side.LEFT);
        carChart.setLabelsVisible(true);
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 20 arial;");

        for (final PieChart.Data dataS : carChart.getData()) {
            dataS.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());

                    caption.setText(String.valueOf(dataS.getPieValue()));
                    caption.setVisible(true);
                }
            });
            dataS.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setVisible(false);
                        }
                    });
        }
    }

    public void toGoBack(ActionEvent actionEvent) {
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
}
