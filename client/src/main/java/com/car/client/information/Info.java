package com.car.client.information;

import java.util.ArrayList;

import com.car.client.ClientInf;
import lombok.Getter;
import lombok.Setter;
import model.User;

@Setter
@Getter
public final class Info {
    private User user;
    private User editUser;
    private ClientInf clientInf;

    private final static Info instance =new Info();
    private Info(){}
    public static Info getInstance(){return instance; }

}
