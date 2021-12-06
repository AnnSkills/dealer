package db.factory.interfaces;

import model.Comment;
import model.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InsuranceInterf {
    int insert(Insurance obj);
    void update(Insurance obj, int id);
    Insurance selectInsurance(int id) throws SQLException;
    void delete(int id);
    ArrayList<Insurance> findAll() throws SQLException;
}
