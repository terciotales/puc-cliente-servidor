package sicof.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sicof.dao.CategoriaDAO;
import sicof.model.Categoria;

public class FXML_CategoriasAdicionar {

    @FXML
    private TextField nome;

    @FXML
    void onClick(MouseEvent event) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        if (nome.getText().length() > 0) {
            Categoria categoria = new Categoria(0, nome.getText());
            boolean insert = categoriaDAO.insert(categoria);
            nome.setText("");

            Alert alert;
            if (insert) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Categoria adicionada com sucesso!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Erro ao adicionar categoria!", ButtonType.OK);
            }
            alert.showAndWait();
        }
    }
}
