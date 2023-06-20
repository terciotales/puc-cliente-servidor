package com.corpevents.main.controller;

import com.corpevents.main.Main;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Pessoa;
import com.corpevents.main.util.Encryption;
import com.corpevents.main.util.Usuario;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe controladora da tela de login
 */
public class FXML_Login implements Initializable {
    private Usuario usuario;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField username_field;

    @FXML
    private Text message_error;

    @FXML
    private HBox dragArea;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void initialize(URL location, ResourceBundle resources) {
        usuario = Usuario.getInstance();
        message_error.setVisible(false);

        dragArea.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = Main.getMainStage().getX() - event.getScreenX();
                yOffset = Main.getMainStage().getY() - event.getScreenY();
            }
        });

        dragArea.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.getMainStage().setX(event.getScreenX() + xOffset);
                Main.getMainStage().setY(event.getScreenY() + yOffset);
            }
        });
    }

    /**
     * Fecha a aplicação.
     *
     * @param event Evento de clique do mouse.
     */
    @FXML
    void closeApplication(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Minimiza a aplicação.
     * @param event Evento de clique do mouse.
     */
    @FXML
    void minimizeApplication(MouseEvent event) {
        ((Stage) Main.getMainStage().getScene().getWindow()).setIconified(true);
    }

    /**
     * Tenta fazer login com os dados inseridos nos campos de texto.
     *
     * @param event Evento de clique do mouse.
     * @throws Exception Exceção lançada caso não seja possível fazer login.
     */
    @FXML
    void tryLogin(MouseEvent event) throws Exception {
        if (this.username_field.getText().isEmpty() || this.password_field.getText().isEmpty()) {
            if (this.username_field.getText().isEmpty() && this.password_field.getText().isEmpty()) {
                message_error.setText("* Preencha os campos de usuário e senha.");
            } else if (this.username_field.getText().isEmpty()) {
                message_error.setText("* Preencha o campo de usuário.");
            } else {
                message_error.setText("* Preencha o campo de senha.");
            }

            message_error.setVisible(true);
            usuario.setIsLogged(false);
            return;
        }

        PessoaDAO pessoaDAO = new PessoaDAO();
        ArrayList<Pessoa> pessoas = pessoaDAO.selectAll();

        String password = this.password_field.getText();
        String username = this.username_field.getText();

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getUsername().equals(username)) {
                if (pessoa.getPassword().equals(Encryption.encryptPassword(password))) {
                    usuario.setIsLogged(true);
                    usuario.setPessoa(pessoa);
                    Main.changeScene("FXML_View.fxml");
                    return;
                }
            }
        }

        message_error.setText("* Usuário ou senha estão incorretos.");
        message_error.setVisible(true);
        usuario.setIsLogged(false);
    }
}
