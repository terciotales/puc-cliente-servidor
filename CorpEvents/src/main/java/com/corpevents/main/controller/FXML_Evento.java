package com.corpevents.main.controller;

import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.dao.EventoPessoaDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Evento;
import com.corpevents.main.model.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import static com.corpevents.main.util.DateFormatter.*;

public class FXML_Evento {
    @FXML
    private Label author;

    @FXML
    private Label category;

    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private Label local;

    @FXML
    private Label people;

    @FXML
    private Label title;

    public void setEvento(Evento evento) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        EventoPessoaDAO eventoPessoaDAO = new EventoPessoaDAO();

        this.author.setText(pessoaDAO.selectById(evento.getAuthor()).getNome());
        this.category.setText(categoriaDAO.selectById(evento.getCategory()).getNome());
        this.date.setText(dateFormatter(evento.getDate()));
        this.description.setText(evento.getDescription());
        this.local.setText(evento.getLocal());
        this.title.setText(evento.getTitle());

        StringBuilder peopleText = new StringBuilder();
        ArrayList<Pessoa> pessoas = eventoPessoaDAO.selectByEvento(evento.getId());

        for (Pessoa pessoa : pessoas) {
            peopleText.append(pessoa.getNome()).append("\n");
        }

        this.people.setText(peopleText.toString());
        this.people.setMinHeight((18 * pessoas.size()) + 12);
    }
}
