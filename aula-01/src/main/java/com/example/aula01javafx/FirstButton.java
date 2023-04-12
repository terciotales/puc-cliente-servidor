package com.example.aula01javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstButton extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            VBox vbox;
            BorderPane root = new BorderPane();
            Scene scene;
            Button btn = new Button("OK");
            btn.setText("Tente de novo");

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (btn.getText().equals("Você me clicou!")) {
                        btn.setText("Tente de novo");
                    } else {

                        btn.setText("Você me clicou!");
                    }
                }
            });

            vbox = new VBox();
            vbox.setSpacing(400);
            vbox.setAlignment(Pos.CENTER);
            scene = new Scene(vbox, 400, 400);
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
