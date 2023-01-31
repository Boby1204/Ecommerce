package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.ecommerce.HelloApplication.*;

public class sellerPageController {
    @FXML
    TextField name,price,sellerId;
    @FXML
    public void addProduct(MouseEvent e) throws SQLException {
        int pID = 1;
        ResultSet response2 =connection.executeQuery("Select max(pID) as pID from Product");
        if(response2.next()){
            pID = response2.getInt("pID") + 1;
        }
        String query = String.format("Insert into Product values(%s,'%s',%s,'%s')",pID,name.getText(),price.getText(),sellerId.getText());
        int response = connection.executeUpdate(query);
        if(response > 0)
            System.out.println("New Product is added");
    }
}
