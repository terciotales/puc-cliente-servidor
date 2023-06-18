package com.corpevents.main.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TableEvento {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty date;
    private SimpleStringProperty author;
    private SimpleStringProperty category;
    private SimpleIntegerProperty countPeople;

    public TableEvento(int id, String title, String date, String author, String category, int countPeople) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.countPeople = new SimpleIntegerProperty(countPeople);
    }

    public TableEvento() {
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

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getCountPeople() {
        return countPeople.get();
    }

    public SimpleIntegerProperty countPeopleProperty() {
        return countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople.set(countPeople);
    }
}
