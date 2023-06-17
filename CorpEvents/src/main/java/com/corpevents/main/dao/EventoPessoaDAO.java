package com.corpevents.main.dao;

import com.corpevents.main.connection.DBConnection;
import com.corpevents.main.model.Evento;
import com.corpevents.main.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public boolean delete(int eventoId, int pessoaId) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM eventos_pessoas WHERE evento_id = ? AND usuario_id = ?";
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

    public boolean deleteByEvento(int eventoId) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM eventos_pessoas WHERE evento_id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, eventoId);
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByPessoa(int pessoaId) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM eventos_pessoas WHERE usuario_id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, pessoaId);
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Pessoa> selectByEvento(int eventoId) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos_pessoas WHERE evento_id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, eventoId);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            ArrayList<Pessoa> pessoas = new ArrayList<>();
            PessoaDAO pessoaDAO = new PessoaDAO();
            while (resultSet.next()) {
                Pessoa pessoa = pessoaDAO.selectById(resultSet.getInt("usuario_id"));
                pessoas.add(pessoa);
            }

            connection.commit();
            connection.close();

            return pessoas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Evento> selectByPessoa(int pessoaId) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos_pessoas WHERE usuario_id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, pessoaId);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            ArrayList<Evento> eventos = new ArrayList<>();
            EventoDAO eventoDAO = new EventoDAO();
            while (resultSet.next()) {
                Evento evento = eventoDAO.selectById(resultSet.getInt("evento_id"));
                eventos.add(evento);
            }

            connection.commit();
            connection.close();

            return eventos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean exists(int eventoId, int pessoaId) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos_pessoas WHERE evento_id = ? AND usuario_id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, eventoId);
            this.preparedStatement.setInt(2, pessoaId);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            boolean exists = resultSet.next();

            connection.commit();
            connection.close();

            return exists;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
