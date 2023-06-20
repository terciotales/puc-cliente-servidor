package com.corpevents.main.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FXML_RelatoriosSimples implements Initializable {

    @FXML
    private CheckBox check_categorias;

    @FXML
    private CheckBox check_eventos;

    @FXML
    private CheckBox check_pessoas;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void gerarRelatorio(MouseEvent event) {

    }
}
