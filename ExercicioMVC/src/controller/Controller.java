package controller;

import dao.ClienteDAO;
import dao.TelefoneDAO;
import model.Cliente;
import model.Telefone;
import view.View;

import java.util.Objects;
import java.util.Scanner;

public class Controller {
    public void cadastrarCliente() throws Exception {
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        TelefoneDAO telefoneDAO = new TelefoneDAO();

        while (Objects.equals(cliente.getNome(), "")) {
            cliente.setNome(View.getClienteNome());
        }

        while (cliente.getTelefones().isEmpty()) {
            cliente.setTelefones(View.getTelefones());
        }

        clienteDAO.cadastrar(cliente);
        telefoneDAO.cadastrarVarios(cliente.getTelefones());
    }

    public void alterarCliente() throws Exception {
        Cliente cliente = new Cliente();
        Scanner scanner = new Scanner(System.in);

        while (cliente.getId() == 0) {
            cliente.setId(View.getClienteId());

            if (!ClienteDAO.check(cliente.getId())) {
                System.out.println("Cliente não encontrado.");
                cliente.setId(0);
            }
        }

        cliente = ClienteDAO.getCliente(cliente.getId());

        System.out.println(cliente);
        System.out.println("Qual campo deseja alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Telefone");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                cliente.setNome(View.getClienteNome());
                ClienteDAO.alterar(cliente);
            }
            case 2 -> {
                System.out.println("Digite o ID do telefone que deseja alterar:");
                int idTelefone = scanner.nextInt();
                for (Telefone telefone : cliente.getTelefones()) {
                    if (telefone.getId() == idTelefone) {
                        telefone.setTelefone(View.getTelefone());
                        TelefoneDAO.alterar(telefone);
                    }
                }
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    public void excluirCliente() {

    }

    public void pesquisarCliente() {

    }
}
