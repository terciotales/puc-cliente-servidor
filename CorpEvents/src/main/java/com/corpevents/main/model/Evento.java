package com.corpevents.main.model;

import java.util.ArrayList;

/**
 * Classe modelo da tabela eventos
 */
public class Evento {
    private int id;
    private String title;
    private String description;
    private String date;
    private int author;
    private int category;
    private String local;
    private ArrayList<Pessoa> pessoas = new ArrayList<>();

    public Evento(int id, String title, String description, String date, int author, int category, String local, ArrayList<Pessoa> pessoas) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.author = author;
        this.category = category;
        this.local = local;
        this.pessoas = pessoas;
    }

    public Evento() {
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", local='" + local + '\'' +
                '}';
    }
}
