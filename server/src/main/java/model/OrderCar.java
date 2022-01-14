package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCar implements Serializable {

    private int idOrdCar;
    private int idCar;
    private LocalDateTime orderDate;
    private int idClient;

    public OrderCar() {
        this.idOrdCar = 0;
        this.orderDate = LocalDateTime.now();
        this.idClient = 0;

    }

    @Override
    public String toString() {
        return "OrderCar{" +
                "idOrdCar=" + idOrdCar +
                ", idCar='" + idCar + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", idClient=" + idClient +
                '}';
    }
}
