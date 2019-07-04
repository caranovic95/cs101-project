/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.client.view;

import com.miloscaranovic.fts.client.logic.ViewController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author mcshu
 */
public class FormMain extends Application {

    Button btn1 = new Button("LOG IN");
    TextField textFieldUsername = new TextField();
    PasswordField passwordFieldPassword = new PasswordField();

    @Override
    public void start(Stage primaryStage) {
        HBox hb = new HBox();
        hb.setAlignment(Pos.BASELINE_CENTER);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().add(new Label("LOGIN"));

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER_LEFT);
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setSpacing(10);
        GridPane gp = new GridPane();
        gp.add(new Label("User: "), 0, 0);
        gp.add(textFieldUsername, 1, 0);
        gp.add(new Label("Password: "), 0, 1);
        gp.add(passwordFieldPassword, 1, 1);

        vb.getChildren().add(gp);

        HBox centar = new HBox();
        centar.setAlignment(Pos.BOTTOM_CENTER);
        centar.getChildren().add(btn1);

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(hb, vb, centar);

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewController.getInstance().login(primaryStage, textFieldUsername.getText(), passwordFieldPassword.getText());
            }
        }
        );

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle(
                "Login");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
