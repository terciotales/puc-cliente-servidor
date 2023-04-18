package sicof.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sicof.dao.CategoriaDAO;
import sicof.model.Categoria;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXML_CategoriasListar implements Initializable {

    @FXML
    private ListView<Categoria> list;

    public void initialize(URL location, ResourceBundle resources) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categoria> categorias = categoriaDAO.getAll();
        list.getItems().addAll(categorias);
    }
}
