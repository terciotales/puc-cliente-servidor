import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GeradorDados gerador = new GeradorDados();
        Scanner entrada = new Scanner(System.in);

        String cabecalho = "LISTA DE PESSOAS E SEUS DEPENDENTES\n" +
                "--------------------------------------------------------------\n" +
                "Ordem     Nome    Data de nascimento     CPF     Profissão\n" +
                "--------------------------------------------------------------\n";


        System.out.println("Informe o valor inicial da sequência: ");
        int valorInicial = entrada.nextInt();
        System.out.println("Informe a quantidade de dígitos da sequência: ");
        int quantidadeDigitos = entrada.nextInt();

        ArrayList<Pessoa> pessoas = getPessoas();
        System.out.println(cabecalho);

        for (int i = 0; i < 30; i++) {
            Pessoa pessoa = pessoas.get(i);

            System.out.println(gerador.formatarSequencia(valorInicial, quantidadeDigitos) + " " + pessoa.getNome() + " " + pessoa.getDataNascimento() + " " + pessoa.getCpf() + " " + pessoa.getProfissao().getNome());
            if (pessoa.getDependentes().size() > 0) {
                System.out.println("Dependentes:");
                for (Dependente dependente : pessoa.getDependentes()) {
                    System.out.println(dependente.getNome() + " " + dependente.getDataNascimento() + " " + dependente.getTipo());
                }
            }
            System.out.println();
            valorInicial++;
        }

    }

    public static ArrayList<Pessoa> getPessoas() {
        GeradorDados gerador = new GeradorDados();

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas = gerador.gerarPessoas(30);
        int i = 0;
        while (i <= 6) {
            pessoas.get(i).addDependente(gerador.gerarDependente());
            i++;
        }

        while (i <= 10) {
            pessoas.get(i).addDependente(gerador.gerarDependente());
            pessoas.get(i).addDependente(gerador.gerarDependente());
            i++;
        }

        while (i <= 17) {
            pessoas.get(i).addDependente(gerador.gerarDependente());
            pessoas.get(i).addDependente(gerador.gerarDependente());
            pessoas.get(i).addDependente(gerador.gerarDependente());
            i++;
        }

        while (i <= 19) {
            pessoas.get(i).addDependente(gerador.gerarDependente());
            pessoas.get(i).addDependente(gerador.gerarDependente());
            pessoas.get(i).addDependente(gerador.gerarDependente());
            pessoas.get(i).addDependente(gerador.gerarDependente());
            i++;
        }

        Collections.shuffle(pessoas);

        return pessoas;
    }
}