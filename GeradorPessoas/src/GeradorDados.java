import java.util.Random;

public class GeradorDados {

    public static void main(String[] args) {

        String[] nomes = {"João", "Maria", "José", "Ana", "Pedro", "Mariana"};
        String[] sobrenomes = {"Silva", "Santos", "Souza", "Oliveira", "Pereira", "Costa"};
        String[] profissoes = {"Engenheiro", "Médico", "Advogado", "Professor", "Programador", "Jornalista"};

        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            int indiceNome = random.nextInt(nomes.length);
            int indiceSobrenome = random.nextInt(sobrenomes.length);
            int indiceProfissao = random.nextInt(profissoes.length);

            String nomeCompleto = nomes[indiceNome] + " " + sobrenomes[indiceSobrenome];
            String profissao = profissoes[indiceProfissao];
            int idade = random.nextInt(61) + 20;
            double salario = random.nextDouble() * 10000 + 1000;

            System.out.println("Nome: " + nomeCompleto + ", Idade: " + idade + ", Profissão: " + profissao + ", Salário: " + salario);
        }
    }
}
