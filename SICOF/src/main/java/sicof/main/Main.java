package sicof.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 550);
        scene.getStylesheets().add("stylesheets/main.css");
        stage.getIcons().add(new Image("icons/icon.png"));
        stage.setTitle("SICOF - Sistema de Controle de Filmes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}