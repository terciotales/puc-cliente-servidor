package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Data {
    private static Data instance;
    public ObservableList<Object> data = FXCollections.observableArrayList();

    private Data(ObservableList<Object> data) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.data = data;
    }

    public static Data getInstance(ObservableList<Object> data) {
        if (instance == null) {
            instance = new Data(data);
        }
        return instance;
    }

    public ObservableList<Object> getData() {
        return data;
    }

    public void setData(ObservableList<Object> data) {
        this.data = data;
    }

    public void addData(Object data) {
        this.data.add(data);
    }

    public void removeData(Object data) {
        this.data.remove(data);
    }
}
