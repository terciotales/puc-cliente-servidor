package com.example.aula01javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstImage extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
            try {
                Image image = new Image("https://www.infoescola.com/wp-content/uploads/2011/03/java.jpg");
                ImageView imageView = new ImageView(image);
                BorderPane root = new BorderPane();
                root.getChildren().add(imageView);
                Scene scene = new Scene(root, 400, 400);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
