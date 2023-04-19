package sicof.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import sicof.dao.FilmeDAO;
import sicof.model.Categoria;
import sicof.model.Filme;
import sicof.model.TableFilme;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class FXML_FilmesListar implements Initializable {

    @FXML
    private TableView<TableFilme> table;

    @FXML
    private TableColumn<TableFilme, String> table_actor;

    @FXML
    private TableColumn<TableFilme, String> table_category;

    @FXML
    private TableColumn<TableFilme, Integer> table_id;

    @FXML
    private TableColumn<TableFilme, String> table_releaseDate;

    @FXML
    private TableColumn<TableFilme, String> table_title;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_edit;

    public void initialize(URL location, ResourceBundle resources) {
        FilmeDAO filmeDAO = new FilmeDAO();
        ArrayList<Filme> filmes = filmeDAO.getAll();
        ObservableList<TableFilme> tableFilmes = FXCollections.observableArrayList();

        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        table_releaseDate.setCellValueFactory(new PropertyValueFactory<>("ReleaseDate"));
        table_category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        table_actor.setCellValueFactory(new PropertyValueFactory<>("Actors"));

        for (Filme filme : filmes) {
            tableFilmes.add(new TableFilme(filme.getId(), filme.getTitle(), filme.getReleaseDate(), filme.getCategory(), filme.getActors()));
        }

        table.setItems(tableFilmes);

        tableSelectionListener();
    }

    private void tableSelectionListener() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                button_delete.setDisable(false);
                button_edit.setDisable(false);
            } else {
                button_delete.setDisable(true);
                button_edit.setDisable(true);
            }
        });
    }

    public void clickEdit(MouseEvent mouseEvent) {
        Filme filme;
        FilmeDAO filmeDAO = new FilmeDAO();
        filme = filmeDAO.getById(table.getSelectionModel().getSelectedItem().getId());

        if (filme != null) {
            System.out.println(filme.getTitle());
        }
    }

    public void clickDelete(MouseEvent mouseEvent) {
        Filme filme;
        FilmeDAO filmeDAO = new FilmeDAO();
        filme = filmeDAO.getById(table.getSelectionModel().getSelectedItem().getId());

        if (filme != null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar Filme");
            alert.setHeaderText("Deseja realmente deletar o filme " + filme.getTitle() + "?");
            alert.showAndWait();

            if (alert.getResult().getText().equals("OK")) {
                filmeDAO.delete(filme);
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } else {
                alert.close();
            }
        }
    }
}
