
module com.car.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires server;
    opens com.car.client to javafx.fxml;
    exports com.car.client;
    exports com.car.client.controllers;
    opens com.car.client.controllers to javafx.fxml;
    exports com.car.client.information;
    opens com.car.client.information to javafx.fxml;
}