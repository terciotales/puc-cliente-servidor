package sicof.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sicof.dao.AtorDAO;
import sicof.model.Ator;

import java.net.URL;
import java.util.ResourceBundle;

public class FXML_AtoresEditar implements Initializable {

    @FXML
    private ComboBox<Ator> combo_box;

    @FXML
    private TextField new_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AtorDAO atorDAO = new AtorDAO();
        atorDAO.getAll().forEach(ator -> {
            combo_box.getItems().add(ator);
        });
    }

    @FXML
    void save(MouseEvent event) {
        AtorDAO atorDAO = new AtorDAO();
        Ator ator = combo_box.getValue();

        if (new_name.getText().length() > 0) {
            ator.setName(new_name.getText());
            boolean update = atorDAO.update(ator);
            new_name.setText("");
            Alert alert;
            if (update) {
                combo_box.getItems().clear();
                atorDAO.getAll().forEach(ator1 -> {
                    combo_box.getItems().add(ator1);
                });

                alert = new Alert(Alert.AlertType.INFORMATION, "Ator alterado com sucesso!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Erro ao alterar ator!", ButtonType.OK);
            }
            alert.showAndWait();
        }

    }

    @FXML
    void exclude(MouseEvent event) {
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir o ator?", ButtonType.YES, ButtonType.NO);

        if (alert.showAndWait().get() == ButtonType.NO) {
            return;
        }

        AtorDAO atorDAO = new AtorDAO();
        Ator ator = combo_box.getValue();
        boolean delete = atorDAO.delete(ator);
        new_name.setText("");

        if (delete) {
            combo_box.getItems().clear();
            atorDAO.getAll().forEach(ator1 -> {
                combo_box.getItems().add(ator1);
            });
            alert = new Alert(Alert.AlertType.INFORMATION, "Ator excluído com sucesso!", ButtonType.OK);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Erro ao excluir ator!", ButtonType.OK);
        }
        alert.showAndWait();
    }
}
