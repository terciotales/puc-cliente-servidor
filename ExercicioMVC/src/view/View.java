package view;

import controller.Controller;
import model.Telefone;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class View {

    private final Controller controller = new Controller();

    private boolean running = true;

    public void init() throws Exception {
        System.out.println("Bem-vindo ao sistema de cadastro de clientes");
        mainMenu();
    }

    // Menu principal
    public void mainMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar novo cliente");
            System.out.println("2 - Alterar cliente");
            System.out.println("3 - Excluir cliente");
            System.out.println("4 - Pesquisar cliente");
            System.out.println("5 - Sair");

            String userOption = scanner.nextLine();
            callController(userOption);
        }
    }

    public void callController(String userOption) throws Exception {
        switch (userOption) {
            case "1": {
                System.out.println("Cadastro de cliente");
                controller.cadastrarCliente();
                break;
            }
            case "2": {
                System.out.println("Alteração de cliente");
                controller.alterarCliente();
                break;
            }
            case "3": {
                System.out.println("Exclusão de cliente");
                controller.excluirCliente();
                break;
            }
            case "4": {
                System.out.println("Pesquisa de cliente");
                controller.pesquisarCliente();
                break;
            }
            case "5": {
                exit();
                break;
            }
            default: {
                System.out.println("Opção inválida");
                break;
            }
        }
    }

    public static String getClienteNome() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do cliente:");
        String nome = scanner.nextLine();

        while (Objects.equals(nome, "")) {
            System.out.println("O nome não pode ser vazio!");
            nome = scanner.nextLine();
        }

        return nome;
    }

    public static ArrayList<Telefone> getTelefones() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Telefone> telefones = new ArrayList<>();
        boolean addTelefone = true;

        while (addTelefone) {
            System.out.println("Digite o número do telefone:");
            String telefone = scanner.nextLine();

            if (Objects.equals(telefone, "")) {
                System.out.println("Você precisa digitar um número!");
                continue;
            }

            telefones.add(new Telefone(telefone));

            System.out.println("Deseja adicionar outro telefone? (s/n)");
            String userOption = scanner.nextLine();

            if (userOption.equals("n")) {
                addTelefone = false;
            }
        }

        return telefones;
    }

    public void exit() {
        this.running = false;
    }
}
