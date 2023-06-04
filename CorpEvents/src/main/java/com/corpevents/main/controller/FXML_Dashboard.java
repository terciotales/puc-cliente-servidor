package com.corpevents.main.controller;
import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.dao.EventoDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Evento;
import com.corpevents.main.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXML_Dashboard implements Initializable {
    @FXML
    private Text categorias;

    @FXML
    private Text eventos_anteriores;

    @FXML
    private Text eventos_participando;

    @FXML
    private Text eventos_totais;

    @FXML
    private Text pessoas;

    @FXML
    private Text proximos_eventos;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.setItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setItems() throws ParseException {
        EventoDAO eventoDAO = new EventoDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        this.eventos_totais.setText(String.valueOf(eventoDAO.count()));

        ArrayList<Evento> proximos = eventoDAO.selectNext();
        StringBuilder proximos_eventos = new StringBuilder();

        for (Evento evento : proximos) {
            proximos_eventos.append(Date.dateFormatter(evento.getDate())).append(" - ").append(evento.getTitle()).append("\n");
        }

        if (proximos.size() == 0) {
            proximos_eventos.append("Nenhum evento econtrado.");
        }

        this.proximos_eventos.setText(proximos_eventos.toString());

        ArrayList<Evento> anteriores = eventoDAO.selectLast();
        StringBuilder eventos_anteriores = new StringBuilder();

        for (Evento evento : anteriores) {
            eventos_anteriores.append(Date.dateFormatter(evento.getDate())).append(" - ").append(evento.getTitle()).append("\n");
        }

        if (anteriores.size() == 0) {
            eventos_anteriores.append("Nenhum evento encontrado.");
        }

        this.eventos_anteriores.setText(eventos_anteriores.toString());

        this.pessoas.setText(String.valueOf(pessoaDAO.count()));

        this.categorias.setText(String.valueOf(categoriaDAO.count()));
    }
}
