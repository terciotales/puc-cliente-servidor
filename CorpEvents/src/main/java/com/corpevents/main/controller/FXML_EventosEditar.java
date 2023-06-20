package com.corpevents.main.controller;

import com.corpevents.main.Main;
import com.corpevents.main.dao.CategoriaDAO;
import com.corpevents.main.dao.EventoDAO;
import com.corpevents.main.dao.EventoPessoaDAO;
import com.corpevents.main.dao.PessoaDAO;
import com.corpevents.main.model.Categoria;
import com.corpevents.main.model.Evento;
import com.corpevents.main.model.Pessoa;
import com.corpevents.main.util.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneOffset;
import java.util.*;

import static com.corpevents.main.util.DateFormatter.*;
import static com.corpevents.main.util.TextFieldUtils.numberTextField;

/**
 * Classe controladora da tela de editar eventos
 */
public class FXML_EventosEditar implements Initializable {

    private Evento evento;

    @FXML
    private AnchorPane editar_evento_root;

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

    public void setEvento(Evento evento) {
        this.evento = evento;

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        EventoPessoaDAO eventoPessoaDAO = new EventoPessoaDAO();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(toDate(evento.getDate())));

        this.title.setText(evento.getTitle());
        this.description.setText(evento.getDescription());
        this.local.setText(evento.getLocal());
        this.date.setValue(toLocalDate(evento.getDate()));
        this.horas.setText(twoDigits(calendar.get(Calendar.HOUR_OF_DAY)));
        this.minutos.setText(twoDigits(calendar.get(Calendar.MINUTE)));
        this.category.setValue(categoriaDAO.selectById(evento.getCategory()));
        this.list_pessoas.getItems().addAll(eventoPessoaDAO.selectByEvento(evento.getId()));

        PessoaDAO pessoaDAO = new PessoaDAO();
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        pessoaDAO.selectAll().forEach(pessoa -> {
            if (pessoa.getId() != this.evento.getAuthor()) {
                pessoas.add(pessoa);
            }
        });

        ObservableList<Pessoa> observableList = FXCollections.observableArrayList(pessoas);
        this.pessoa.setItems(observableList);
    }

    public void initialize(URL location, ResourceBundle resources) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categoria> categorias = categoriaDAO.selectAll();
        ObservableList<Categoria> observableList2 = FXCollections.observableArrayList(categorias);
        this.category.setItems(observableList2);

        remove_pessoa.setDisable(true);
        add_pessoa.setDisable(true);

        this.list_pessoas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            remove_pessoa.setDisable(newValue == null);
        });

        numberTextField(this.horas, 23);
        numberTextField(this.minutos, 59);

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

        this.evento.setTitle(this.title.getText());
        this.evento.setDescription(this.description.getText());
        this.evento.setLocal(this.local.getText());
        this.evento.setCategory(this.category.getValue().getId());
        this.evento.setDate(calendarToString(calendar));
        this.evento.setAuthor(this.evento.getAuthor());
        this.evento.setPessoas(new ArrayList<>());
        this.list_pessoas.getItems().forEach(evento::addPessoa);

        Alert alert;
        if (eventoDAO.update(evento)) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Evento editado com sucesso!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao editar evento!");
            alert.showAndWait();
        }
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

    @FXML
    void cancelEdit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancelar edição");
        alert.setHeaderText("Você tem certeza que deseja cancelar a edição?");
        alert.setContentText("Todos os dados não salvos serão perdidos!");

        ButtonType buttonTypeYes = new ButtonType("Sim");
        ButtonType buttonTypeNo = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeYes) {
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/FXML_EventosListar.fxml"));
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BorderPane borderPane = (BorderPane) editar_evento_root.getParent();
                borderPane.setCenter(root);

                for (Node node : borderPane.getChildren()) {
                    if (node.getStyleClass().contains("header-menu") && Objects.equals(node.getTypeSelector(), "HBox")) {
                        for (Node child : ((HBox) node).getChildren()) {
                            if (Objects.equals(child.getTypeSelector(), "HBox")) {
                                for (Node button : ((HBox) child).getChildren()) {
                                    if (Objects.equals(button.getTypeSelector(), "Button") && Objects.equals(((Button) button).getText(), "Listar")) {
                                        button.getStyleClass().add("active");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

//    @FXML
//    void onComboAction(ActionEvent event) {
//        if (this.actor.getValue() == null) {
//            add_ator.setDisable(true);
//            return;
//        }
//
//        for (Ator ator : this.list_actors.getItems()) {
//            if (ator.getId() == this.actor.getValue().getId()) {
//                add_ator.setDisable(true);
//                return;
//            }
//        }
//
//        Ator ator = this.actor.getValue();
//        add_ator.setDisable(this.list_actors.getItems().contains(ator));
//    }
//
//    @FXML
//    void addActor(MouseEvent event) {
//        Ator ator = this.actor.getValue();
//
//        if (ator != null) {
//            this.list_actors.getItems().add(ator);
//            this.actor.setValue(null);
//        }
//    }
//
//    @FXML
//    void removeActor(MouseEvent event) {
//        Ator ator = this.list_actors.getSelectionModel().getSelectedItem();
//        if (ator != null) {
//            this.list_actors.getItems().remove(ator);
//        }
//    }
//
//    @FXML
//    void saveFilme(MouseEvent event) {
//        String title = this.title.getText();
//        ZoneOffset zoneOffset = ZoneOffset.ofHours(0);
//        Date releaseDate;
//        Categoria category = this.category.getValue();
//        ArrayList<Ator> actors = new ArrayList<>(this.list_actors.getItems());
//
//        if (title.length() == 0 || this.release_date.getValue() == null || category == null || actors.size() == 0) {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Revise os dados!", ButtonType.OK);
//            alert.showAndWait();
//            return;
//        }
//
//        releaseDate = Date.from(this.release_date.getValue().atStartOfDay().toInstant(zoneOffset));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(releaseDate);
//        calendar.add(Calendar.DATE, 1);
//        releaseDate = calendar.getTime();
//        Filme filme = new Filme(this.filme.getId(), title, releaseDate, category, actors);
//        FilmeDAO filmeDAO = new FilmeDAO();
//        AtorFilmes atorFilmes;
//
//        Alert alert;
//        if (filmeDAO.update(filme)) {
//            AtorFilmesDAO atorFilmesDAO = new AtorFilmesDAO();
//            for (Ator ator : filme.getActors()) {
//                if (!this.filme.getActors().contains(ator)) {
//                    atorFilmes = new AtorFilmes(ator, filme);
//                    atorFilmesDAO.insert(atorFilmes);
//                }
//            }
//
//            for (Ator ator : this.filme.getActors()) {
//                if (!filme.getActors().contains(ator)) {
//                    atorFilmes = new AtorFilmes(ator, filme);
//                    atorFilmesDAO.delete(atorFilmes);
//                }
//            }
//
//            alert = new Alert(Alert.AlertType.INFORMATION, "Filme editado com sucesso!", ButtonType.OK);
//        } else {
//            alert = new Alert(Alert.AlertType.ERROR, "Erro ao editar filme!", ButtonType.OK);
//        }
//
//        alert.showAndWait();
//    }
//
//    public void cancelEdit(MouseEvent mouseEvent) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cancelar a edição?", ButtonType.YES, ButtonType.NO);
//        alert.showAndWait();
//
//        if (alert.getResult() == ButtonType.NO) {
//            return;
//        }
//
//        Parent root = null;
//        try {
//            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXML_FilmesListar.fxml"));
//            root = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        BorderPane borderPane = (BorderPane) editar_filme_root.getParent();
//        borderPane.setCenter(root);
//
//        for (Node node : borderPane.getChildren()) {
//            if (node.getStyleClass().contains("header-menu") && Objects.equals(node.getTypeSelector(), "HBox")) {
//                for (Node child : ((HBox) node).getChildren()) {
//                    if (Objects.equals(child.getTypeSelector(), "HBox")) {
//                        for (Node button : ((HBox) child).getChildren()) {
//                            if (Objects.equals(button.getTypeSelector(), "Button") && Objects.equals(((Button) button).getText(), "Listar")) {
//                                button.getStyleClass().add("active");
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}
