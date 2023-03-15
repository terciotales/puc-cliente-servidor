package model;

import controller.Item;

import java.util.ArrayList;

public class Utilidade {
    public final ArrayList<Item> list;

    public Utilidade(Item item) {
        this.list = new ArrayList<>() {
            {
                add(item);
            }
        };
    }

    public void add(Item item) {
        this.list.add(item);
    }

    public void remove(Item item) {
        this.list.remove(item);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public Item get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }
}
