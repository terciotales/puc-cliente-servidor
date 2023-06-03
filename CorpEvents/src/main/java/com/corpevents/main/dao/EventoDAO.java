package com.corpevents.main.dao;

import com.corpevents.main.connection.DBConnection;
import com.corpevents.main.model.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EventoDAO extends DBConnection {
    private PreparedStatement preparedStatement = null;

    public boolean insert(Evento evento) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO eventos (title, description, date, author, category, local) VALUES (?, ?, ?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, evento.getTitle());
            this.preparedStatement.setString(2, evento.getDescription());
            this.preparedStatement.setString(3, evento.getDate());
            this.preparedStatement.setString(4, evento.getAuthor());
            this.preparedStatement.setString(5, evento.getCategory());
            this.preparedStatement.setString(6, evento.getLocal());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Evento evento) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "UPDATE events SET title = ?, description = ?, date = ?, author = ?, category = ?, local = ? WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, evento.getTitle());
            this.preparedStatement.setString(2, evento.getDescription());
            this.preparedStatement.setString(3, evento.getDate());
            this.preparedStatement.setString(4, evento.getAuthor());
            this.preparedStatement.setString(5, evento.getCategory());
            this.preparedStatement.setString(6, evento.getLocal());
            this.preparedStatement.setInt(7, evento.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Evento evento) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM events WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, evento.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Evento> selectAll() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos";
            this.preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            ArrayList<Evento> eventos = new ArrayList<Evento>();
            while (resultSet.next()) {
                Evento evento = new Evento();
                evento.setId(resultSet.getInt("id"));
                evento.setTitle(resultSet.getString("title"));
                evento.setDescription(resultSet.getString("description"));
                evento.setDate(resultSet.getString("date"));
                evento.setAuthor(resultSet.getString("author"));
                evento.setCategory(resultSet.getString("category"));
                evento.setLocal(resultSet.getString("local"));
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
}