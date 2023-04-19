import java.util.Date;

public class Dependente {
    private String nome;
    private String dataNascimento;
    private String tipo;

    public Dependente(String nome, String dataNascimento, String tipo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTipo() {
        return tipo;
    }
}
