package com.corpevents.main.controller;

import com.corpevents.main.Main;
import com.corpevents.main.util.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Classe controladora da tela de pessoas
 */
public class FXML_Pessoas implements Initializable {
    private String page = "Listar";

    @FXML
    private BorderPane border_pane;

    @FXML
    private HBox buttons;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.loadPage();
            this.setActiveButton();

            if (!Usuario.getInstance().isAdministrador()) {
                buttons.getChildren().get(1).setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setPage(MouseEvent event) throws IOException {
        this.page = ((Button) event.getSource()).getText();
        this.setActiveButton();
        this.loadPage();
    }

    private void setActiveButton() {
        this.buttons.getChildren().forEach(button -> {
            if (Objects.equals(((Button) button).getText(), this.page)) {
                if (!button.getStyleClass().contains("active")) {
                    button.getStyleClass().add("active");
                }
            } else {
                button.getStyleClass().remove("active");
            }
        });
    }

    public void loadPage() throws IOException {
        Parent root = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/FXML_Pessoas" + this.page + ".fxml"));
            root = fxmlLoader.load();
        } catch (IOException exception) {
            Logger.getLogger(FXML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, exception);
        }

        border_pane.setCenter(root);
        border_pane.setMaxWidth(698);
        BorderPane.setMargin(root, new javafx.geometry.Insets(0, 2, 0, 0));
    }

    public void setPage(String page) {
        this.page = page;
    }

    public BorderPane getBorderPane() {
        return this.border_pane;
    }
}
