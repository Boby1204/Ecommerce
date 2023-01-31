package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.ecommerce.HelloApplication.connection;
import static com.example.ecommerce.HelloApplication.root;

public class loginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query = String.format("Select * from User where email ='%s' AND pass='%s'",email.getText(),password.getText());
        try (ResultSet res = connection.executeQuery(query)) {
            if (res.next()) {
                HelloApplication.emailID = res.getString("email");
                String userType = res.getString("userType");
                if (userType.equals("seller")) {
                    AnchorPane sellerPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sellerPage.fxml")));
                    root.getChildren().add(sellerPage);
                } else {
                    System.out.println("We are logged in as Buyer");
                    ProductPage productPage = new ProductPage();

                    Header header = new Header();
                    AnchorPane productPane = new AnchorPane();
                    productPane.getChildren().add(productPage.products());
                    productPane.setLayoutX(150);
                    productPane.setLayoutY(100);
                    root.getChildren().clear();
                  root.getChildren().addAll(header.root,productPane);
                }
                System.out.println("The user is present in the user table");
            } else {
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Login");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.setContentText("Login Failed! , Please check username/password and try again");
                dialog.showAndWait();
            }
        }
    }
}
