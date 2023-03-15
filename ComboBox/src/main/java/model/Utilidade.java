package model;

import controller.Item;

import java.util.ArrayList;

public class Utilidade {

    private static Utilidade instance;

    public final ArrayList<Item> list;

    private Item actualItem;

    public Utilidade(Item item) {
        this.actualItem = item;
        this.list = new ArrayList<>() {
            {
                add(item);
            }
        };
    }

    public static synchronized Utilidade getInstance() {
        if (instance == null) {
            instance = new Utilidade(new Item(0));
        }

        return instance;
    }

    public Item getActualItem() {
        return this.actualItem;
    }

    public void setActualItem(String nome, int codProfissao) {
        this.actualItem.pessoa.setNome(nome);
        this.actualItem.pessoa.setCodProfissao(codProfissao);
    }

    public void addNew() {
        this.actualItem = new Item(this.list.size());
        this.list.add(this.actualItem);
    }

    public void next() {
        try {
            this.actualItem = this.list.get(this.actualItem.getIndex() + 1);
        } catch (IndexOutOfBoundsException e) {
            this.addNew();
        }
    }

    public void previous() {
        if (this.actualItem.getIndex() == 0) {
            this.actualItem = this.list.get(this.list.size() - 1);
        } else {
            this.actualItem = this.list.get(this.actualItem.getIndex() - 1);
        }
    }

    public int getActualItemIndex() {
        return this.actualItem.getIndex();
    }

    public int size() {
        return this.list.size();
    }
}
