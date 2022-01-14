package db.factory.impl;

import db.MyDatabase;
import db.factory.interfaces.CarInterf;
import model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarSQL implements CarInterf {

    private static CarSQL instance;
    private final MyDatabase dbConnection;

    public CarSQL() throws SQLException, ClassNotFoundException {
        dbConnection = MyDatabase.getInstance();
    }

    public static synchronized CarSQL getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new CarSQL();
        }
        return instance;
    }


    @Override
    public int insert(Car obj) {
        String str = "INSERT INTO car (vin, model, color, price) VALUES('"
                + obj.getVinCar() +  "','" + obj.getModelCar() +  "','" + obj.getColorCar() + "','" + obj.getPriceCar() + "') ";
        ArrayList<String[]> result = dbConnection.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public void update(Car obj, int id) {
        String str = "UPDATE car SET car.vin='"
                + obj.getVinCar()
                + "', car.model='"
                + obj.getModelCar()
                + "', car.color='"
                + obj.getColorCar()
                + "', car.price='"
                + obj.getPriceCar()
                + "'  WHERE car.id=" + obj.getIdCar();
        dbConnection.update(str);
    }

    @Override
    public Car selectCar(String model) throws SQLException {
        String str = "SELECT * FROM car WHERE model='" + model +"'";
        ArrayList<String[]> result = dbConnection.select(str);
        Car car = new Car();
        for (String[] items: result){
            car.setIdCar(Integer.parseInt(items[0]));
            car.setVinCar(items[1]);
            car.setModelCar(items[2]);
            car.setColorCar(items[3]);
            car.setPriceCar(Integer.parseInt(items[4]));
        }
        return car;
    }

    @Override
    public Car selectCarById(int id) throws SQLException {
        String str = "SELECT * FROM car WHERE id='" + id +"'";
        ArrayList<String[]> result = dbConnection.select(str);
        Car car = new Car();
        for (String[] items: result){
            car.setIdCar(Integer.parseInt(items[0]));
            car.setVinCar(items[1]);
            car.setModelCar(items[2]);
            car.setColorCar(items[3]);
            car.setPriceCar(Integer.parseInt(items[4]));
        }
        return car;
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM car WHERE id = " + id;
        dbConnection.delete(str);
    }

    @Override
    public ArrayList<Car> findAll() throws SQLException {
        String str = "SELECT * FROM car";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<Car> cars = new ArrayList<>();
        for (String[] items: result){
            Car car = new Car();
            car.setIdCar(Integer.parseInt(items[0]));
            car.setVinCar(items[1]);
            car.setModelCar(items[2]);
            car.setColorCar(items[3]);
            car.setPriceCar(Integer.parseInt(items[4]));
            cars.add(car);
        }
        return cars;
    }

    @Override
    public ArrayList<Car> findAll(String model) throws SQLException {
        String str = "SELECT * FROM car WHERE model='" + model +"'";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<Car> cars = new ArrayList<>();
        for (String[] items: result){
            Car car = new Car();
            car.setIdCar(Integer.parseInt(items[0]));
            car.setVinCar(items[1]);
            car.setModelCar(items[2]);
            car.setColorCar(items[3]);
            car.setPriceCar(Integer.parseInt(items[4]));
            cars.add(car);
        }
        return cars;
    }
}
