package com.corpevents.main.controller;

import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Pessoa;
import com.corpevents.main.util.TablePessoa;
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

public class FXML_PessoasListar implements Initializable {

    @FXML
    private AnchorPane listar_pessoas_root;

    @FXML
    private TextField busca;

    @FXML
    private TableView<TablePessoa> table;

    @FXML
    private TableColumn<TablePessoa, Integer> column_id;

    @FXML
    private TableColumn<TablePessoa, String> column_nome;

    @FXML
    private TableColumn<TablePessoa, String> column_username;

    @FXML
    private TableColumn<TablePessoa, Integer> column_role;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_edit;

    public void initialize(URL location, ResourceBundle resources) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ArrayList<Pessoa> pessoas = pessoaDAO.selectAll();
        ObservableList<TablePessoa> tablePessoas = FXCollections.observableArrayList();

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_role.setCellValueFactory(new PropertyValueFactory<>("role"));

        for (Pessoa pessoa : pessoas) {
            tablePessoas.add(new TablePessoa(pessoa.getId(), pessoa.getNome(), pessoa.getUsername(), pessoa.getRole()));
        }

        table.setItems(tablePessoas);

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
