package sicof.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sicof.dao.FilmeDAO;
import sicof.model.Ator;
import sicof.model.Categoria;
import sicof.model.Filme;
import sicof.model.TableFilme;

import java.net.URL;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class FXML_FilmesAdicionar implements Initializable {

    @FXML
    private ComboBox<Ator> actor;

    @FXML
    private ComboBox<Categoria> category;

    @FXML
    private ListView<Ator> list_actors;

    @FXML
    private DatePicker release_date;

    @FXML
    private TextField title;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addActor(MouseEvent event) {

    }

    @FXML
    void cancel(MouseEvent event) {

    }

    @FXML
    void removeActor(MouseEvent event) {

    }

    @FXML
    void saveFilme(MouseEvent event) {
        String title = this.title.getText();
        ZoneOffset zoneOffset = ZoneOffset.ofHours(0);
        Date releaseDate = new Date();
        Categoria category = this.category.getValue();
        ArrayList<Ator> actors = new ArrayList<>(this.list_actors.getItems());

        System.out.println(this.release_date.getValue());

        if (title.length() == 0 || this.release_date.getValue() == null || category == null || actors.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Preencha todos os campos!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Filme filme = new Filme(0, title, releaseDate, category, actors);
        FilmeDAO filmeDAO = new FilmeDAO();

        boolean insert = filmeDAO.insert(filme);

        Alert alert;
        if (insert) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Filme adicionado com sucesso!", ButtonType.OK);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Erro ao adicionar filme!", ButtonType.OK);
        }

        alert.showAndWait();
    }
}
