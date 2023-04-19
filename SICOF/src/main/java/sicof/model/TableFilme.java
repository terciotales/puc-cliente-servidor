package sicof.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Date;

public class TableFilme {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty releaseDate;
    private SimpleStringProperty category;
    private SimpleStringProperty actors;

    public TableFilme() {
    }

    public TableFilme(int id, String title, Date releaseDate, Categoria category, ArrayList<Ator> actors) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.releaseDate = new SimpleStringProperty(releaseDate.toString());
        this.category = new SimpleStringProperty(category.getName());
        this.actors = new SimpleStringProperty(actors.toString());
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

    public String getReleaseDate() {
        return releaseDate.get();
    }

    public SimpleStringProperty releaseDateProperty() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.set(releaseDate);
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

    public String getActors() {
        return actors.get();
    }

    public SimpleStringProperty actorsProperty() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors.set(actors);
    }

    public String toString() {
        return getTitle();
    }
}
