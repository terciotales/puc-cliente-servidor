package sicof.dao;

import sicof.connection.DBConnection;
import sicof.model.Ator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AtorDAO extends DBConnection {
    private Connection connection = null;
    private final Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public boolean insert(Ator ator) {
        try {
            this.connection = this.getConnection();
            this.connection.setAutoCommit(false);

            String sql = "INSERT INTO ator (name) VALUES (?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, ator.getName());
            this.preparedStatement.executeUpdate();

            this.connection.commit();
            this.connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Ator ator) {
        try {
            this.connection = this.getConnection();
            this.connection.setAutoCommit(false);

            String sql = "UPDATE ator SET name = ? WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, ator.getName());
            this.preparedStatement.setInt(2, ator.getId());
            this.preparedStatement.executeUpdate();

            this.connection.commit();
            this.connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Ator ator) {
        try {
            this.connection = this.getConnection();
            this.connection.setAutoCommit(false);

            String sql = "DELETE FROM ator WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, ator.getId());
            this.preparedStatement.executeUpdate();

            this.connection.commit();
            this.connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Ator get(int id) {
        Ator ator = null;

        try {
            this.connection = this.getConnection();
            this.connection.setAutoCommit(false);

            String sql = "SELECT * FROM ator WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            if (resultSet.next()) {
                ator = new Ator(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }

            this.connection.commit();
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ator;
    }

    public Ator get(String name) {
        Ator ator = null;

        try {
            this.connection = this.getConnection();
            this.connection.setAutoCommit(false);

            String sql = "SELECT * FROM ator WHERE name = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, name);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            if (resultSet.next()) {
                ator = new Ator(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }

            this.connection.commit();
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ator;
    }

    public ArrayList<Ator> search(String name) {
        ArrayList<Ator> atores = new ArrayList<>();

        try {
            this.connection = this.getConnection();
            this.connection.setAutoCommit(false);

            String sql = "SELECT * FROM ator WHERE name LIKE ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()) {
                atores.add(new Ator(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }

            this.connection.commit();
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return atores;
    }
}
