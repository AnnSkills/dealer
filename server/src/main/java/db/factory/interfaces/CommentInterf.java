package db.factory.interfaces;

import model.Comment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommentInterf {
    int insert(Comment obj);
    void update(Comment obj, int id);
    Comment selectComment(int id) throws SQLException;
    void delete(int id);
    ArrayList<Comment> findAll() throws SQLException;
}
