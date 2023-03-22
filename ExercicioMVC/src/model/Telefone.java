package model;

import dao.TelefoneDAO;

public class Telefone {

    private int clienteId;
    private String telefone;

    public Telefone() {
        // super();
    }

    public Telefone (String telefone) {
        this.telefone = telefone;
    }

    public Telefone(int clienteId, String telefone) {
        this.clienteId = clienteId;
        this.telefone = telefone;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void cadastrar() throws Exception {
        TelefoneDAO clienteDAO = new TelefoneDAO();
        clienteDAO.cadastrar(this);
    }

    @Override
    public String toString() {
        return "Telefone [clienteId=" + clienteId + ", telefone=" + telefone + "]";
    }
}
