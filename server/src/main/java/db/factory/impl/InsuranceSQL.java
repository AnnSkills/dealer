package db.factory.impl;

import db.MyDatabase;
import db.factory.interfaces.InsuranceInterf;
import model.Comment;
import model.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceSQL implements InsuranceInterf {

    private static InsuranceSQL instance;
    private final MyDatabase dbConnection;

    public InsuranceSQL() throws SQLException, ClassNotFoundException {
        dbConnection = MyDatabase.getInstance();
    }

    public static synchronized InsuranceSQL getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new InsuranceSQL();
        }
        return instance;
    }

    @Override
    public int insert(Insurance obj) {
        String str = "INSERT INTO insurance (insurance_type, id_client) VALUES('"
                + obj.getInsuranceName() + "', "+ obj.getIdClient() + ")";
        ArrayList<String[]> result = dbConnection.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public void update(Insurance obj, int id) {

    }

    @Override
    public Insurance selectInsurance(int id) throws SQLException {
        String str = "SELECT * FROM insurance WHERE id = " + id;
        ArrayList<String[]> result = dbConnection.select(str);
        Insurance insurance = new Insurance();
        for (String[] items: result){
            insurance.setIdInsurance(Integer.parseInt(items[0]));
            insurance.setIdClient(Integer.parseInt(items[1]));
            insurance.setInsuranceName(items[2]);
        }
        return insurance;
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM insurance WHERE id = " + id;
        dbConnection.delete(str);
    }

    @Override
    public ArrayList<Insurance> findAll() throws SQLException {
        String str = "SELECT * FROM insurance";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<Insurance> insurances = new ArrayList<>();
        for (String[] items: result){
        Insurance insurance = new Insurance();
            insurance.setIdInsurance(Integer.parseInt(items[0]));
            insurance.setIdClient(Integer.parseInt(items[1]));
            insurance.setInsuranceName(items[2]);
            insurances.add(insurance);
        }
        return insurances;
    }
}
