package com.corpevents.main.dao;

import com.corpevents.main.connection.DBConnection;
import com.corpevents.main.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDAO extends DBConnection {
    private PreparedStatement preparedStatement = null;

    public boolean insert(Categoria categoria) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO categorias (name) VALUES (?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, categoria.getNome());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Categoria categoria) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "UPDATE categorias SET name = ? WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, categoria.getNome());
            this.preparedStatement.setInt(2, categoria.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Categoria categoria) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM categorias WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, categoria.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Categoria> selectAll() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM categorias";
            this.preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            ArrayList<Categoria> categorias = new ArrayList<Categoria>();
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("name"));
                categorias.add(categoria);
            }

            connection.commit();
            connection.close();

            return categorias;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Categoria selectById(int id) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM categorias WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            Categoria categoria = new Categoria();
            while (resultSet.next()) {
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("name"));
            }

            connection.commit();
            connection.close();

            return categoria;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Categoria selectByName(String name) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM categorias WHERE name = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, name);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            Categoria categoria = new Categoria();
            while (resultSet.next()) {
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("name"));
            }

            connection.commit();
            connection.close();

            return categoria;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int count() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM categorias";
            this.preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }

            connection.commit();
            connection.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
