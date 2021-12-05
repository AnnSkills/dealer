package db.factory.interfaces;

import model.OrderList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderListInterf {
    int insert(OrderList obj);
    OrderList selectOrderList(int id) throws SQLException;
    void delete(int id);
    ArrayList<OrderList> findAll() throws SQLException;

}
