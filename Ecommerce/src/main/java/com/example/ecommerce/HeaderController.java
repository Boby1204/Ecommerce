package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.ecommerce.HelloApplication.root;

public class HeaderController {
    @FXML
    public void initialize(){
        if(!HelloApplication.emailID.equals("")){
            loginButton.setOpacity(0);
            email.setText(HelloApplication.emailID);
        }
    }
    @FXML
    Label email;
    @FXML
    Button loginButton,logoutButton;
    @FXML
    TextField searchText;

    @FXML
    public void login(MouseEvent e)throws IOException{
        AnchorPane loginpage = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
    @FXML
    public void search(MouseEvent e) throws IOException, SQLException {
        ProductPage productPage = new ProductPage();

        Header header = new Header();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.productsBySearch(searchText.getText()));
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        root.getChildren().clear();
        root.getChildren().addAll(header.root,productPane);
    }
    @FXML
    public void logoutappear(MouseEvent e) {
        if(logoutButton.getOpacity()==0){
            logoutButton.setOpacity(1);
        }
        else{
            logoutButton.setOpacity(0);
        }
    }
@FXML
    public void logout(MouseEvent e) throws IOException {
        if(logoutButton.getOpacity()==1){
            HelloApplication.emailID = "";
            logoutButton.setOpacity(0);
            Header header = new Header();
            HelloApplication.root.getChildren().add(header.root);
        }
    }
}
