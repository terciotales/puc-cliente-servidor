package sicof.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sicof.dao.AtorDAO;
import sicof.dao.CategoriaDAO;
import sicof.model.Ator;
import sicof.model.Categoria;

import java.net.URL;
import java.util.ResourceBundle;

public class FXML_CategoriasEditar implements Initializable {

    @FXML
    private ComboBox<Categoria> combo_box;

    @FXML
    private TextField new_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CategoriaDAO cateogoriaDAO = new CategoriaDAO();
        cateogoriaDAO.getAll().forEach(categoria -> {
            combo_box.getItems().add(categoria);
        });
    }

    @FXML
    void save(MouseEvent event) {
        CategoriaDAO cateogoriaDAO = new CategoriaDAO();
        Categoria categoria = combo_box.getValue();

        if (new_name.getText().length() > 0) {
            categoria.setName(new_name.getText());
            boolean update = cateogoriaDAO.update(categoria);
            new_name.setText("");
            Alert alert;
            if (update) {
                combo_box.getItems().clear();
                cateogoriaDAO.getAll().forEach(ator1 -> {
                    combo_box.getItems().add(ator1);
                });

                alert = new Alert(Alert.AlertType.INFORMATION, "Categoria alterada com sucesso!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Erro ao alterar categoria!", ButtonType.OK);
            }
            alert.showAndWait();
        }

    }

    @FXML
    void exclude(MouseEvent event) {
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir a categoria?", ButtonType.YES, ButtonType.NO);

        if (alert.showAndWait().get() == ButtonType.NO) {
            return;
        }

        CategoriaDAO cateogoriaDAO = new CategoriaDAO();
        Categoria categoria = combo_box.getValue();
        boolean delete = cateogoriaDAO.delete(categoria);
        new_name.setText("");

        if (delete) {
            combo_box.getItems().clear();
            cateogoriaDAO.getAll().forEach(categoria1 -> {
                combo_box.getItems().add(categoria1);
            });
            alert = new Alert(Alert.AlertType.INFORMATION, "Categoria excluída com sucesso!", ButtonType.OK);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Erro ao excluir categoria!", ButtonType.OK);
        }
        alert.showAndWait();
    }
}
