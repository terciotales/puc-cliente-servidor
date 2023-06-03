package com.corpevents.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/FXML_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 550);
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("css/main.css")));
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/icon.png"))));
        stage.setTitle("CorpEvents - Gerenciamento de eventos corporativos");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}