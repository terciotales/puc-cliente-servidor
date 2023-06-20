package com.corpevents.main.dao;

import com.corpevents.main.connection.DBConnection;
import com.corpevents.main.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe DAO da tabela usuarios
 */
public class PessoaDAO extends DBConnection {
    private PreparedStatement preparedStatement = null;

    public boolean insert(Pessoa pessoa) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO usuarios (name, username, password, role) VALUES (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, pessoa.getNome());
            this.preparedStatement.setString(2, pessoa.getUsername());
            this.preparedStatement.setString(3, pessoa.getPassword());
            this.preparedStatement.setInt(4, pessoa.getRole());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Pessoa pessoa) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "UPDATE usuarios SET name = ?, username = ?, password = ?, role = ? WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, pessoa.getNome());
            this.preparedStatement.setString(2, pessoa.getUsername());
            this.preparedStatement.setString(3, pessoa.getPassword());
            this.preparedStatement.setInt(4, pessoa.getRole());
            this.preparedStatement.setInt(5, pessoa.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Pessoa pessoa) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM usuarios WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, pessoa.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Pessoa> selectAll() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM usuarios";
            this.preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = this.preparedStatement.executeQuery();

            ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("name"));
                pessoa.setUsername(rs.getString("username"));
                pessoa.setPassword(rs.getString("password"));
                pessoa.setRole(rs.getInt("role"));
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

    public Pessoa selectById(int id) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM usuarios WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            ResultSet rs = this.preparedStatement.executeQuery();

            Pessoa pessoa = new Pessoa();

            while (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("name"));
                pessoa.setUsername(rs.getString("username"));
                pessoa.setPassword(rs.getString("password"));
                pessoa.setRole(rs.getInt("role"));
            }

            connection.commit();
            connection.close();

            return pessoa;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int count() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM usuarios";
            this.preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = this.preparedStatement.executeQuery();

            int count = 0;

            while (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }

            connection.commit();
            connection.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean checkUsername(String username) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM usuarios WHERE username = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, username);
            ResultSet rs = this.preparedStatement.executeQuery();

            boolean exists = false;

            while (rs.next()) {
                exists = true;
            }

            connection.commit();
            connection.close();

            return exists;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkPassword(String username, String password) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, username);
            this.preparedStatement.setString(2, password);
            ResultSet rs = this.preparedStatement.executeQuery();

            boolean exists = false;

            while (rs.next()) {
                exists = true;
            }

            connection.commit();
            connection.close();

            return exists;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Pessoa> search(String search) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM usuarios WHERE name LIKE ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, "%" + search + "%");
            ResultSet rs = this.preparedStatement.executeQuery();

            ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("name"));
                pessoa.setUsername(rs.getString("username"));
                pessoa.setPassword(rs.getString("password"));
                pessoa.setRole(rs.getInt("role"));
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
}
