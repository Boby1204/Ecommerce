package com.example.ecommerce;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
   public static DatabaseConnection connection;
    public static Group root;
    public static String emailID;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailID ="";
        connection = new DatabaseConnection();
        root = new Group();
        Header header = new Header();
        ProductPage productPage = new ProductPage();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.products());
        root.getChildren().addAll(header.root,productPane);
        productPane.setLayoutY(100);
        productPane.setLayoutX(150);

        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("Connection closed");
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}