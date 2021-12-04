package db.factory.impl;

import db.MyDatabase;
import db.factory.interfaces.ClientInterf;
import model.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientSQL implements ClientInterf {

    private static ClientSQL instance;
    private final MyDatabase dbConnection;

    public ClientSQL() throws SQLException,ClassNotFoundException {
        dbConnection = MyDatabase.getInstance();
    }
    public static synchronized ClientSQL getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ClientSQL();
        }
        return instance;
    }

    @Override
    public int insert(Client obj) {
        String str = "INSERT INTO client (id_user, name, surname) VALUES("
                + obj.getIdUser() + ", "+ obj.getClientName() + ",'" + obj.getClientSurname() + "') RETURNING id";
        ArrayList<String[]> result = dbConnection.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public void update(Client obj, int id) {
        String str = "UPDATE client SET client.id_user="
                + obj.getIdUser()
                + ", client.name='"
                + obj.getClientName()
                + "', person.surname='"
                + obj.getClientSurname()
                + "'  WHERE client.id=" + id;
        dbConnection.update(str);
    }

    @Override
    public Client selectClient(int id) throws SQLException {
        String str = "SELECT * FROM client WHERE id = " + id;
        ArrayList<String[]> result = dbConnection.select(str);
        Client client = new Client();
        for (String[] items: result){
            client.setIdClient(Integer.parseInt(items[0]));
            client.setIdUser(Integer.parseInt(items[1]));
            client.setClientName(items[2]);
            client.setClientSurname(items[3]);
        }
        return client;
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM client WHERE id = " + id;
        dbConnection.delete(str);
    }

    @Override
    public ArrayList<Client> findAll() throws SQLException {
        String str = "SELECT * FROM client";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<Client> clients = new ArrayList<>();
        for (String[] items: result){
            Client client = new Client();

            client.setIdClient(Integer.parseInt(items[0]));
            client.setIdUser(Integer.parseInt(items[1]));
            client.setClientName(items[2]);
            client.setClientSurname(items[3]);
            clients.add(client);
        }
        return clients;
    }
}
