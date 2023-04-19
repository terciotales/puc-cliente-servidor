import java.util.Date;

public class Dependente {
    private String nome;
    private Date dataNascimento;
    private int tipo;

    public Dependente(String nome, Date dataNascimento, int tipo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public int getTipo() {
        return tipo;
    }
}
