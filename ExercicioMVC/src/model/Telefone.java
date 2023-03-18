package model;

public class Telefone {

    private int clienteId;
    private String telefone;

    public Telefone() {
        // super();
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

    @Override
    public String toString() {
        return "Telefone [clienteId=" + clienteId + ", telefone=" + telefone + "]";
    }
}
