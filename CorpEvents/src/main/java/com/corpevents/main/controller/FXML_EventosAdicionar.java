package com.corpevents.main.controller;

import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.dao.EventoDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Categoria;
import com.corpevents.main.model.Evento;
import com.corpevents.main.model.Pessoa;
import com.corpevents.main.util.DateFormatter;
import com.corpevents.main.util.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class FXML_EventosAdicionar implements Initializable {

    @FXML
    private ComboBox<Pessoa> pessoa;

    @FXML
    private ComboBox<Categoria> category;

    @FXML
    private ListView<Pessoa> list_pessoas;

    @FXML
    private DatePicker date;

    @FXML
    private TextField horas;

    @FXML
    private TextField minutos;

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private TextArea local;

    @FXML
    private Button add_pessoa;

    @FXML
    private Button remove_pessoa;

    @FXML
    private Text error_message;

    public void initialize(URL location, ResourceBundle resources) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        pessoaDAO.selectAll().forEach(pessoa -> {
            if (pessoa.getId() != Usuario.getInstance().getPessoa().getId()) {
                pessoas.add(pessoa);
            }
        });

        ObservableList<Pessoa> observableList = FXCollections.observableArrayList(pessoas);
        this.pessoa.setItems(observableList);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categoria> categorias = categoriaDAO.selectAll();
        ObservableList<Categoria> observableList2 = FXCollections.observableArrayList(categorias);
        this.category.setItems(observableList2);

        remove_pessoa.setDisable(true);
        add_pessoa.setDisable(true);

        this.list_pessoas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            remove_pessoa.setDisable(newValue == null);
        });

        numberTextField(this.horas);
        numberTextField(this.minutos);

        this.error_message.setVisible(false);
    }

    @FXML
    void onComboAction(ActionEvent event) {
        if (this.pessoa.getValue() == null) {
            add_pessoa.setDisable(true);
            return;
        }

        for (Pessoa person : this.list_pessoas.getItems()) {
            if (person.getId() == this.pessoa.getValue().getId()) {
                add_pessoa.setDisable(true);
                return;
            }
        }

        Pessoa person = this.pessoa.getValue();
        add_pessoa.setDisable(this.list_pessoas.getItems().contains(person));
    }

    @FXML
    void addPessoa(MouseEvent event) {
        Pessoa person = this.pessoa.getValue();
        if (person != null && !this.list_pessoas.getItems().contains(person)) {
            this.list_pessoas.getItems().add(person);
            this.pessoa.setValue(null);
        }
    }

    @FXML
    void removeActor(MouseEvent event) {
        Pessoa person = this.list_pessoas.getSelectionModel().getSelectedItem();
        if (person != null) {
            this.list_pessoas.getItems().remove(person);
        }
    }

    @FXML
    void saveEvento(MouseEvent event) {
        if (!checkFields()) {
            return;
        }

        EventoDAO eventoDAO = new EventoDAO();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(this.date.getValue().atStartOfDay().toInstant(ZoneOffset.UTC)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(this.horas.getText()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(this.minutos.getText()));

        Evento evento = new Evento();
        evento.setTitle(this.title.getText());
        evento.setDescription(this.description.getText());
        evento.setLocal(this.local.getText());
        evento.setCategory(this.category.getValue().getId());
        evento.setDate(calendarToString(calendar));
        evento.setAuthor(Usuario.getInstance().getPessoa().getId());
        this.list_pessoas.getItems().forEach(evento::addPessoa);

        eventoDAO.insert(evento);
    }

    String calendarToString(Calendar calendar) {
        return calendar.get(Calendar.YEAR) + "-" + twoDigits(calendar.get(Calendar.MONTH) + 1) + "-" + twoDigits(calendar.get(Calendar.DAY_OF_MONTH) + 1) + " " + twoDigits(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + twoDigits(calendar.get(Calendar.MINUTE)) + ":" + "00";
    }

    String twoDigits(int number) {
        return number < 10 ? "0" + number : "" + number;
    }

    boolean checkFields() {
        boolean valid = true;

        if (this.title.getText().length() == 0) {
            this.title.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.title.setStyle("-fx-border-width: 0px;");
        }

        if (this.description.getText().length() == 0) {
            this.description.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.description.setStyle("-fx-border-width: 0px;");
        }

        if (this.category.getValue() == null) {
            this.category.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.category.setStyle("-fx-border-width: 0px;");
        }

        if (this.list_pessoas.getItems().size() == 0) {
            this.list_pessoas.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.list_pessoas.setStyle("-fx-border-width: 0px;");
        }

        if (this.horas.getText().length() == 0) {
            this.horas.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.horas.setStyle("-fx-border-width: 0px;");
        }

        if (this.minutos.getText().length() == 0) {
            this.minutos.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.minutos.setStyle("-fx-border-width: 0px;");
        }

        if (this.date.getValue() == null) {
            this.date.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.date.setStyle("-fx-border-width: 0px;");
        }

        if (this.local.getText().length() == 0) {
            this.local.setStyle("-fx-border-color: #ED315C; -fx-border-width: 1px;");
            valid = false;
        } else {
            this.local.setStyle("-fx-border-width: 0px;");
        }

        this.error_message.setVisible(!valid);

        return valid;
    }

    void numberTextField(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 0 || newValue.equals("0")) {
                return;
            }

            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
                return;
            }

            if (textField.getText().length() > 2) {
                textField.setText(textField.getText().substring(0, 2));
            }

            if (textField == this.horas && Integer.parseInt(textField.getText()) > 23) {
                textField.setText("23");
            }

            if (textField == this.minutos && Integer.parseInt(textField.getText()) > 59) {
                textField.setText("59");
            }
        });
    }
}
