package model;

import java.io.Serializable;

public class Car implements Serializable {

    private int idCar;
    private String vinCar;
    private String modelCar;
    private String colorCar;
    private int priceCar;

    public Car() {
        this.idCar = 0;
        this.vinCar = "";
        this.modelCar = "";
        this.colorCar = "";
        this.priceCar = 0;
    }

    @Override
    public String toString() {
        return "Car{" +
                "idCar=" + idCar +
                ", vinCar='" + vinCar + '\'' +
                ", modelCar='" + modelCar + '\'' +
                ", colorCar='" + colorCar + '\'' +
                ", priceCar=" + priceCar +
                '}';
    }
}
