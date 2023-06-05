package com.corpevents.main;

import com.corpevents.main.util.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/FXML_Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 550);
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("css/main.css")));
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/icon.png"))));
        stage.setTitle("CorpEvents - Gerenciamento de Eventos Corporativos");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(String fxml) throws IOException {
        if (!Usuario.getInstance().isLogged()) {
            fxml = "FXML_Login.fxml";
        }

        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("view/" + fxml))));
        mainStage.setScene(scene);
        mainStage.show();
    }
}