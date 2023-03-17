package model;

import controller.Item;

import java.util.ArrayList;

public class Utilidade {

    // Singleton da classe Utilidade
    private static Utilidade instance;

    // Lista de itens
    private final ArrayList<Item> list;

    // Item atual
    private Item actualItem = new Item(0);

    // Construtor privado
    private Utilidade() {
        this.list = new ArrayList<>() {
            {
                add(actualItem);
            }
        };
    }

    // Método para retornar a instância da classe
    public static synchronized Utilidade getInstance() {
        if (instance == null) {
            instance = new Utilidade();
        }

        return instance;
    }

    // Método para retornar o item atual
    public Item getActualItem() {
        return this.actualItem;
    }

    // Método para setar o item atual
    public void setActualItem(String nome, int codProfissao) {
        this.actualItem.pessoa.setNome(nome);
        this.actualItem.pessoa.setCodProfissao(codProfissao);
    }

    // Método para adicionar um novo item
    public void addNewItem() {
        this.actualItem = new Item(this.list.size());
        this.list.add(this.actualItem);
    }

    // Método para avançar para o próximo item
    public void nextItem() {
        // Se o item atual for o último da lista, adiciona um novo item
        try {
            this.actualItem = this.list.get(this.actualItem.getIndex() + 1);
        } catch (IndexOutOfBoundsException e) {
            this.addNewItem();
        }
    }

    // Método para voltar para o item anterior
    public void previousItem() {
        // Se o item atual não for o primeiro da lista, volta para o item anterior
        if (this.actualItem.getIndex() != 0) {
            this.actualItem = this.list.get(this.actualItem.getIndex() - 1);
        }
    }
}
