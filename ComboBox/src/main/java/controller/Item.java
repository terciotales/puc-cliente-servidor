package controller;

import model.Pessoa;
import model.Profissao;

public class Item {
    int index;

    public Pessoa pessoa;

    public Profissao profissao;

    public Item(int index) {
        this.index = index;
        this.pessoa = new Pessoa("", 0);
        this.profissao = new Profissao(0, "");
    }

    public int getIndex() {
        return index;
    }
}
