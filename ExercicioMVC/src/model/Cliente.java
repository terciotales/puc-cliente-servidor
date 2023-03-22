package model;

import dao.ClienteDAO;

import java.util.ArrayList;

public class Cliente {

    private int id = 0;
    private String nome = "";
    private ArrayList<Telefone> telefones = new ArrayList<>();

    public Cliente() {
        // super();
    }

    public Cliente(int id, String nome, ArrayList<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.telefones = telefones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone(String telefoneStr) {
        if (this.telefones == null) {
            this.telefones = new ArrayList<Telefone>();
        }

        Telefone telefone = new Telefone();
        telefone.setTelefone(telefoneStr);
        this.telefones.add(telefone);
    }

    public void cadastrar() throws Exception {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.cadastrar(this);

        if (this.id != 0) {
            telefones.forEach((telefone) -> {
                try {
                    telefone.setClienteId(this.id);
                    telefone.cadastrar();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println("Cliente cadastrado com sucesso!");
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", telefones=" + telefones + "]";
    }
}
