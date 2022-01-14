package com.car.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.car.client.information.Info;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Comment;
import model.Insurance;

public class MakeCommentUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField topicInput;

    @FXML
    private TextArea textInput;

    @FXML
    private Slider rateSliderInput;

    @FXML
    private Button backButton;

    @FXML
    private Button sendButton;

    @FXML
    private Label comLabel;


    @FXML
    void toSendComment(ActionEvent event) throws IOException, ClassNotFoundException {

        Info info = Info.getInstance();

        Comment comment = new Comment();
        comment.setIdClient(info.getUser().getIdClient());
        comment.setCommentTopic(topicInput.getText());
        comment.setCommentText(textInput.getText());
        comment.setCommentEstimation((int) rateSliderInput.getValue());

        info.getClientInf().writeInt(5);
        info.getClientInf().writeObject("Comment");
        info.getClientInf().writeObject(comment);
        int id =(int) info.getClientInf().getObject();

        comLabel.setText("Комментарий отправлен!");
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

}
