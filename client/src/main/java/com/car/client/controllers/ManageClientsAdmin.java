package com.car.client.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.car.client.information.ClientData;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Client;
import model.User;

public class ManageClientsAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ClientData> table;

    @FXML
    private TableColumn<ClientData, String> columnLogin;

    @FXML
    private TableColumn<ClientData, String> columnPassword;

    @FXML
    private TableColumn<ClientData, String> columnName;

    @FXML
    private TableColumn<ClientData, String> columnSurname;

    @FXML
    private Button buttAdd;

    @FXML
    private Button buttEdit;

    @FXML
    private Button buttDelete;

    @FXML
    private TextField searchLogin;

    @FXML
    private Button buttToSearch;

    @FXML
    private Button buttToRefresh;

    @FXML
    private Button buttBack;

    @FXML
    private ComboBox<String> combBx;

    @FXML
    private Label labelForBox;

    @FXML
    private Label labelField;

    private ArrayList<ClientData> list;
    private DirectoryChooser directoryChooser;
    private int nowId = 0;
    private String nowName = "";
    private String nowSurname = "";
    private String nowLogin = "";
    private String nowPassword = "";
    private int nowIdClient = 0;
    private final String TEXT_REGEX = "[А-Яа-яё]{1,30}";

    @FXML
    void toAdd(ActionEvent event) {
        Info info = Info.getInstance();
        info.setEditUser(info.getUser());
        buttAdd.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/registration-page.fxml"));
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
    void toBack(ActionEvent event) {
        buttBack.getScene().getWindow().hide();
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
    void toDelete(ActionEvent event) throws IOException, ClassNotFoundException{

        if (nowId != 0) {
            Info info = Info.getInstance();
            info.getClientInf().writeInt(6);
            info.getClientInf().writeObject("User");
            info.getClientInf().writeObject(nowId);
            refreshTable();
        }
    }

    @FXML
    void toEdit(ActionEvent event) {
        if (nowId != 0) {
            User user = new User();
            user.setIdUser(nowId);
            user.setRole("USER");
            user.setLogin(nowLogin);
            user.setPassword(nowPassword);
            user.setIdClient(nowIdClient);
            Info info = Info.getInstance();
            info.setEditUser(user);

            buttEdit.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/controlUsers.fxml"));
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

    @FXML
    void toRefresh(ActionEvent event) throws IOException, ClassNotFoundException {
        refreshTable();
    }

    @FXML
    void toSearch(ActionEvent event) throws IOException, ClassNotFoundException {
        if (!combBx.getValue().equals("поле поиска")) {
            ArrayList<ClientData> listS = new ArrayList<>();
            Info info = Info.getInstance();




            if (combBx.getValue().equals("Логин")) {
                if (!searchLogin.getText().equals("")) {
                    info.getClientInf().writeInt(9);
                    info.getClientInf().writeObject("User");
                    info.getClientInf().writeObject(searchLogin.getText());
                    ArrayList<User> listOne = (ArrayList<User>)  info.getClientInf().getObject();
                    toGetPersonFromUser(listS, listOne);
                } else {
                    labelField.setText("Введите корректно поле");
                    return;
                }
            }
            if (combBx.getValue().equals("Имя") || combBx.getValue().equals("Фамилия")) {
                if (searchLogin.getText().matches(TEXT_REGEX)) {
                    info.getClientInf().writeInt(9);
                    info.getClientInf().writeObject("User");
                    info.getClientInf().writeObject(combBx.getValue());
                    info.getClientInf().writeObject(searchLogin.getText());

                    ArrayList<Client> listOne = (ArrayList<Client>) info.getClientInf().getObject();
                    toGetUsersFromClient(listS, listOne);
                } else {
                    labelField.setText("Введите корректно поле");
                    return;
                }
            }
            labelField.setText("");
            labelForBox.setText("");
            ObservableList<ClientData> arrayList = FXCollections.observableArrayList(listS);
            table.setItems(arrayList);
            nowLogin = "";
            nowPassword = "";
            nowId = 0;
            nowName = "";
            nowSurname = "";
            nowIdClient = 0;
        } else {
            labelForBox.setText("Необходимо выбрать");
        }
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException{
        refreshTable();
        directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);

        ObservableList<String> langs = FXCollections.observableArrayList("поле поиска", "Логин", "Имя", "Фамилия");
        combBx.setItems(langs);
        combBx.setValue("поле поиска");

        combBx.setOnAction(event -> searchLogin.setPromptText(combBx.getValue()));

        TableView.TableViewSelectionModel<ClientData> selectionModel = table.getSelectionModel();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<ClientData>() {

            @Override
            public void changed(ObservableValue<? extends ClientData> observableValue, ClientData clientData, ClientData t1) {
                if (t1 != null) {
                    nowId = t1.getId();
                    nowName = t1.getName();
                    nowSurname = t1.getSurname();
                    nowLogin = t1.getLogin();
                    nowPassword = t1.getPassword();
                    nowIdClient = t1.getIdClient();
                }
            }

        });

    }
    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        directoryChooser.setTitle("Выберите путь расположения файла");
    }

    private void toGetUsersFromClient(ArrayList<ClientData> listS, ArrayList<Client> listOne) throws IOException, ClassNotFoundException {
        Info info = Info.getInstance();
        for (Client obj :
                listOne) {
            if (obj.getIdUser() > 1) {
                ClientData clientData = new ClientData();
                clientData.setId(obj.getIdUser());
                clientData.setIdClient(obj.getIdClient());
                clientData.setName(obj.getClientName());
                clientData.setSurname(obj.getClientSurname());


               info.getClientInf().writeInt(8);
               info.getClientInf().writeObject("User");
               info.getClientInf().writeObject(clientData.getIdClient());
               User user = (User) info.getClientInf().getObject();
               clientData.setLogin(user.getLogin());
               clientData.setPassword(user.getPassword());

                listS.add(clientData);
            }
        }
    }

    private void toGetPersonFromUser(ArrayList<ClientData> listS, ArrayList<User> listOne) throws IOException, ClassNotFoundException {
        Info info = Info.getInstance();
        for (User obj :
                listOne) {
            if (obj.getIdUser() > 1) {

                ClientData clientData = new ClientData();
                clientData.setId(obj.getIdUser());
                clientData.setLogin(obj.getLogin());
                clientData.setPassword(obj.getPassword());
                clientData.setIdClient(obj.getIdClient());

                info.getClientInf().writeInt(8);
                info.getClientInf().writeObject("Client");
                info.getClientInf().writeObject(clientData.getIdClient());
                Client client=(Client) info.getClientInf().getObject();
                clientData.setName(client.getClientName());
                clientData.setSurname(client.getClientSurname());

                listS.add(clientData);
            }
        }
    }
    private void refreshTable() throws IOException, ClassNotFoundException {
        Info info = Info.getInstance();
        info.getClientInf().writeInt(2);
        info.getClientInf().writeObject("User");
        ArrayList<User> listOne = (ArrayList<User>) info.getClientInf().getObject();
        list = new ArrayList<>();
        toGetPersonFromUser(list, listOne);

        ObservableList<ClientData> arrayList = FXCollections.observableArrayList(list);

        columnLogin.setCellValueFactory(new PropertyValueFactory<ClientData, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<ClientData, String>("password"));
        columnName.setCellValueFactory(new PropertyValueFactory<ClientData, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<ClientData, String>("surname"));


        table.setItems(arrayList);
        nowLogin = "";
        nowPassword = "";
        nowId = 0;
        nowName = "";
        nowSurname = "";
        nowIdClient = 0;
        searchLogin.clear();
        combBx.setValue("поле поиска");
    }

}
