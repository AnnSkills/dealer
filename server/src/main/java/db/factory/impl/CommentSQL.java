package db.factory.impl;

import db.MyDatabase;
import db.factory.interfaces.CommentInterf;
import model.Comment;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommentSQL implements CommentInterf {

    private static CommentSQL instance;
    private final MyDatabase dbConnection;

    public CommentSQL() throws SQLException, ClassNotFoundException {
        dbConnection = MyDatabase.getInstance();
    }

    public static synchronized CommentSQL getInstance()
            throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new CommentSQL();
        }
        return instance;
    }

    @Override
    public int insert(Comment obj) {
        String str = "INSERT INTO comment (id_client, topic, comment_text, estimation) VALUES("
                + obj.getIdClient() + ", '"+ obj.getCommentTopic() + "','" + obj.getCommentText() + "','"
                + obj.getCommentEstimation()  + "') ";
        ArrayList<String[]> result = dbConnection.insert(str);
        return Integer.parseInt(result.get(0)[0]);
    }

    @Override
    public void update(Comment obj, int id) {
        String str = "UPDATE comment SET comment.topic="
                + obj.getCommentTopic()
                + ", comment.comment_text='"
                + obj.getCommentText()
                + "', comment.estimation='"
                + obj.getCommentEstimation()
                + "'  WHERE comment.id=" + id;
        dbConnection.update(str);
    }

    @Override
    public Comment selectComment(int id) throws SQLException {
        String str = "SELECT * FROM comment WHERE id = " + id;
        ArrayList<String[]> result = dbConnection.select(str);
        Comment comment = new Comment();
        for (String[] items: result){
            comment.setIdComment(Integer.parseInt(items[0]));
            comment.setIdClient(Integer.parseInt(items[1]));
            comment.setCommentTopic(items[2]);
            comment.setCommentText(items[3]);
            comment.setCommentEstimation(Integer.parseInt(items[4]));
        }
        return comment;
    }

    @Override
    public void delete(int id) {
        String str = "DELETE FROM comment WHERE id = " + id;
        dbConnection.delete(str);
    }

    @Override
    public ArrayList<Comment> findAll() throws SQLException {
        String str = "SELECT * FROM comment";
        ArrayList<String[]> result = dbConnection.select(str);
        ArrayList<Comment> comments = new ArrayList<>();
        for (String[] items: result){
            Comment comment = new Comment();
            comment.setIdComment(Integer.parseInt(items[0]));
            comment.setIdClient(Integer.parseInt(items[1]));
            comment.setCommentTopic(items[2]);
            comment.setCommentText(items[3]);
            comment.setCommentEstimation(Integer.parseInt(items[4]));
            comments.add(comment);
        }
        return comments;
    }
}
