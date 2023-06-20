package com.corpevents.main.controller;

import com.corpevents.main.connection.DBConnection;
import com.corpevents.main.dao.RelatoriosDAO;
import com.corpevents.main.model.Relatorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FXML_RelatoriosSimples implements Initializable {

    @FXML
    private CheckBox check_categorias;

    @FXML
    private CheckBox check_eventos;

    @FXML
    private CheckBox check_pessoas;

    private DBConnection database;
    private Connection connection;
    private Map<String, Object> map;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void gerarRelatorio(MouseEvent event) throws SQLException {
        printReport();
    }

    void printReport() throws SQLException {
        RelatoriosDAO relatoriosDAO = new RelatoriosDAO();
        database = new DBConnection();
        connection = database.getConnection();
        map = new HashMap<>();

        Relatorio.createReport(connection, map, relatoriosDAO.getReport("Simples"));
        Relatorio.showReport();
    }
}
