package sicof.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import sicof.dao.AtorDAO;
import sicof.main.Main;
import sicof.model.Ator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXML_AtoresListar implements Initializable {

    @FXML
    private ListView<Ator> list;

    public void initialize(URL location, ResourceBundle resources) {
        AtorDAO atorDAO = new AtorDAO();
        ArrayList<Ator> atores = atorDAO.getAll();
        list.getItems().addAll(atores);


    }
}
