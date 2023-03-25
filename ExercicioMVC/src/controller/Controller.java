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

        String opcao = View.changeClienteMenu();

        switch (opcao) {
            case "1" -> {
                cliente.setNome(View.getClienteNome());
                ClienteDAO.alterar(cliente);
            }
            case "2" -> {
                this.alterarTelefone(cliente);
            }
            default -> View.printMessage("Opção inválida.");
        }
    }

    public void alterarTelefone(Cliente cliente) throws Exception {
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
                View.printMessage("Telefone não encontrado!");
            }
        }

        telefone.setTelefone(View.getTelefone());
        TelefoneDAO.alterar(telefone);
    }

    public void excluirCliente() {

    }

    public void pesquisarCliente() {

    }
}
