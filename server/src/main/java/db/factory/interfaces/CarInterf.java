package db.factory.interfaces;

import model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarInterf {
    int insert(Car obj);
    void update(Car obj, int id);
    Car selectCar(String model) throws SQLException;
    void delete(int id);
    ArrayList<Car> findAll() throws SQLException;
}
