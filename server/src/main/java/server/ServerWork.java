package server;

import db.factory.*;
import db.factory.impl.*;
import db.MyDatabase;
import db.factory.interfaces.*;
import model.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerWork {

    private ObjectInputStream in;
    private MyDatabase database;
    private ObjectOutputStream out;

    public ServerWork (ObjectInputStream in, MyDatabase database, ObjectOutputStream outputStream){
        this.in = in;
        this.database = database;
        this.out = outputStream;
    }


    public void getId (int idOperation) throws IOException, SQLException, ClassNotFoundException {
        switch(idOperation){
            case 1:
                signingIn();
                break;
            case 2:
                getAllEntities();
                break;
            case 3:
                findUserByLogin();
                break;
            case 4:
                getCarByModel();
                break;
            case 5:
                saveEntityAndGetId();
                break;
            case 6:
                deleteEntityById();
                break;
            case 7:
                updateEntity();
                break;
            case 8:
                getEnityById();
                break;
            default:
                break;
        }
    }


    private void getAllEntities() throws SQLException, ClassNotFoundException, IOException{
        String type = (String) in.readObject();
        switch (type) {
            case "Car": {
                CarInterf iCar = new CarSQL();
                ArrayList<Car> list = iCar.findAll();
                out.writeObject(list);
                break;
            }
            case "User": {
                UserInterf iUser = new UserSQL();
                ArrayList<User> list = iUser.findAll();
                out.writeObject(list);
                break;
            }
            case "Client": {
                ClientInterf iClient = new ClientSQL();
                ArrayList<Client> list = iClient.findAll();
                out.writeObject(list);
                break;
            }
            case "OrderList": {
                OrderListInterf iOrderList = new OrderListSQL();
                ArrayList<OrderList> list = iOrderList.findAll();
                out.writeObject(list);
                break;
            }
            case "OrderCar": {
                OrderCarInterf iOrderCar = new OrderCarSQL();
                ArrayList<OrderCar> list = iOrderCar.findAll();
                out.writeObject(list);
                break;
            }
            case "Comment":{
                CommentInterf iComment = new CommentSQL();
                ArrayList<Comment> list = iComment.findAll();
                out.writeObject(list);
            }
            case "Insurance":{
                InsuranceInterf insuranceInterf = new InsuranceSQL();
                ArrayList<Insurance> list = insuranceInterf.findAll();
                out.writeObject(list);
            }
            default:
                break;
        }
    }

    private void deleteEntityById() throws IOException, ClassNotFoundException, SQLException {
        String type = (String) in.readObject();
        int id = (int) in.readObject();
        switch (type) {
            case "Car":
            {
                CarInterf iCar = new CarSQL();
                iCar.delete(id);
                break;
            }
            case "User":
            {
                UserInterf iUser = new UserSQL();
                iUser.delete(id);
                break;
            }
            case "Client": {
                ClientInterf iClient = new ClientSQL();
                iClient.delete(id);
                break;
            }
            case "OrderList": {
                OrderListInterf iOrderList = new OrderListSQL();
                iOrderList.delete(id);
                break;
            }
            case "OrderCar": {
                OrderCarInterf iOrderCar = new OrderCarSQL();
               iOrderCar.delete(id);
                break;
            }
            case "Comment":{
                CommentInterf iComment = new CommentSQL();
                iComment.delete(id);
                break;
            }
            case "Insurance":{
                InsuranceInterf insuranceInterf = new InsuranceSQL();
                insuranceInterf.delete(id);
            }
            default:
                break;
        }
    }


    private void getEnityById() throws IOException, ClassNotFoundException, SQLException{
        String type = (String) in.readObject();
        int id = (int) in.readObject();
        switch (type) {
            case "User":
            {
            UserInterf userInterf = new UserSQL();
            User user = userInterf.selectUserById(id);
            out.writeObject(user);
            break;
            }
            case "Client":
            {
                ClientInterf clientInterf = new ClientSQL();
                Client client = clientInterf.selectClient(id);
                out.writeObject(client);
                break;
            }
            case "Car":
            {
               CarInterf carInterf = new CarSQL();
               Car car = carInterf.selectCarById(id);
                out.writeObject(car);
                break;
            }
            case "OrderCar":
            {
                OrderCarInterf orderCarInterf = new OrderCarSQL();
                OrderCar orderCar = orderCarInterf.selectOrderCar(id);
                out.writeObject(orderCar);
                break;
            }
            case "OrderList":
            {
                OrderListInterf orderListInterf = new OrderListSQL();
                OrderList orderList = orderListInterf.selectOrderList(id);
                out.writeObject(orderList);
                break;
            }
            case "Comment":
            {
                CommentInterf commentInterf = new CommentSQL();
                Comment comment = commentInterf.selectComment(id);
                out.writeObject(comment);
                break;
            }
            case "Insurance":{
                InsuranceInterf insuranceInterf = new InsuranceSQL();
                Insurance insurance = insuranceInterf.selectInsurance(id);
                out.writeObject(insurance);
            }
            default:
                break;
        }
    }

    private void updateEntity() throws IOException, ClassNotFoundException, SQLException{
        String type = (String) in.readObject();
        switch (type) {
            case "User":
            {
                User user = (User) in.readObject();
                int idUser = (int) in.readObject();
                int idClient = (int) in.readObject();
                user.setIdClient(idClient);
                user.setIdUser(idUser);
                UserInterf userInterf = new UserSQL();
                userInterf.update(user,idUser);
                break;
            }
            case "Client":
            {
                Client client = (Client) in.readObject();
                int idClient = (int) in.readObject();
                int idUser = (int) in.readObject();
                client.setIdClient(idClient);
                client.setIdUser(idUser);
                ClientInterf clientInterf = new ClientSQL();
                clientInterf.update(client,idClient);
                break;
            }
            case "Car":
            {
                Car car = (Car) in.readObject();
                int idCar = (int) in.readObject();
                car.setIdCar(idCar);
                CarInterf carInterf = new CarSQL();
                carInterf.update(car, idCar);
                break;
            }
            case "Comment":
            {
                Comment comment = (Comment) in.readObject();
                int idComment = (int) in.readObject();
                comment.setIdComment(idComment);
                CommentInterf commentInterf = new CommentSQL();
                commentInterf.update(comment, idComment);
                break;
            }
            case "OrderCar":
            {
                OrderCar orderCar = (OrderCar) in.readObject();
                int idOrderCar =(int)in.readObject();
                int idClient = (int)in.readObject();
                orderCar.setIdOrdCar(idOrderCar);
                orderCar.setIdClient(idClient);
                OrderCarInterf orderCarInterf = new OrderCarSQL();
                orderCarInterf.update(orderCar,idOrderCar);
                break;
            }
            default:
                break;
        }

    }

    private void saveEntityAndGetId() throws IOException, ClassNotFoundException, SQLException {
        String type = (String) in.readObject();
        switch (type) {
            case "User":
            {
                User user = (User) in.readObject();
                UserInterf iUser = new UserSQL();
                int id = iUser.insert(user);
                out.writeObject(id);
                break;
            }
            case "Client":
            {
                Client client = (Client) in.readObject();
                ClientInterf clientInterf = new ClientSQL();
                int id = clientInterf.insert(client);
                out.writeObject(id);
                break;
            }
            case "Car":
            {
                Car car = (Car) in.readObject();
                CarInterf carInterf = new CarSQL();
                int id = carInterf.insert(car);
                out.writeObject(id);
                break;
            }
            case "OrderList": {
                OrderList orderList = (OrderList) in.readObject();
                OrderListInterf orderListInterf = new OrderListSQL();
                int id = orderListInterf.insert(orderList);
                out.writeObject(id);
                break;
            }
            case "OrderCar": {
                OrderCar orderCar = (OrderCar) in.readObject();
                OrderCarInterf orderCarInterf = new OrderCarSQL();
                int id = orderCarInterf.insert(orderCar);
                out.writeObject(id);
                break;
            }
            case "Comment":{
                Comment comment = (Comment)in.readObject();
                CommentInterf commentInterf = new CommentSQL();
                int id = commentInterf.insert(comment);
                out.writeObject(id);
                break;
            }
            case "Insurance":{
                Insurance insurance = (Insurance)in.readObject();
                InsuranceInterf insuranceInterf = new InsuranceSQL();
                int id = insuranceInterf.insert(insurance);
                out.writeObject(id);
            }
            default:
                break;
        }


    }

    private void getCarByModel() throws IOException, ClassNotFoundException, SQLException {
        String model = (String) in.readObject();
        CarInterf carInterf = new CarSQL();
        Car car = carInterf.selectCar(model);
        out.writeObject(car);
    }

    private void findUserByLogin() throws IOException, ClassNotFoundException, SQLException {
        String login = (String) in.readObject();
        UserInterf iUser = new UserSQL();
        User user = iUser.selectUserByLogin(login);
        out.writeObject(user);
    }

    private void signingIn() throws IOException, SQLException, ClassNotFoundException {
        String login = (String) in.readObject();
        String password = (String) in.readObject();

        UserInterf iUser = new UserSQL();
        User user = iUser.selectUser(login, password);

        out.writeObject(user);
    }

}
