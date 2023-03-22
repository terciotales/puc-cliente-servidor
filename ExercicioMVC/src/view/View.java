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

    public void callController(String userOption) throws Exception {
        switch (userOption) {
            case "1": {
                System.out.println("Cadastro de cliente");
                controller.cadastrarCliente();
                System.out.println("Cliente cadastrado com sucesso!");
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

    public static void changeClienteMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual campo deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Telefone");

        String userOption = scanner.nextLine();

        switch (userOption) {
            case "1": {
                controller.alterarClienteNome();
                break;
            }
            case "2": {
                controller.alterarClienteTelefone();
                break;
            }
            default: {
                System.out.println("Opção inválida");
                break;
            }
        }
    }

    public static int getClienteId() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do cliente:");
        int id = scanner.nextInt();

        while (id <= 0) {
            System.out.println("O ID não pode ser menor ou igual a zero!");
            id = scanner.nextInt();
        }

        return id;
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

    public static String getTelefone() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o número do telefone:");
        String telefone = scanner.nextLine();

        while (Objects.equals(telefone, "")) {
            System.out.println("O número não pode ser vazio!");
            telefone = scanner.nextLine();
        }

        return telefone;
    }

    public static ArrayList<Telefone> getTelefones() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Telefone> telefones = new ArrayList<>();
        boolean addTelefone = true;

        while (addTelefone) {
            String telefone = getTelefone();
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
