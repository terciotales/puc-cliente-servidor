package com.corpevents.main.controller;

import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Pessoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.corpevents.main.util.Encryption.encryptPassword;

/**
 * Classe controladora da tela de adicionar pessoas
 */
public class FXML_PessoasEditar implements Initializable {

    private Pessoa pessoa;

    @FXML
    private Text error_message;

    @FXML
    private TextField name;

    @FXML
    private PasswordField old_password;

    @FXML
    private PasswordField new_password;

    @FXML
    private ComboBox<String> role;

    @FXML
    private TextField username;

    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll("Administrador", "Usuário padrão");
        username.setDisable(true);
        error_message.setVisible(false);
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        name.setText(pessoa.getNome());
        username.setText(pessoa.getUsername());
        role.getSelectionModel().select(pessoa.getRole() == 1 ? "Administrador" : "Usuário padrão");
    }

    @FXML
    void savePessoa(MouseEvent event) throws Exception {
        if (!checkFields()) {
            return;
        }

        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = new Pessoa();

        pessoa.setId(this.pessoa.getId());
        pessoa.setNome(name.getText());
        pessoa.setUsername(this.pessoa.getUsername());
        pessoa.setPassword(!new_password.getText().isEmpty() ? encryptPassword(new_password.getText()) : this.pessoa.getPassword());
        pessoa.setRole(Objects.equals(role.getSelectionModel().getSelectedItem(), "Administrador") ? 1 : 2);

        Alert alert;
        if (pessoaDAO.update(pessoa)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Usuário editado com sucesso!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao salvar o usuário");
            alert.showAndWait();
        }
    }


    boolean checkFields() throws Exception {
        boolean valid = true;
        PessoaDAO pessoaDAO = new PessoaDAO();

        if (name.getText().isEmpty()) {
            error_message.setText("O campo nome é obrigatório");
            valid = false;
        } else if (username.getText().isEmpty()) {
            error_message.setText("O campo usuário é obrigatório");
            valid = false;
        } else if (role.getSelectionModel().isEmpty()) {
            error_message.setText("O campo função é obrigatório");
            valid = false;
        } else if (!old_password.getText().isEmpty() || !new_password.getText().isEmpty()) {
            if (new_password.getText().isEmpty()) {
                error_message.setText("O campo nova senha é obrigatório");
                valid = false;
            } else if (new_password.getText().length() < 5) {
                error_message.setText("A nova senha deve ter no mínimo 5 caracteres");
                valid = false;
            } else if (old_password.getText().isEmpty()) {
                error_message.setText("O campo senha atual é obrigatório");
                valid = false;
            } else if (pessoaDAO.checkPassword(this.pessoa.getUsername(), encryptPassword(new_password.getText()))) {
                error_message.setText("A nova senha deve ser diferente da senha atual");
                valid = false;
            } else if (!pessoaDAO.checkPassword(this.pessoa.getUsername(), encryptPassword(old_password.getText()))) {
                error_message.setText("A senha atual está incorreta");
                valid = false;
            }
        }

        error_message.setVisible(!valid);
        return valid;
    }
}
