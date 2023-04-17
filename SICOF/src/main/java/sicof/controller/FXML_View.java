package sicof.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sicof.main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class FXML_View {
    private String page = "Filmes";

    @FXML
    private Label page_title;

    @FXML
    private BorderPane border_pane;

    @FXML
    private VBox buttons;

    @FXML
    void setPage(MouseEvent event) throws IOException {
        this.page = ((Button) event.getSource()).getText();

        ((Button) event.getSource()).getStyleClass().add("active");

        this.buttons.getChildren().forEach(button -> {
            if (!Objects.equals(((Button) button).getText(), this.page)) {
                ((Button) button).getStyleClass().remove("active");
            }
        });

        page_title.setText(page);
        this.loadPage();
    }

    private void loadPage() throws IOException {
        Parent root = null;

        System.out.println("FXML_" + this.page + ".fxml");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML_" + this.page + ".fxml"));
            root = fxmlLoader.load();
        } catch (IOException exception) {
            Logger.getLogger(FXML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, exception);
        }

        border_pane.setCenter(root);
    }
}
