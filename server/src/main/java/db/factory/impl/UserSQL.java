package db.factory.impl;

import db.MyDatabase;
import db.factory.interfaces.UserInterf;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserSQL implements UserInterf {
    private static UserSQL instance;
    private final MyDatabase dbConnection;

    public UserSQL() throws SQLException, ClassNotFoundException {
        dbConnection = MyDatabase.getInstance();
    }

    public static synchronized UserSQL getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new UserSQL();
        }
        return instance;
    }

    @Override
    public void update(User obj, int id) {
        String str = "UPDATE users SET users.login= "
                + obj.getLogin()
                + "', users.password='"
                + obj.getPassword()
                + "', users.role='"
                + obj.getRole()
                + "'  WHERE users.id=" + obj.getIdUser();
        dbConnection.update(str);
    }

    @Override
    public int insert(User obj) {
        String str = "INSERT INTO users (login, password, role) VALUES('"
                + obj.getLogin() + "','" + obj.getPassword() + "','"
                + obj.getRole() + "') RETURNING id";
        ArrayList<String[]> result = dbConnection.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public User selectUser(String login, String password) throws SQLException {
        String str = "SELECT * FROM users WHERE login='" + login
                + "' AND password='" + password + "'";
        return setUserFromDB(str);
    }

    @Override
    public User selectUserByLogin(String login) throws SQLException {
        String str = "SELECT * FROM users WHERE login='" + login + "'";
        return setUserFromDB(str);
    }

    private User setUserFromDB(String str) throws SQLException {
        ArrayList<String[]> result = dbConnection.select(str);
        User user = new User();
        for (String[] items: result){
            user.setIdUser(Integer.parseInt(items[0]));
            user.setLogin(items[1]);
            user.setPassword(items[2]);
            user.setRole(items[3]);
        }
        return user;
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM users WHERE id = " + id;
        dbConnection.delete(str);
    }

    @Override
    public User selectUserById(int id) throws SQLException {
        String str = "SELECT * FROM users WHERE id=" + id;
        return setUserFromDB(str);
    }

    @Override
    public ArrayList<User> findAll() throws SQLException {
        String str = "SELECT * FROM users";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<User> users = new ArrayList<>();
        for (String[] items: result){
            User user = new User();
            user.setIdUser(Integer.parseInt(items[0]));
            user.setIdClient(Integer.parseInt(items[1]));
            user.setLogin(items[2]);
            user.setPassword(items[3]);
            user.setRole(items[4]);
            users.add(user);
        }
        return users;
    }
}
