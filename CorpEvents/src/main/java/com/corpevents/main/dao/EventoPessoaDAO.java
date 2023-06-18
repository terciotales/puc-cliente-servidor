package com.corpevents.main.dao;

import com.corpevents.main.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EventoPessoaDAO extends DBConnection {
    private PreparedStatement preparedStatement = null;

    public boolean insert(int eventoId, int pessoaId) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO eventos_pessoas (evento_id, usuario_id) VALUES (?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, eventoId);
            this.preparedStatement.setInt(2, pessoaId);
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
