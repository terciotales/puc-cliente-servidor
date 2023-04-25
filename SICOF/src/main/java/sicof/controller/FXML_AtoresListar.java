package sicof.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sicof.dao.AtorDAO;
import sicof.dao.FilmeDAO;
import sicof.helpers.TableAtor;
import sicof.main.Main;
import sicof.model.Ator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class FXML_AtoresListar implements Initializable {

    @FXML
    private AnchorPane listar_atores_root;

    @FXML
    private TextField busca;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_edit;

    @FXML
    private TableView<TableAtor> table;

    @FXML
    private TableColumn<TableAtor, Integer> table_id;

    @FXML
    private TableColumn<TableAtor, String> table_title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AtorDAO atorDAO = new AtorDAO();
        ArrayList<Ator> atores = atorDAO.getAll();
        ObservableList<TableAtor> tableAtores = FXCollections.observableArrayList();

        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_title.setCellValueFactory(new PropertyValueFactory<>("Name"));

        for (Ator ator : atores) {
            tableAtores.add(new TableAtor(ator.getId(), ator.getName()));
        }

        table.setItems(tableAtores);
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

    @FXML
    void clickDelete(MouseEvent event) {
        AtorDAO atorDAO = new AtorDAO();
        Ator ator = atorDAO.getById(table.getSelectionModel().getSelectedItem().getId());
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir a ator " + ator.getName() + "?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            FilmeDAO filmeDAO = new FilmeDAO();

            if (filmeDAO.existsWithCategory(ator.getId())) {
                alert = new Alert(Alert.AlertType.ERROR, "Não foi possível excluir a ator, pois existem filmes associados a ela!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if (atorDAO.delete(ator)) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Ator excluída com sucesso!", ButtonType.OK);
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
                table.getSelectionModel().clearSelection();
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Não foi possível excluir a ator!", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    @FXML
    void clickEdit(MouseEvent event) {
        Ator ator;
        AtorDAO atorDAO = new AtorDAO();
        ator = atorDAO.getById(table.getSelectionModel().getSelectedItem().getId());

        if (ator != null) {
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXML_AtoresEditar.fxml"));
                root = loader.load();
                FXML_AtoresEditar controller = loader.getController();
                controller.setAtor(ator);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BorderPane borderPane = (BorderPane) listar_atores_root.getParent();
            borderPane.setCenter(root);
        }
    }

    @FXML
    void onSearch(KeyEvent event) {
        AtorDAO atorDAO = new AtorDAO();
        ArrayList<Ator> atores = atorDAO.getAll();
        ObservableList<TableAtor> tableAtores = FXCollections.observableArrayList();

        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_title.setCellValueFactory(new PropertyValueFactory<>("Name"));

        for (Ator ator : atores) {
            if (Objects.requireNonNull(ator.getName()).toLowerCase().contains(busca.getText().toLowerCase())) {
                tableAtores.add(new TableAtor(ator.getId(), ator.getName()));
            }
        }

        table.setItems(tableAtores);
        tableSelectionListener();
    }
}
