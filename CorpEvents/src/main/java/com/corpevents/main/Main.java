package com.corpevents.main;

import com.corpevents.main.util.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe principal do sistema
 */
public class Main extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        String resource = "view/FXML_Login.fxml";
        stage.initStyle(StageStyle.UNDECORATED);

        if (Usuario.getInstance().isLogged()) {
            resource = "view/FXML_View.fxml";
        }

        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), 900, 585);
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("css/main.css")));
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/icon.png"))));
        stage.setTitle("CorpEvents - Gerenciamento de Eventos Corporativos");
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
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
        scene.setFill(Color.TRANSPARENT);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}