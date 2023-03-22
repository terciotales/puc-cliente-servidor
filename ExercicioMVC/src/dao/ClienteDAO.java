package dao;

import model.Cliente;
import model.Conexao;

import java.sql.ResultSet;

public class ClienteDAO {
    public void cadastrar(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "INSERT INTO clientes (nome) VALUES ('" + cliente.getNome() + "')";
        conexao.executar(sql);
        cliente.setId(conexao.getResultId());
    }

    public static void alterar(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "UPDATE clientes SET nome = '" + cliente.getNome() + "' WHERE id = " + cliente.getId();
        conexao.executar(sql);
    }

    public static boolean check(int id) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "Select 1 from clientes where id = " + id;

        ResultSet resultSet = conexao.consultar(sql);

        return resultSet.next();
    }

    public static Cliente getCliente(int id) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "SELECT * FROM clientes WHERE id = " + id;

        ResultSet resultSet = conexao.consultar(sql);

        Cliente cliente = new Cliente();

        if (resultSet.next()) {
            cliente.setId(resultSet.getInt("id"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setTelefones(TelefoneDAO.getTelefones(id));
        }

        return cliente;
    }
}
