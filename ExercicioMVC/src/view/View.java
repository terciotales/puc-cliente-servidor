package view;

import controller.Controller;
import model.Cliente;
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
            case "1" -> {
                System.out.println("Cadastro de cliente");

                Cliente cliente = new Cliente();

                while (Objects.equals(cliente.getNome(), "")) {
                    cliente.setNome(View.getClienteNome());
                }

                while (cliente.getTelefones().isEmpty()) {
                    cliente.setTelefones(View.getTelefones());
                }

                controller.cadastrarCliente(cliente);
                System.out.println("Cliente cadastrado com sucesso!");
            }
            case "2" -> {
                System.out.println("Adicionar telefone a um cliente");

                Cliente cliente = new Cliente();
                Telefone telefone = new Telefone();

                while (cliente.getId() == 0) {
                    cliente.setId(View.getClienteId());

                    if (controller.checkCliente(cliente.getId())) {
                        System.out.println("Cliente não encontrado.");
                        cliente.setId(0);
                    }
                }

                telefone.setClienteId(cliente.getId());
                telefone.setTelefone(View.getTelefone());

                while (Objects.equals(telefone.getTelefone(), "")) {
                    telefone.setTelefone(View.getTelefone());
                }

                controller.adicionarTelefone(telefone);

                System.out.println("Telefone adicionado com sucesso!");
            }
            case "3" -> {
                System.out.println("Alteração de cliente");

                Cliente cliente = new Cliente();

                while (cliente.getId() == 0) {
                    cliente.setId(View.getClienteId());

                    if (controller.checkCliente(cliente.getId())) {
                        System.out.println("Cliente não encontrado.");
                        cliente.setId(0);
                    }
                }

                cliente = controller.getCliente(cliente.getId());

                System.out.println(cliente);

                String opcao = View.changeClienteMenu();

                switch (opcao) {
                    case "1" -> {
                        cliente.setNome(View.getClienteNome());
                        controller.alterarCliente(cliente);
                    }
                    case "2" -> {
                        Telefone telefone = new Telefone();
                        boolean telefoneEncontrado = false;

                        while (!telefoneEncontrado) {
                            int telefoneId = View.getTelefoneID();
                            for (Telefone t : cliente.getTelefones()) {
                                if (Objects.equals(t.getId(), telefoneId)) {
                                    telefoneEncontrado = true;
                                    telefone = t;
                                    break;
                                }
                            }
                            if (!telefoneEncontrado) {
                                System.out.println("Telefone não encontrado!");
                            }
                        }

                        telefone.setTelefone(View.getTelefone());

                        controller.alterarTelefone(telefone);
                    }
                    default -> System.out.println("Opção inválida.");
                }

                controller.alterarCliente(cliente);
                System.out.println("Alteração realizada com sucesso!");
            }
            case "4" -> {
                System.out.println("Exclusão de cliente");
                Cliente cliente = new Cliente();

                while (cliente.getId() == 0) {
                    cliente.setId(View.getClienteId());

                    if (controller.checkCliente(cliente.getId())) {
                        System.out.println("Cliente não encontrado.");
                        cliente.setId(0);
                    }
                }

                String confirm = View.exclusionConfirmation("cliente");

                if (confirm.equals("s")) {
                    System.out.println("Cliente excluído com sucesso!");
                    controller.excluirCliente(cliente);
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            }
            case "5" -> {
                System.out.println("Exclusão de telefone");
                Telefone telefone = new Telefone();

                while (telefone.getId() == 0) {
                    telefone.setId(View.getTelefoneID());

                    if (controller.checkTelefone(telefone.getId())) {
                        System.out.println("Telefone não encontrado.");
                        telefone.setId(0);
                    }
                }

                String confirm = View.exclusionConfirmation("telefone");

                if (confirm.equals("s")) {
                    controller.excluirTelefone(telefone);
                    System.out.println("Telefone excluído com sucesso!");
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            }
            case "6" -> {
                System.out.println("Pesquisa de cliente");

                Cliente cliente = new Cliente();

                while (cliente.getId() == 0) {
                    cliente.setId(View.getClienteId());

                    if (controller.checkCliente(cliente.getId())) {
                        System.out.println("Cliente não encontrado.");
                        cliente.setId(0);
                    }
                }

                cliente = controller.getCliente(cliente.getId());

                View.printCliente(cliente.getNome(), cliente.getTelefones());
            }
            case "7" -> {
                exit();
            }
            default -> {
                System.out.println("Opção inválida");
            }
        }
    }

    // Menu principal
    public void mainMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        while (running) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar novo cliente");
            System.out.println("2 - Adicionar telefone a um cliente");
            System.out.println("3 - Alterar cliente ou telefone");
            System.out.println("4 - Excluir cliente");
            System.out.println("5 - Excluir telefone");
            System.out.println("6 - Pesquisar cliente");
            System.out.println("7 - Sair");

            String userOption = scanner.nextLine();

            System.out.println();

            callController(userOption);
        }
    }

    public static String changeClienteMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual campo deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Telefone");

        String userOption = scanner.nextLine();

        while (!userOption.equals("1") && !userOption.equals("2")) {
            System.out.println("Opção inválida");
            userOption = scanner.nextLine();
        }

        return userOption;
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

    public static int getTelefoneID() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do telefone:");
        String userAnswer = scanner.nextLine();
        int id = Integer.parseInt(userAnswer);

        while (id <= 0 || userAnswer.equals("")) {
            System.out.println("O ID não pode ser menor ou igual a zero!");
            id = scanner.nextInt();
        }

        return id;
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

    public static void printCliente(String nome, ArrayList<Telefone> telefones) {
        System.out.println("Nome: " + nome);
        System.out.println("Telefones: ");
        int i = 1;
        for (Telefone telefone : telefones) {
            System.out.println(i + " - " + telefone.getTelefone());
            i++;
        }
    }

    public static String exclusionConfirmation(String toBeDeleted) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tem certeza que deseja excluir o " + toBeDeleted + "? (s/n)");


        String userOption = scanner.nextLine();

        while (!userOption.equals("s") && !userOption.equals("n")) {
            System.out.println("Opção inválida");
            userOption = scanner.nextLine();
        }

        return userOption;
    }

    public void exit() {
        this.running = false;
    }
}
