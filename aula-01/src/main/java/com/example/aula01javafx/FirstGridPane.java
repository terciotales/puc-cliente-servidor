package com.example.aula01javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstGridPane extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(20);
            grid.setPadding(new Insets(25, 25, 25, 25));

            Label lblUser = new Label("Usuário");
            grid.add(lblUser, 0, 1);
            TextField txtUser = new TextField();
            grid.add(txtUser, 1, 1);

            Label lblPassword = new Label("Senha");
            grid.add(lblPassword, 0, 2);
            TextField txtPassword = new TextField();
            grid.add(txtPassword, 1, 2);

            Button btnLogin = new Button("Login");
            grid.add(btnLogin, 0, 3);

            Button btnRegister = new Button("Registrar");
            grid.add(btnRegister, 1, 3);

            Scene cena = new Scene(grid, 400, 400);
            primaryStage.setTitle("Primeiro Projeto");
            primaryStage.setScene(cena);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
