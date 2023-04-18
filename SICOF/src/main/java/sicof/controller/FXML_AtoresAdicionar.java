package sicof.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sicof.dao.AtorDAO;
import sicof.model.Ator;

public class FXML_AtoresAdicionar {

    @FXML
    private TextField nome;

    @FXML
    void onClick(MouseEvent event) {
        AtorDAO atorDAO = new AtorDAO();
        if (nome.getText().length() > 0) {
            Ator ator = new Ator(0, nome.getText());
            boolean insert = atorDAO.insert(ator);
            nome.setText("");

            Alert alert;
            if (insert) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Ator adicionado com sucesso!", ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Erro ao adicionar ator!", ButtonType.OK);
            }
            alert.showAndWait();
        }
    }
}
