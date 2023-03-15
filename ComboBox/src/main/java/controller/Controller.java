package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Profissao;
import model.Utilidade;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // Singleton da classe Utilidade
    private final Utilidade utilidade = Utilidade.getInstance();

    @FXML
    private ComboBox<Profissao> combobox_role;

    @FXML
    private TextField txt_name;

    public void initialize(URL location, ResourceBundle resources) {
        setComboboxRoles();
    }

    /**
     * Método para adicionar um novo item
     */
    @FXML
    void onChange(MouseEvent event) {
        String name = txt_name.getText();
        int codProfissao = combobox_role.getSelectionModel().getSelectedItem().codigo();

        utilidade.setActualItem(name, codProfissao);
    }

    /**
     * Método para avançar para o próximo item
     */
    @FXML
    void onNext(MouseEvent event) {
        utilidade.next();
        setController();
    }

    /**
     * Método para voltar para o item anterior
     */
    @FXML
    void onPrevious(MouseEvent event) {
        utilidade.previous();
        setController();
    }

    /**
     * Método para popular os campos de texto com os dados do item atual
     */
    private void setController() {
        txt_name.setText(utilidade.getActualItem().pessoa.getNome());
        combobox_role.getSelectionModel().select(utilidade.getActualItem().pessoa.getCodProfissao());
    }

    /**
     * Método para popular o combobox de profissões
     */
    public void setComboboxRoles() {
        // Lista de objetos Profissao
        ObservableList<Profissao> elements = FXCollections.observableArrayList(
                new Profissao(0, "Selecione uma profissão"),
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
                new Profissao(20, "Fotógrafo")
        );

        // Converter para exibir a descrição do objeto Profissao no combobox
        combobox_role.setConverter(new StringConverter<>() {
            @Override
            public String toString(Profissao object) {
                return object.descricao();
            }

            @Override
            public Profissao fromString(String string) {
                return null;
            }
        });

        // Adiciona os elementos ao combobox
        combobox_role.setItems(elements);

        // Seleciona o primeiro elemento
        combobox_role.getSelectionModel().selectFirst();
    }
}