package dao;

import model.Cliente;
import model.Conexao;

public class ClienteDAO {
    public void cadastrar(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "INSERT INTO clientes (nome) VALUES ('" + cliente.getNome() + "')";
        conexao.executar(sql);
        System.out.println("Cliente cadastrado com sucesso!");
    }
}
