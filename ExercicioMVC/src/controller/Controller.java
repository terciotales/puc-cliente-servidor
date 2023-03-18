package controller;

import model.Cliente;
import model.Telefone;

import java.util.Scanner;

public class Controller {
    public void cadastrarCliente() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();
        boolean addTelefone = true;

        System.out.println("Cadastro de cliente");

        System.out.println("Digite o nome do cliente:");
        cliente.setNome(scanner.nextLine());

        while (addTelefone) {
            System.out.println("Digite o número do telefone:");
            String telefoneStr = scanner.nextLine();
            cliente.addTelefone(telefoneStr);

            System.out.println("Deseja adicionar outro telefone? (s/n)");
            String userOption = scanner.nextLine();

            if (userOption.equals("n")) {
                addTelefone = false;
            }
        }

        cliente.cadastrar();
    }

    public void alterarCliente() {

    }

    public void excluirCliente() {

    }

    public void pesquisarCliente() {

    }
}
