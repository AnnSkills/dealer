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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Car;
import model.Insurance;

import java.io.IOException;
import java.util.Objects;

public class AddInsurance {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> combBox;

    @FXML
    private Label done;

    @FXML
    private Button ordInsButton;

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
    void toOrderInsurance(ActionEvent event) throws IOException, ClassNotFoundException {
        if (isFilled()) {
            Info info = Info.getInstance();
            Insurance insurance = new Insurance();
            insurance.setInsuranceName(combBox.getValue());
            insurance.setIdClient(info.getUser().getIdClient());

            info.getClientInf().writeInt(5);
            info.getClientInf().writeObject("Insurance");
            info.getClientInf().writeObject(insurance);
            int id =(int) info.getClientInf().getObject();

            done.setText("Страховка оформлена!");


        }

    }
    private boolean isFilled() {
        boolean answer = true;
        if(combBox.getValue().equals("тип страхования")){
            done.setText("Выберите тип страхования!");
            answer = false;
        }
        else {
            done.setText("");
        }
        return answer;
    }


    @FXML
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList("тип страхования", "КАСКО", "ОСГО", "Страхование недвижимости");
        combBox.setItems(langs);
        combBox.setValue("тип страхования");

    }

}
