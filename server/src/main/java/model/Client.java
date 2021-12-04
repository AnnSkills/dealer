package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Client implements Serializable {

    private int idClient;
    private int idUser;
    private String clientName;
    private String clientSurname;

    public Client() {
        this.idClient = 0;
       this.idUser = 0;
       this.clientName = "";
       this.clientSurname = "";
    }
    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", idUser=" + idUser +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                '}';
    }
}
