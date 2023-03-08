package com.example.aula01javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane sp = new StackPane();
        Label lbl = new Label();
        lbl.setText("Usando JavaFX");
        sp.getChildren().add(lbl);

        Scene cena = new Scene(sp, 400, 200);
        primaryStage.setTitle("Primeiro Projeto");
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}