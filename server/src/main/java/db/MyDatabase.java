package db;

import db.command.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDatabase {
    private static MyDatabase instance;

    private String drivName = "com.mysql.jdbc.Driver";
    protected Connection connect;
    protected Statement statement;

    public MyDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(this.drivName);
        String url = "jdbc:mysql://localhost:3306/ann_db";
        String username = "ann";
        String password = "12345678";
        this.connect = DriverManager.getConnection(url, username, password);
        this.statement = this.connect.createStatement();
    }

    public static synchronized MyDatabase getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new MyDatabase();
        }
        return instance;
    }
    public ArrayList<String[]> insert(String query) {
        Command command = new InsertCommand(query, statement);
        ArrayList<String[]> result = new ArrayList<String[]>();
        try {
            command.execute();
            statement.executeQuery("SELECT LAST_INSERT_ID()");
            ResultSet resultSet = statement.getResultSet();
            int count = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                String[] arrayString = new String[count];
                for (int i = 1;  i <= count; i++)
                    arrayString[i - 1] = resultSet.getString(i);

                result.add(arrayString);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String[]> select(String query) throws SQLException {
        Command command = new SelectCommand(query, statement);
        ArrayList<String[]> result = new ArrayList<String[]>();
        try {
            ResultSet resultSet = command.execute().getResultSet();
            int count = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                String[] arrayString = new String[count];
                for (int i = 1;  i <= count; i++)
                    arrayString[i - 1] = resultSet.getString(i);

                result.add(arrayString);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public void delete(String query) {
        Command command = new DeleteCommand(query, statement);
        command.execute();
    }

    public void update(String query) {
        Command command = new UpdateCommand(query, statement);
        command.execute();
    }

    public void close() {
        try {
            connect.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
