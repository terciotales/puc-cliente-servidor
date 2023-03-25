package controller;

import dao.ClienteDAO;
import dao.TelefoneDAO;
import model.Cliente;
import model.Telefone;

public class Controller {
    public Cliente getCliente(int id) throws Exception {
        return ClienteDAO.getCliente(id);
    }

    public void cadastrarCliente(Cliente cliente) throws Exception {
        ClienteDAO clienteDAO = new ClienteDAO();
        TelefoneDAO telefoneDAO = new TelefoneDAO();

        clienteDAO.cadastrar(cliente);
        telefoneDAO.cadastrarVarios(cliente.getTelefones());
    }

    public void adicionarTelefone(Telefone telefone) throws Exception {
        TelefoneDAO.cadastrar(telefone);
    }

    public void alterarTelefone(Telefone telefone) throws Exception {
        TelefoneDAO.alterar(telefone);
    }

    public void excluirCliente(Cliente cliente) throws Exception {
        ClienteDAO.excluir(cliente.getId());
    }

    public void excluirTelefone(Telefone telefone) throws Exception {
        TelefoneDAO.excluir(telefone.getId());
    }

    public boolean checkCliente(int id) throws Exception {
        return ClienteDAO.check(id);
    }

    public void alterarCliente(Cliente cliente) throws Exception {
        ClienteDAO.alterar(cliente);
    }

    public boolean checkTelefone(int id) throws Exception {
        return TelefoneDAO.check(id);
    }
}
