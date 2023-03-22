package dao;

import model.Conexao;
import model.Telefone;

public class TelefoneDAO {
    public void cadastrar(Telefone telefone) throws Exception {
        Conexao conexao = new Conexao();
        conexao.conectar();

        String sql = "INSERT INTO telefones (cliente_id, telefone) VALUES ('" + telefone.getClienteId() + "', '" + telefone.getTelefone() + "')";
        conexao.executar(sql);
    }
}
