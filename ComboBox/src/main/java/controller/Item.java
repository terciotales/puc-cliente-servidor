package controller;

import model.Pessoa;
import model.Profissao;
import model.Utilidade;

public class Item {
    int index;

    public Pessoa pessoa;

    Profissao profissao;

    public Item(int index) {
        this.index = index;
        this.pessoa = new Pessoa("", 0);
        this.profissao = new Profissao(0, "");
    }

    public Item(int index, Pessoa pessoa, Profissao profissao) {
        this.index = index;
        this.pessoa = pessoa;
        this.profissao = profissao;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public int getIndex() {
        return index;
    }
}
