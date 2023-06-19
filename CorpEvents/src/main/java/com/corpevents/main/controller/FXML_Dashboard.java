package com.corpevents.main.controller;

import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.dao.EventoDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Evento;
import com.corpevents.main.util.DateFormatter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXML_Dashboard implements Initializable {
    @FXML
    private Text categorias;

    @FXML
    private Label eventos_anteriores;

    @FXML
    private Text eventos_participando;

    @FXML
    private Text eventos_totais;

    @FXML
    private Text pessoas;

    @FXML
    private Label proximos_eventos;

    @FXML
    private VBox last_container;

    @FXML
    private VBox next_container;

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

        if (proximos.size() != 0) {
            this.next_container.getChildren().remove(2);
            for (Evento evento : proximos) {
                Label label = new Label(DateFormatter.dateFormatter(evento.getDate()) + " - " + evento.getTitle() + "\n");
                this.next_container.getChildren().add(label);
            }
        }

        ArrayList<Evento> anteriores = eventoDAO.selectLast();

        if (anteriores.size() != 0) {
            this.last_container.getChildren().remove(2);
            for (Evento evento : anteriores) {
                Label label = new Label(DateFormatter.dateFormatter(evento.getDate()) + " - " + evento.getTitle() + "\n");
                this.last_container.getChildren().add(label);
            }
        }

        this.pessoas.setText(String.valueOf(pessoaDAO.count()));

        this.categorias.setText(String.valueOf(categoriaDAO.count()));
    }
}
