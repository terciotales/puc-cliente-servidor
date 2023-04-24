package sicof.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sicof.dao.CategoriaDAO;
import sicof.main.Main;
import sicof.model.Categoria;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXML_CategoriasEditar implements Initializable {

    private Categoria categoria;

    @FXML
    private TextField new_name;

    @FXML
    private AnchorPane editar_categoria_root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void save(MouseEvent event) {
        CategoriaDAO cateogoriaDAO = new CategoriaDAO();
        Categoria categoria = new Categoria();

        if (new_name.getText().length() > 0) {
            categoria.setName(new_name.getText());
            categoria.setId(this.categoria.getId());

            Alert alert;
            if (cateogoriaDAO.update(categoria)) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Categoria alterada com sucesso!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Erro ao alterar categoria!", ButtonType.OK);
            }
            alert.showAndWait();
        }
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        this.new_name.setText(categoria.getName());
    }

    public void cancelEdit(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cancelar a edição?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO) {
            return;
        }

        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXML_CategoriasListar.fxml"));
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BorderPane borderPane = (BorderPane) editar_categoria_root.getParent();
        borderPane.setCenter(root);
    }
}
