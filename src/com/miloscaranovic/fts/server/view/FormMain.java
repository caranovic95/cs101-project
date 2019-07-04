/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.server.view;

import com.miloscaranovic.fts.server.logic.thread.ThreadServer;
import com.miloscaranovic.fts.server.remote.ClientCommunication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author mcshu
 */
public class FormMain extends Application {

    Label l1 = new Label("Stanje servera: OFF");
    Button b1 = new Button("POKRENI SERVER");
    boolean statusServera = false;

    @Override
    public void start(Stage primaryStage) {

        VBox vb = new VBox();
        vb.setSpacing(10);

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);
        gp.add(l1, 0, 0);

        HBox centar = new HBox();
        centar.setAlignment(Pos.CENTER);
        centar.getChildren().add(b1);
        vb.getChildren().add(gp);

        HBox root = new HBox();
        root.setPadding(new Insets(10, 10, 10, 10)); // razmak od okvira
        root.setSpacing(10);

        b1.setOnAction((ActionEvent event) -> {
            ThreadServer threadServer = new ThreadServer();
            
            if (statusServera == true) {
                b1.setText("POKRENI SERVER!");
                l1.setText("Stanje servera: OFF");
                statusServera = false;
                
                threadServer.interrupt();
            } else {
                try {
                    ClientCommunication.getInstance();
                    b1.setText("UGASI SERVER!");
                    l1.setText("Stanje servera: ON");
                    
                    threadServer.start();
                    
                    statusServera = true;
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Could not start FTS Server due to error " + ex.getMessage());

                    alert.showAndWait();
                }
            }

        });

        root.getChildren().addAll(vb, centar);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("FTS-Server");
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
