package com.corpevents.main.controller;

import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Categoria;
import com.corpevents.main.model.Pessoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FXML_EventosFiltros implements Initializable {
    @FXML
    private ComboBox<Pessoa> filter_author;

    @FXML
    private ComboBox<Categoria> filter_category;

    @FXML
    private DatePicker final_date;

    @FXML
    private DatePicker initial_date;

    @FXML
    private Label title;

    private FXML_EventosListar eventosListar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = new Pessoa(0, "Todos", "", "", 1);
        this.filter_author.getItems().add(pessoa);
        this.filter_author.getItems().addAll(pessoaDAO.selectAll());

        Categoria categoria = new Categoria(0, "Todas");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        this.filter_category.getItems().add(categoria);
        this.filter_category.getItems().addAll(categoriaDAO.selectAll());
    }

    @FXML
    void filtrarBusca(MouseEvent event) {
        Map<String, String> filters = new HashMap<>();

        if (this.initial_date.getValue() != null) {
            filters.put("initial_date", this.initial_date.getValue().toString());
        }

        if (this.final_date.getValue() != null) {
            filters.put("final_date", this.final_date.getValue().toString());
        }

        if (this.filter_author.getValue() != null && this.filter_author.getValue().getId() != 0) {
            filters.put("author", String.valueOf(this.filter_author.getValue().getId()));
        }

        if (this.filter_category.getValue() != null && this.filter_category.getValue().getId() != 0) {
            filters.put("category", String.valueOf(this.filter_category.getValue().getId()));
        }

        this.eventosListar.setFilters(filters);
    }

    public void setEventosListar(FXML_EventosListar eventosListar) {
        this.eventosListar = eventosListar;

        Map<String, String> filters = this.eventosListar.getFilters();

        if (filters == null) {
            return;
        }

        if (filters.containsKey("initial_date")) {
            this.initial_date.setValue(java.time.LocalDate.parse(filters.get("initial_date")));
        }

        if (filters.containsKey("final_date")) {
            this.final_date.setValue(java.time.LocalDate.parse(filters.get("final_date")));
        }

        if (filters.containsKey("author")) {
            PessoaDAO pessoaDAO = new PessoaDAO();
            this.filter_author.setValue(pessoaDAO.selectById(Integer.parseInt(filters.get("author"))));
        }

        if (filters.containsKey("category")) {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            this.filter_category.setValue(categoriaDAO.selectById(Integer.parseInt(filters.get("category"))));
        }
    }
}
