package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Profissao;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {

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
        dataInstance();
    }

    private void dataInstance() {
        ObservableList<Object> data = FXCollections.observableArrayList();
        data.add(txt_name.getText());
        data.add(combobox_role.getSelectionModel().getSelectedItem());
        Data.getInstance(data);

        System.out.println(Data.getInstance(data).getData().get(0));
    }

    private void setCombobox_roles() {
        ObservableList<Profissao> elements = FXCollections.observableArrayList(
                new Profissao(1, "Programador"),
                new Profissao(2, "Analista"),
                new Profissao(3, "Designer"),
                new Profissao(4, "Engenheiro")
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