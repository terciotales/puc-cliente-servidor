package controller;

import model.Pessoa;
import model.Profissao;
import model.Utilidade;

public class Item {
    int id;

    Pessoa pessoa;

    Profissao profissao;

    public Item(int id) {
        this.id = id;
        this.pessoa = new Pessoa("", 0);
        this.profissao = new Profissao(0, "");
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public int getId() {
        return id;
    }
}
