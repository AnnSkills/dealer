package db.factory.interfaces;

import model.Comment;
import model.OrderCar;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderCarInterf {
    int insert(OrderCar obj);
    OrderCar selectOrderCar(int id) throws SQLException;
    void update(OrderCar obj, int id);
    void delete(int id);
    ArrayList<OrderCar> findAll() throws SQLException;
}
