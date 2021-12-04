package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Comment implements Serializable {

    private int idComment;
    private int idClient;
    private String commentTopic;
    private String commentText;
    private int commentEstimation;

    public Comment() {
        this.commentEstimation=0;
        this.commentText = "";
        this.commentTopic="";
        this.idClient = 0;
        this.idComment = 0;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", idClient=" + idClient +
                ", commentTopic='" + commentTopic + '\'' +
                ", commentText='" + commentText + '\'' +
                ", commentEstimation=" + commentEstimation +
                '}';
    }
}
