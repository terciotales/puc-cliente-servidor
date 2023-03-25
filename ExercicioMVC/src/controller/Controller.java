package controller;

import dao.ClienteDAO;
import dao.TelefoneDAO;
import model.Cliente;
import model.Telefone;
import view.View;

import java.util.Objects;

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

    public void adicionarTelefone() throws Exception {
        Cliente cliente = new Cliente();
        Telefone telefone = new Telefone();

        while (cliente.getId() == 0) {
            cliente.setId(View.getClienteId());

            if (ClienteDAO.check(cliente.getId())) {
                System.out.println("Cliente não encontrado.");
                cliente.setId(0);
            }
        }

        telefone.setClienteId(cliente.getId());
        telefone.setTelefone(View.getTelefone());

        while (Objects.equals(telefone.getTelefone(), "")) {
            telefone.setTelefone(View.getTelefone());
        }

        TelefoneDAO.cadastrar(telefone);

    }

    public void alterarCliente() throws Exception {
        Cliente cliente = new Cliente();

        while (cliente.getId() == 0) {
            cliente.setId(View.getClienteId());

            if (ClienteDAO.check(cliente.getId())) {
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

    public void excluirCliente() throws Exception {
        Cliente cliente = new Cliente();

        while (cliente.getId() == 0) {
            cliente.setId(View.getClienteId());

            if (ClienteDAO.check(cliente.getId())) {
                System.out.println("Cliente não encontrado.");
                cliente.setId(0);
            }
        }

        String confirm = View.exclusionConfirmation("cliente");

        if (confirm.equals("s")) {
            ClienteDAO.excluir(cliente.getId());
        } else {
            View.printMessage("Exclusão cancelada.");
        }
    }

    public void pesquisarCliente() {

    }

    public void excluirTelefone() throws Exception {
        Telefone telefone = new Telefone();

        while (telefone.getId() == 0) {
            telefone.setId(View.getTelefoneID());

            if (TelefoneDAO.check(telefone.getId())) {
                View.printMessage("Telefone não encontrado.");
                telefone.setId(0);
            }
        }

        String confirm = View.exclusionConfirmation("telefone");

        if (confirm.equals("s")) {
            TelefoneDAO.excluir(telefone.getId());
        } else {
            View.printMessage("Exclusão cancelada.");
        }
    }
}
