package com.corpevents.main.controller;

import com.corpevents.main.Main;
import com.corpevents.main.util.Greetings;
import com.corpevents.main.util.Usuario;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static com.corpevents.main.util.TextFieldUtils.escapeSpecialCharacters;
import static com.corpevents.main.util.TextFieldUtils.removeSpecialCharacters;

/**
 * Classe controladora da tela principal
 */
public class FXML_View implements Initializable {
    private String page = "Dashboard";

    @FXML
    private Label page_title;

    @FXML
    private BorderPane border_pane;

    @FXML
    private VBox buttons;

    @FXML
    private Text greetings;

    @FXML
    private Text user;

    @FXML
    private HBox dragArea;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.loadPage();
            this.setActiveButton();
            page_title.setText(page);
            greetings.setText(Greetings.getGreeting());
            user.setText(Usuario.getInstance().getPessoa().getNome() + "!");

            if (!Usuario.getInstance().isAdministrador()) {
                buttons.getChildren().remove(2);
                buttons.getChildren().remove(3);
            }

            dragArea.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = Main.getMainStage().getX() - event.getScreenX();
                    yOffset = Main.getMainStage().getY() - event.getScreenY();
                }
            });

            dragArea.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Main.getMainStage().setX(event.getScreenX() + xOffset);
                    Main.getMainStage().setY(event.getScreenY() + yOffset);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setPage(MouseEvent event) throws IOException {
        this.page = ((Button) event.getSource()).getText();
        page_title.setText(page);
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

    private void loadPage() throws IOException {
        Parent root = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/FXML_" + escapeSpecialCharacters(this.page) + ".fxml"));
            root = fxmlLoader.load();
        } catch (IOException exception) {
            Logger.getLogger(FXML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, exception);
        }

        border_pane.setCenter(root);
        BorderPane.setAlignment(root, javafx.geometry.Pos.TOP_LEFT);
        BorderPane.setMargin(root, new javafx.geometry.Insets(0, 2, 0, 0));

    }

    /**
     * Fecha a aplicação.
     *
     * @param event Evento de clique do mouse.
     */
    @FXML
    void closeApplication(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Faz o logout do usuário.
     *
     * @param event Evento de clique do mouse.
     * @throws IOException Exceção de I/O.
     */
    @FXML
    void logout(MouseEvent event) throws IOException {
        Usuario.getInstance().setIsLogged(false);
        Usuario.getInstance().setPessoa(null);
        Main.changeScene("view/FXML_Login.fxml");
    }

    /**
     * Minimiza a aplicação.
     *
     * @param event Evento de clique do mouse.
     */
    @FXML
    void minimizeApplication(MouseEvent event) {
        ((Stage) Main.getMainStage().getScene().getWindow()).setIconified(true);
    }

}
