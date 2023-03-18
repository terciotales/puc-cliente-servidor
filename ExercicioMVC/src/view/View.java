package view;

import controller.Controller;

import java.util.Scanner;

public class View {

    private Controller controller = new Controller();

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
            case "1" -> controller.cadastrarCliente();
            case "2" -> controller.alterarCliente();
            case "3" -> controller.excluirCliente();
            case "4" -> controller.pesquisarCliente();
            case "5" -> exit();
            default -> System.out.println("Opção inválida");
        }
    }

    public void exit() {
        this.running = false;
    }
}
