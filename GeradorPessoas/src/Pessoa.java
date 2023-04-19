import java.util.ArrayList;
import java.util.Date;

public class Pessoa {
    private String nome;
    private String dataNascimento;
    private String cpf;
    private Profissao profissao;
    private ArrayList<Dependente> dependentes;

    public Pessoa() {

    }

    public Pessoa(String nome, String dataNascimento, String cpf, Profissao profissao) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.profissao = profissao;
        this.dependentes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public ArrayList<Dependente> getDependentes() {
        return dependentes;
    }

    public void addDependente(Dependente dependente) {
        this.dependentes.add(dependente);
    }

    @Override
    public String toString() {
        return getNome() + " " + getDataNascimento() + " " + getCpf() + " " + getProfissao().getNome();
    }
}