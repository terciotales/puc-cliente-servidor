package controller;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Pessoa;
import model.Profissao;
import model.Utilidade;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Item item = new Item(0);

    private Utilidade utilidade = new Utilidade(item);
    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btn_change;

    @FXML
    private Button btn_next;

    @FXML
    private Button btn_previous;

    @FXML
    private ComboBox<Profissao> combobox_role;

    @FXML
    private TextField txt_name;


    public void initialize(URL location, ResourceBundle resources) {
        setCombobox_roles();
    }

    @FXML
    void onClick(MouseEvent event) {
        String nome = txt_name.getText();
        int codProfissao = combobox_role.getValue().getCodigo();

        System.out.println(codProfissao);
    }

    @FXML
    void onNext(MouseEvent event) {
        item = new Item(utilidade.size());
        utilidade.add(item);
    }

    @FXML
    void onPrevious(MouseEvent event) {
        item = utilidade.get(item.getId() - 1);
    }

    private void setCombobox_roles() {
        ObservableList<Profissao> elements = FXCollections.observableArrayList(
                new Profissao(1, "Programador"),
                new Profissao(2, "Analista"),
                new Profissao(3, "Designer"),
                new Profissao(4, "Engenheiro"),
                new Profissao(5, "Arquiteto"),
                new Profissao(6, "Mecânico"),
                new Profissao(7, "Eletricista"),
                new Profissao(8, "Médico"),
                new Profissao(9, "Professor"),
                new Profissao(10, "Enfermeiro"),
                new Profissao(11, "Bombeiro"),
                new Profissao(12, "Advogado"),
                new Profissao(13, "Policial"),
                new Profissao(14, "Motorista"),
                new Profissao(15, "Cabeleireiro"),
                new Profissao(16, "Pedreiro"),
                new Profissao(17, "Jardineiro"),
                new Profissao(18, "Padeiro"),
                new Profissao(19, "Pintor"),
                new Profissao(20, "Fotógrafo"),
                new Profissao(21, "Jornalista"),
                new Profissao(22, "Escritor"),
                new Profissao(23, "Ator"),
                new Profissao(24, "Cantor"),
                new Profissao(25, "Dançarino"),
                new Profissao(26, "Cientista"),
                new Profissao(27, "Arqueólogo"),
                new Profissao(28, "Músico"),
                new Profissao(29, "Físico"),
                new Profissao(30, "Matemático"),
                new Profissao(31, "Químico"),
                new Profissao(32, "Biólogo"),
                new Profissao(33, "Historiador"),
                new Profissao(34, "Geógrafo"),
                new Profissao(35, "Filósofo"),
                new Profissao(36, "Psicólogo"),
                new Profissao(37, "Economista"),
                new Profissao(38, "Administrador"),
                new Profissao(39, "Contador"),
                new Profissao(40, "Militar"),
                new Profissao(41, "Político"),
                new Profissao(42, "Empresário")
        );

        combobox_role.setConverter(new StringConverter<>() {
            @Override
            public String toString(Profissao object) {
                return object.getDescricao();
            }

            @Override
            public Profissao fromString(String string) {
                return null;
            }
        });

        combobox_role.setItems(elements);
        combobox_role.getSelectionModel().selectFirst();
    }
}