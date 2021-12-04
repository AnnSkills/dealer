package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderCar implements Serializable {

    private int idOrdCar;
    private String orderDate;
    private int idClient;

    public OrderCar() {
        this.idOrdCar = 0;
        this.orderDate = "";
        this.idClient = 0;

    }

    @Override
    public String toString() {
        return "OrderCar{" +
                "idOrdCar=" + idOrdCar +
                ", orderDate='" + orderDate + '\'' +
                ", idClient=" + idClient +
                '}';
    }
}
