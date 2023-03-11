package controle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controle implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private TextField txt;

    @FXML
    void btnOnAction(ActionEvent event) {
        txt.setText("Alterei o texto da caixa de texto!");
    }

    public void initialize(URL location, ResourceBundle resources) {
       verificaTexto();
    }

    public void verificaTexto() {
        txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mensagem de aviso");
                    alert.setHeaderText("Caixa de texto");
                    alert.setContentText("A caixa de texto perdeu o foco!");
                    alert.showAndWait();
                }
            }
        });
    }

}
