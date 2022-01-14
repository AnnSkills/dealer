package com.car.client.information;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientData {
    private int id;
    private String login;
    private String password;
    private int idClient;
    private String name;
    private String surname;

    public ClientData(){
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", idClient=" + idClient +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

