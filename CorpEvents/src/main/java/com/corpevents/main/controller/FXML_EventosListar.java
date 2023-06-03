package com.corpevents.main.controller;

import com.corpevents.main.dao.EventoDAO;
import com.corpevents.main.model.Evento;
import com.corpevents.main.util.TableEvento;
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

public class FXML_EventosListar implements Initializable {

    @FXML
    private AnchorPane listar_eventos_root;

    @FXML
    private TextField busca;

    @FXML
    private TableView<TableEvento> table;

    @FXML
    private TableColumn<TableEvento, Integer> column_id;

    @FXML
    private TableColumn<TableEvento, String> column_title;

    @FXML
    private TableColumn<TableEvento, String> column_date;

    @FXML
    private TableColumn<TableEvento, String> column_author;

    @FXML
    private TableColumn<TableEvento, String> column_category;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_edit;

    public void initialize(URL location, ResourceBundle resources) {
        EventoDAO eventoDAO = new EventoDAO();
        ArrayList<Evento> eventos = eventoDAO.selectAll();
        ObservableList<TableEvento> tableEventos = FXCollections.observableArrayList();

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        column_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        column_category.setCellValueFactory(new PropertyValueFactory<>("category"));

        for (Evento evento : eventos) {
            tableEventos.add(new TableEvento(evento.getId(), evento.getTitle(), evento.getDate(), evento.getAuthor(), evento.getCategory()));
        }

        table.setItems(tableEventos);

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

    }

    @FXML
    public void onSearch(KeyEvent keyEvent) {

    }

    public void clickDelete(MouseEvent mouseEvent) {

    }
}
