package com.corpevents.main.model;

public class Pessoa {
    private int id;
    private String nome;
    private String username;
    private String password;
    private int role;

    public Pessoa(int id, String nome, String username, String password, int role) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Pessoa() {
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return nome;
    }
}
