package com.example.ecommerce;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res = HelloApplication.connection.executeQuery("Select max(oID) as oID from orders");
        int orderID = 1;
        if(res.next()){
            orderID = res.getInt("oID") + 1;
        }
        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
        String query = String.format("Insert into orders values(%s, %s,'%s','%s')",orderID,productID,
                HelloApplication.emailID,ts);
        System.out.println(query);
        int response = HelloApplication.connection.executeUpdate(query);
        if(response > 0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your order is placed");
            dialog.showAndWait();
        }
        else
            System.out.println("The order is not placed");
    }
}
