package com.car.client.information;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {
    private int idOrder;
    private String model;
    private String vinCar;
    private int priceCar;
    private String name;
    private String surname;
    private String created_at;

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", model='" + model + '\'' +
                ", vinCar='" + vinCar + '\'' +
                ", priceCar=" + priceCar +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
