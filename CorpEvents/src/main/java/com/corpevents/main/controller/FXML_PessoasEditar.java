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


public class FXML_PessoasAdicionar implements Initializable {

    @FXML
    private PasswordField check_password;

    @FXML
    private Text error_message;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<String> role;

    @FXML
    private TextField username;

    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll("Administrador", "Usuário padrão");
        error_message.setVisible(false);
    }

    @FXML
    void savePessoa(MouseEvent event) throws Exception {
        if (!checkFields()) {
            return;
        }

        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(name.getText());
        pessoa.setUsername(username.getText());
        pessoa.setPassword(encryptPassword(password.getText()));
        pessoa.setRole(Objects.equals(role.getSelectionModel().getSelectedItem(), "Administrador") ? 1 : 2);

        Alert alert;
        if (pessoaDAO.insert(pessoa)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Usuário adicionado com sucesso!");
            alert.showAndWait();
            clearFields();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao adicionar o usuário");
            alert.showAndWait();
        }
    }


    boolean checkFields() {
        boolean valid = true;
        PessoaDAO pessoaDAO = new PessoaDAO();

        if (name.getText().isEmpty()) {
            error_message.setText("O campo nome é obrigatório");
            valid = false;
        } else if (username.getText().isEmpty()) {
            error_message.setText("O campo usuário é obrigatório");
            valid = false;
        } else if (password.getText().isEmpty()) {
            error_message.setText("O campo senha é obrigatório");
            valid = false;
        } else if (check_password.getText().isEmpty()) {
            error_message.setText("O campo confirmação de senha é obrigatório");
            valid = false;
        } else if (!password.getText().equals(check_password.getText())) {
            error_message.setText("As senhas não coincidem");
            valid = false;
        } else if (role.getSelectionModel().isEmpty()) {
            error_message.setText("O campo perfil é obrigatório");
            valid = false;
        } else if (pessoaDAO.checkUsername(username.getText())) {
            error_message.setText("O usuário já existe");
            valid = false;
        }

        error_message.setVisible(!valid);
        return valid;
    }

    void clearFields() {
        name.setText("");
        username.setText("");
        password.setText("");
        check_password.setText("");
        role.getSelectionModel().clearSelection();
    }
}
