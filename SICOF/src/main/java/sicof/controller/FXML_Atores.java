package sicof.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sicof.dao.AtorDAO;
import sicof.main.Main;
import sicof.model.Ator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class FXML_Atores implements Initializable {
    private String page = "Listar";

    @FXML
    private BorderPane border_pane;

    @FXML
    private HBox buttons;

    @FXML
    private TextField search;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.loadPage();
            this.setActiveButton();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setPage(MouseEvent event) throws IOException {
        this.page = ((Button) event.getSource()).getText();
        this.setActiveButton();
        this.loadPage();

        this.search.setVisible(this.page.equals("Listar"));
    }

    private void setActiveButton() {
        this.buttons.getChildren().forEach(button -> {
            if (Objects.equals(((Button) button).getText(), this.page)) {
                ((Button) button).getStyleClass().add("active");
            } else {
                ((Button) button).getStyleClass().remove("active");
            }
        });
    }

    private void loadPage() throws IOException {
        Parent root = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML_Atores" + this.page + ".fxml"));
            root = fxmlLoader.load();
        } catch (IOException exception) {
            Logger.getLogger(FXML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, exception);
        }

        border_pane.setCenter(root);
    }

    @FXML
    public void onSearch(KeyEvent keyEvent) {
        AtorDAO atorDAO = new AtorDAO();
        ArrayList<Ator> atores = atorDAO.search(((TextField) keyEvent.getSource()).getText());
        Parent root = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML_Atores" + this.page + ".fxml"));
            root = fxmlLoader.load();
            ListView<Ator> list = (ListView<Ator>) root.lookup("#list");
            list.getItems().clear();
            list.getItems().addAll(atores);
        } catch (IOException exception) {
            Logger.getLogger(FXML_View.class.getName()).log(java.util.logging.Level.SEVERE, null, exception);
        }

        border_pane.setCenter(root);
    }
}
