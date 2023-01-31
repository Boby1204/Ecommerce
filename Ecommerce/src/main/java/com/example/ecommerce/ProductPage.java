package com.example.ecommerce;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.ecommerce.HelloApplication.*;

//Page without use of FXML
public class ProductPage {
public ListView<HBox> product; //we can align them horizontally

    public ListView<HBox> productsBySearch(String search) throws SQLException {
        product = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList(); //when new things comes in new label automatically changed in this list
        try (ResultSet res = connection.executeQuery("select * from product")) {
            while (res.next()) {
                if(res.getString("pName").toLowerCase().contains(search.toLowerCase())) {
                    Label name = new Label();
                    Label productId = new Label();
                    Label price = new Label();
                    Button buy = new Button();

                    name.setMaxWidth(50);
                    productId.setMinWidth(50);
                    price.setMinWidth(50);
                    buy.setText("Buy");
                    HBox productDetails = new HBox(); // horizontal box to show these

                    buy.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (HelloApplication.emailID.equals("")) {
                                Dialog<String> dialog = new Dialog<>();
                                dialog.setTitle("Login");
                                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                                dialog.getDialogPane().getButtonTypes().add(type);
                                dialog.setContentText("Please Login first!!");
                                dialog.showAndWait();
                            } else {
                                System.out.println("You are logged in with" + emailID);
                                Order order = new Order();
                                try {
                                    order.placeOrder((productId.getText()));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("Buy button is getting clicked");
                        }
                    });

                    name.setText(res.getString("pName"));
                    price.setText(res.getString("price"));
                    productId.setText(res.getString("pID"));
                    productDetails.getChildren().addAll(productId, name, price, buy);
                    productList.add(productDetails);
                }
            }
        }
        product.setItems(productList);
        return product;
    }

        public ListView<HBox> products() throws SQLException {
        product = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList(); //when new things comes in new label automatically changed in this list
        try (ResultSet res = connection.executeQuery("select * from product")) {
            while (res.next()) {
                Label name = new Label();
                Label productId = new Label();
                Label price = new Label();
                Button buy = new Button();

                name.setMaxWidth(50);
                productId.setMinWidth(50);
                price.setMinWidth(50);
                buy.setText("Buy");
                HBox productDetails = new HBox(); // horizontal box to show these

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(HelloApplication.emailID.equals("")){
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("Please Login first!!");
                            dialog.showAndWait();
                        }
                        else{
                            System.out.println("You are logged in with "+ emailID);
                            Order order = new Order();
                            try {
                                order.placeOrder((productId.getText()));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Buy button is getting clicked");
                    }
                });

                name.setText(res.getString("pName"));
                price.setText(res.getString("price"));
                productId.setText(res.getString("pID"));
                productDetails.getChildren().addAll(productId, name, price, buy);
                productList.add(productDetails);
            }
        }
        product.setItems(productList);
        return product;
    }

}
