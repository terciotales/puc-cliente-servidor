package com.corpevents.main.controller;

import com.corpevents.main.Main;
import com.corpevents.main.dao.*;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Pessoa;
import com.corpevents.main.model.Pessoa;
import com.corpevents.main.model.Pessoa;
import com.corpevents.main.util.TablePessoa;
import com.corpevents.main.util.TablePessoa;
import com.corpevents.main.util.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Classe controladora da tela de listagem de pessoas
 */
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
    private TableColumn<TablePessoa, String> column_role;

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
            tablePessoas.add(new TablePessoa(pessoa.getId(), pessoa.getNome(), pessoa.getUsername(), Usuario.getInstance().getRole(pessoa.getRole())));
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
        Pessoa pessoa;
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoa = pessoaDAO.selectById(table.getSelectionModel().getSelectedItem().getId());

        if (pessoa != null) {
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/FXML_PessoasEditar.fxml"));
                root = loader.load();
                FXML_PessoasEditar controller = loader.getController();
                controller.setPessoa(pessoa);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BorderPane borderPane = (BorderPane) listar_pessoas_root.getParent();
            borderPane.setCenter(root);

            for (Node node : borderPane.getChildren()) {
                if (node.getStyleClass().contains("header-menu") && Objects.equals(node.getTypeSelector(), "HBox")) {
                    for (Node child : ((HBox) node).getChildren()) {
                        if (Objects.equals(child.getTypeSelector(), "HBox")) {
                            for (Node button : ((HBox) child).getChildren()) {
                                if (Objects.equals(button.getTypeSelector(), "Button")) {
                                    button.getStyleClass().remove("active");
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    @FXML
    public void onSearch(KeyEvent keyEvent) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ArrayList<Pessoa> pessoas = pessoaDAO.search(busca.getText());
        ObservableList<TablePessoa> tablePessoas = FXCollections.observableArrayList();

        if (pessoas == null) {
            pessoas = new ArrayList<>();
        }

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_role.setCellValueFactory(new PropertyValueFactory<>("role"));

        for (Pessoa pessoa : pessoas) {
            tablePessoas.add(new TablePessoa(pessoa.getId(), pessoa.getNome(), pessoa.getUsername(), Usuario.getInstance().getRole(pessoa.getRole())));
        }

        table.setItems(tablePessoas);
    }

    public void clickDelete(MouseEvent mouseEvent) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        EventoDAO eventoDAO = new EventoDAO();
        EventoPessoaDAO eventoPessoaDAO = new EventoPessoaDAO();

        TablePessoa tablePessoa = table.getSelectionModel().getSelectedItem();
        Pessoa pessoa = pessoaDAO.selectById(tablePessoa.getId());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Você tem certeza que deseja excluir o usuário " + pessoa.getNome() + "?");
        alert.setContentText("Esta ação não pode ser desfeita.");

        ButtonType buttonTypeYes = new ButtonType("Sim");
        ButtonType buttonTypeNo = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeYes) {
                if (pessoa.getId() == Usuario.getInstance().getPessoa().getId()) {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Erro");
                    alertError.setHeaderText("Não foi possível excluir o usuário " + pessoa.getNome());
                    alertError.setContentText("Você não pode excluir o usuário que está logado.");

                    alertError.showAndWait();
                } else if (!eventoPessoaDAO.pessoaHasRelatedEvento(pessoa.getId()) && !eventoDAO.hasRelatedUser(pessoa.getId())) {
                    pessoaDAO.delete(pessoa);
                    table.getItems().remove(tablePessoa);
                } else {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Erro");
                    alertError.setHeaderText("Não foi possível excluir o usuário " + pessoa.getNome());
                    alertError.setContentText("O usuário está relacionado a um ou mais eventos.");

                    alertError.showAndWait();
                }
            }
        });
    }
}
