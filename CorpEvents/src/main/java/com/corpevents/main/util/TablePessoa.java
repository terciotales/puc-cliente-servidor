package com.corpevents.main.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablePessoa {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nome;
    private SimpleStringProperty username;
    private SimpleIntegerProperty role;

    public TablePessoa(int id, String nome, String username, int role) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.username = new SimpleStringProperty(username);
        this.role = new SimpleIntegerProperty(role);
    }

    public TablePessoa() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public int getRole() {
        return role.get();
    }

    public SimpleIntegerProperty roleProperty() {
        return role;
    }

    public void setRole(int role) {
        this.role.set(role);
    }
}
