package com.corpevents.main.model;

import com.corpevents.main.dao.EventoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public abstract class Relatorio {
    private static JasperReport jasperReport;
    private static JasperPrint jasperPrint;
    private static JasperViewer jasperViewer;

    public static void createReport(Connection connection, Map<String, Object> map, InputStream inputStream) {
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showReport() {
        jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }

    public ObservableList<Evento> getEventos() {
        ObservableList<Evento> eventos = FXCollections.observableArrayList();
        try {
            EventoDAO eventoDAO = new EventoDAO();
            eventos.addAll(eventoDAO.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventos;
    }
}
