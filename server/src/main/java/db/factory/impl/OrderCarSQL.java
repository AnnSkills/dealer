package db.factory.impl;

import db.MyDatabase;
import db.factory.interfaces.OrderCarInterf;
import model.OrderCar;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderCarSQL implements OrderCarInterf {

    private static OrderCarSQL instance;
    private final MyDatabase dbConnection;

    public OrderCarSQL() throws SQLException, ClassNotFoundException {
        dbConnection = MyDatabase.getInstance();
    }

    public static synchronized OrderCarSQL getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new OrderCarSQL();
        }
        return instance;
    }

    @Override
    public int insert(OrderCar obj) {
        String str = "INSERT INTO order_car (created_at, id_client) VALUES("
                + obj.getOrderDate() + ",'" + obj.getIdClient() + "') RETURNING id";
        ArrayList<String[]> result = dbConnection.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public OrderCar selectOrderCar(int id) throws SQLException {
        String str = "SELECT * FROM order_car WHERE id=" + id;
        ArrayList<String[]> result = dbConnection.select(str);
        OrderCar orderCar = new OrderCar();

        for (String[] items: result){
            orderCar.setIdOrdCar(Integer.parseInt(items[0]));
            orderCar.setOrderDate(items[1]);
            orderCar.setIdClient(Integer.parseInt(items[2]));
        }
        return orderCar;
    }

    @Override
    public void update(OrderCar obj, int id) {
        String str = "UPDATE order_car SET order_car.created_at="
                + obj.getOrderDate()
                + "'  WHERE order_car.id=" + id;
        dbConnection.update(str);
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM order_car WHERE id = " + id;
        dbConnection.delete(str);
    }

    @Override
    public ArrayList<OrderCar> findAll() throws SQLException {
        String str = "SELECT * FROM order_car";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<OrderCar> list = new ArrayList<>();
        for (String[] items: result){
            OrderCar orderCar = new OrderCar();
            orderCar.setIdOrdCar(Integer.parseInt(items[0]));
            orderCar.setOrderDate(items[1]);
            orderCar.setIdClient(Integer.parseInt(items[2]));
            list.add(orderCar);
        }
        return list;
    }
}
