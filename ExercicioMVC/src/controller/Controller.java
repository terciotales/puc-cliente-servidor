package controller;

import model.Cliente;
import model.Telefone;
import view.View;

import java.util.Objects;

public class Controller {
    public void cadastrarCliente() throws Exception {
        Cliente cliente = new Cliente();

        while (Objects.equals(cliente.getNome(), "")) {
            cliente.setNome(View.getClienteNome());
        }

        while (cliente.getTelefones().isEmpty()) {
            cliente.setTelefones(View.getTelefones());
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
