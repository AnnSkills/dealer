package db.factory.impl;

import db.MyDatabase;
import db.factory.interfaces.OrderListInterf;
import model.OrderList;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderListSQL implements OrderListInterf {

    private static OrderListSQL instance;
    private final MyDatabase dbConnection;

    public OrderListSQL() throws SQLException, ClassNotFoundException {
        dbConnection = MyDatabase.getInstance();
    }

    public static synchronized OrderListSQL getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new OrderListSQL();
        }
        return instance;
    }


    @Override
    public int insert(OrderList obj) {
        String str = "INSERT INTO order_list (id_order, id_car) VALUES(" + obj.getIdOrder()
                + ", " + obj.getIdCar() + ") RETURNING id";
        ArrayList<String[]> result = dbConnection.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public OrderList selectOrderList(int id) throws SQLException {
        String str = "SELECT * FROM order_list WHERE id = " + id;
        ArrayList<String[]> result = dbConnection.select(str);
        OrderList orderList = new OrderList();
        for (String[] items: result){
            orderList.setIdOrdList(Integer.parseInt(items[0]));
            orderList.setIdOrder(Integer.parseInt(items[1]));
            orderList.setIdCar(Integer.parseInt(items[2]));
        }
        return orderList;
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM order_list WHERE id = " + id;
        dbConnection.delete(str);
    }

    @Override
    public ArrayList<OrderList> findAll() throws SQLException {
        String str = "SELECT * FROM order_list";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<OrderList> allOrderLists = new ArrayList<>();
        for (String[] items: result){

            OrderList orderList = new OrderList();
            orderList.setIdOrdList(Integer.parseInt(items[0]));
            orderList.setIdOrder(Integer.parseInt(items[1]));
            orderList.setIdCar(Integer.parseInt(items[2]));
            allOrderLists.add(orderList);
        }
        return allOrderLists;
    }
}
