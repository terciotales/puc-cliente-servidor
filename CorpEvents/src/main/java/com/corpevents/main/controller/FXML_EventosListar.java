package com.corpevents.main.controller;

import com.corpevents.main.Main;
import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.dao.EventoDAO;
import com.corpevents.main.dao.EventoPessoaDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Evento;
import com.corpevents.main.util.DateFormatter;
import com.corpevents.main.util.TableEvento;
import com.corpevents.main.util.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class FXML_EventosListar implements Initializable {

    @FXML
    private AnchorPane listar_eventos_root;

    @FXML
    private TextField busca;

    @FXML
    private TableView<TableEvento> table;

    @FXML
    private TableColumn<TableEvento, Integer> column_title;

    @FXML
    private TableColumn<TableEvento, String> column_date;

    @FXML
    private TableColumn<TableEvento, String> column_author;

    @FXML
    private TableColumn<TableEvento, Integer> column_category;

    @FXML
    private TableColumn<TableEvento, Integer> column_people;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_edit;

    public void initialize(URL location, ResourceBundle resources) {
        EventoDAO eventoDAO = new EventoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        EventoPessoaDAO eventoPessoaDAO = new EventoPessoaDAO();

        ArrayList<Evento> eventos = eventoDAO.selectAll();
        ObservableList<TableEvento> tableEventos = FXCollections.observableArrayList();

        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        column_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        column_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        column_people.setCellValueFactory(new PropertyValueFactory<>("countPeople"));

        for (Evento evento : eventos) {
            tableEventos.add(new TableEvento(evento.getId(), evento.getTitle(), DateFormatter.dateFormatter(evento.getDate()), pessoaDAO.selectById(evento.getAuthor()).getNome(), categoriaDAO.selectById(evento.getCategory()).getNome(), eventoPessoaDAO.selectByEvento(evento.getId()).size()));
        }

        table.setItems(tableEventos);

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

    @FXML
    void onMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Evento evento;
            EventoDAO eventoDAO = new EventoDAO();
            evento = eventoDAO.selectById(table.getSelectionModel().getSelectedItem().getId());

            if (evento != null) {
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/FXML_Evento.fxml"));
                    Scene scene = new Scene(loader.load(), 400, 600);
                    FXML_Evento controller = loader.getController();
                    controller.setEvento(evento);
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/icon.png"))));
                    stage.setTitle("CorpEvents - Gerenciamento de Eventos Corporativos");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clickEdit(MouseEvent mouseEvent) {
        Evento evento;
        EventoDAO eventoDAO = new EventoDAO();
        evento = eventoDAO.selectById(table.getSelectionModel().getSelectedItem().getId());

        if (evento != null) {
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/FXML_EventosEditar.fxml"));
                root = loader.load();
                FXML_EventosEditar controller = loader.getController();
                controller.setEvento(evento);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BorderPane borderPane = (BorderPane) listar_eventos_root.getParent();
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

    }

    public void clickDelete(MouseEvent mouseEvent) {
        EventoDAO eventoDAO = new EventoDAO();
        TableEvento tableEvento = table.getSelectionModel().getSelectedItem();
        Evento evento = eventoDAO.selectById(tableEvento.getId());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Você tem certeza que deseja excluir o evento " + evento.getTitle() + "?");
        alert.setContentText("Esta ação não pode ser desfeita.");

        ButtonType buttonTypeYes = new ButtonType("Sim");
        ButtonType buttonTypeNo = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeYes) {
                eventoDAO.delete(evento);
                table.getItems().remove(tableEvento);
            }
        });
    }
}
