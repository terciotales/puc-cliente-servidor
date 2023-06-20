package com.corpevents.main.controller;

import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.model.Categoria;
import com.corpevents.main.util.TableCategoria;
import com.corpevents.main.util.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe controladora da página de listagem de categorias
 */
public class FXML_CategoriasListar implements Initializable {

    @FXML
    private AnchorPane listar_categorias_root;

    @FXML
    private TextField busca;

    @FXML
    private TableView<TableCategoria> table;

    @FXML
    private TableColumn<TableCategoria, Integer> column_id;

    @FXML
    private TableColumn<TableCategoria, String> column_nome;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_edit;

    public void initialize(URL location, ResourceBundle resources) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categoria> categorias = categoriaDAO.selectAll();
        ObservableList<TableCategoria> tableCategorias = FXCollections.observableArrayList();

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        for (Categoria categoria : categorias) {
            tableCategorias.add(new TableCategoria(categoria.getId(), categoria.getNome()));
        }

        table.setItems(tableCategorias);
        tableSelectionListener();

        if (!Usuario.getInstance().isAdministrador()) {
            button_delete.setVisible(false);
            button_edit.setVisible(false);
        }
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

    }

    @FXML
    public void onSearch(KeyEvent keyEvent) {

    }

    public void clickDelete(MouseEvent mouseEvent) {

    }
}
