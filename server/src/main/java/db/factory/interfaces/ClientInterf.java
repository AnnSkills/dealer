package db.factory.interfaces;

import model.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientInterf {
    int insert(Client obj);
    void update(Client obj, int id);
    Client selectClient(int id) throws SQLException;
    void delete(int id);
    ArrayList<Client> findAll() throws SQLException;
}
