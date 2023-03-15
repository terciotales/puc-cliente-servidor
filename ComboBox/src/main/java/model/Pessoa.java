package model;

public class Pessoa {
    String nome;
    int codProfissao;

    public Pessoa(String nome, int codProfissao) {
        this.nome = nome;
        this.codProfissao = codProfissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodProfissao() {
        return codProfissao;
    }

    public void setCodProfissao(int codProfissao) {
        this.codProfissao = codProfissao;
    }
}
