package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderList implements Serializable {
    private int idOrdList;
    private int idOrder;
    private int idCar;

    public OrderList() {
        this.idCar = 0;
        this.idOrdList = 0;
        this.idOrder = 0;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "idOrdList=" + idOrdList +
                ", idOrder=" + idOrder +
                ", idCar=" + idCar +
                '}';
    }
}
