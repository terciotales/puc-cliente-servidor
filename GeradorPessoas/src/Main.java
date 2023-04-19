import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/**
 * a)Gere informações de diversas pessoas: nome, data de nascimento, CPF, código da profissão
 * (01 a 08) e dados de seus dependentes;
 * b)Gere randomicamente as informações de cada dependente: nome, data de nascimento e
 * tipo de dependente (01 a 04);
 * c)Os nomes (nome + dois sobrenomes) de cada pessoa (e seus respectivos dependentes)
 * devem ser geradoa com letras em caixa alta (maiúsculas), sendo que cada nome deve possuir
 * 30 caracteres (20 para cada dependente). Cada caractere de um nome deve ser gerado
 * baseando-se nos respectivos códigos da tabela ASCII;
 * d)As datas de nascimento devem ser geradas de tal forma que sejam válidas, visto que uma
 * data gerada como, por exemplo, 30/02/2020 não é válida, pois não existe o dia 30 no mês de
 * fevereiro. As datas geradas devem ser entre 01/01/1980 a 31/12/2022, sendo que as datas de
 * nascimento dos dependentes devem apresentar idade menor que 15 anos e as datas de
 * nascimento das pessoas que possuem dependentes devem estabelecer idade acima de 20
 * anos;
 * e)Os primeiros nove dígitos do CPF devem ser também ser gerados e os dois últimos dígitos,
 * denominados de dígitos de controle do CPF, devem ser calculados pelo programa conforme
 * regra que deve ser pesquisada pelo aluno;
 * f)Para gerar os códigos das profissões, o programa deverá ter inicialmente uma lista de nomes
 * de 20 profissões. Os nomes de cada profissão não devem exceder 20 caracteres. Esses nomes
 * devem ser armazenados em um vetor de String. Em seguida, o programa deverá gerar os
 * códigos (01 a 08) de cada profissão e relacionar cada código gerado com um nome na lista de
 * profissão, randomizando o nome desejado para cada um dos 8 códigos gerados, sem repetir a
 * descrição da profissão. Cada código de profissão com sua respectiva profissão deverá ser os
 * atributos de oito objetos da classe Profissao. Assim, esses oito objetos devem ser
 * armazenados em uma coleção ArrayList;
 * g)Obter, por randomização, o código da profissão de uma pessoa de acordo com a coleção de
 * profissões do item f;
 * h)Após a geração dos dados de uma pessoa, armazenar esses dados como atributos de um
 * objeto da classe Pessoa, sendo que uma pessoa poderá ter nenhum, um ou até quatro
 * dependentes. Os dados de cada dependente gerado devem ser armazenados em um objeto da
 * classe Dependente. Além dos atributos dos dados da pessoa, a classe Pessoa possui o atributo
 * dependentes que é uma coleção do tipo ArrayList para armazenar cada um dos seus
 * dependentes. Assim, cada objeto da classe Dependente deve ficar armazenado no atributo
 * dependentes da classe Pessoa;
 * ESCOLA POLITÉCNICA E DE ARTES
 * CURSO DE TECNOLOGIA EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * DISCIPLINA DESENVOLVIMENTO DE APLICAÇÕES CLIENTE-SERVIDOR
 * i)Gerar, no mínimo, 30 pessoas, sendo que, obrigatoriamente, 5 não devem possuir
 * dependentes; 6 devem possuir um dependente; 4 devem possuir dois dependentes; 7 devem
 * possuir três dependentes; 2 devem possuir quatro dependentes;
 * j)O programa deve possui um código para geração de dependentes de tal forma que essa
 * geração seja flexível a mudanças, facilitando sua manutenção;
 * k)Listar os dados de cada pessoa e de seus respectivos dependentes no seguinte formato:
 * LISTA DE PESSOAS E SEUS DEPENDENTES
 * ------------------------------------------------------------------------------------------------------------------
 * Ordem nome dt.Nasc. cpf profissão
 * ------------------------------------------------------------------------------------------------------------------
 * 00001 Antonio Alves Pereira xxxxxxxx 01/10/2000 123456789-00 Eng.Civil xxxxxxxxxxx
 * Dependentes:
 * Maria Carla Pereiraa 05/08/2001 Conjuge
 * 00002 Carlos Antônio Moreira Silvaist 30/01/1998 456789001-35 Endocrinologista xxx
 * Dependentes:
 * <Sem dependentes>
 * 00002 Abcdef Ghijklmopq Rstuvyxwz 01/10/1998 123456789-00 Abcdef Ghijklmnopqr
 * Dependentes:
 * Abcd Efghijklm NopqrstUvxzw 15/09/2000 Conjuge
 * Efghijklm NopqrstUvxzw Abcd 18/07/2010 Filho
 * NopqrstUvxzw Abcdefghijklm 20/11/2012 Filho
 * .......
 * Observações:
 * I)A listagem deve possuir 20 linhas por página, incluindo o cabeçalho. Assim, a cada vinte
 * linhas, um novo cabeçalho deve ser apresentado e continuar a escrever os dados da listagem,
 * considerando sempre a sequência das informações, conforme o padrão apresentado
 * anteriormente.
 * II)O número da ordem deve ser seqüencial. O valor inicial da sequência dessa ordem deve ser
 * informada pelo usuário no inicio do programa, não podendo ser inferior a 1 ou superior a 300.
 * Além disso, o usuário deve informar também a quantidade de dígitos da sequência. Com esses
 * dados, a sequência deve ser mostrada com complementos de zeros à esquerda de acordo com
 * a quantidade de dígitos informada. Fazer um método para processar essa estrutura
 */

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
        System.out.println(cabecalho);
        
        for (i = 0; i < 30; i++) {
            Pessoa pessoa = pessoas.get(i);

            System.out.println(gerador.formatarSequencia(valorInicial, quantidadeDigitos) + " " + pessoa.getNome() + " " + pessoa.getDataNascimento() + " " + pessoa.getCpf() + " " + pessoa.getProfissao().getNome());
            if (pessoa.getDependentes().size() > 0) {
                System.out.println("Dependentes:");
                for (Dependente dependente : pessoa.getDependentes()) {
                    System.out.println(dependente.getNome() + " " + dependente.getDataNascimento() + " " + dependente.getTipo());
                }
            }
            System.out.println("");
            valorInicial++;
        }

    }

    private Pessoa adicionarDependentes(Pessoa pessoa, int quantidadeDependentes) {
        GeradorDados gerador = new GeradorDados();
        for (int i = 0; i < quantidadeDependentes; i++) {
            pessoa.getDependentes().add(gerador.gerarDependente());
        }
        return pessoa;
    }
}