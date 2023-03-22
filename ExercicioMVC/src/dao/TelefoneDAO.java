package dao;

import model.Conexao;
import model.Telefone;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TelefoneDAO {
    public void cadastrar(Telefone telefone) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "INSERT INTO telefones (cliente_id, telefone) VALUES ('" + telefone.getClienteId() + "', '" + telefone.getTelefone() + "')";
        conexao.executar(sql);
        telefone.setId(conexao.getResultId());
    }

    public void cadastrarVarios(ArrayList<Telefone> telefones) throws Exception {
        for (Telefone telefone : telefones) {
            cadastrar(telefone);
        }
    }

    public static void alterar(Telefone telefone) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "UPDATE telefones SET telefone = '" + telefone.getTelefone() + "' WHERE id = " + telefone.getId();
        conexao.executar(sql);
    }

    public static ArrayList<Telefone> getTelefones(int clienteId) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "SELECT * FROM telefones WHERE cliente_id = " + clienteId;

        ResultSet resultSet = conexao.consultar(sql);

        ArrayList<Telefone> telefones = new ArrayList<Telefone>();

        while (resultSet.next()) {
            Telefone telefone = new Telefone();
            telefone.setId(resultSet.getInt("id"));
            telefone.setClienteId(resultSet.getInt("cliente_id"));
            telefone.setTelefone(resultSet.getString("telefone"));
            telefones.add(telefone);
        }

        return telefones;
    }
}
