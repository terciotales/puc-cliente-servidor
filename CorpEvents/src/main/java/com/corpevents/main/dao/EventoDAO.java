package com.corpevents.main.dao;

import com.corpevents.main.connection.DBConnection;
import com.corpevents.main.model.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe DAO da tabela eventos
 */
public class EventoDAO extends DBConnection {
    private PreparedStatement preparedStatement = null;

    public boolean insert(Evento evento) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO eventos (title, description, date, author, category, local) VALUES (?, ?, ?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, evento.getTitle());
            this.preparedStatement.setString(2, evento.getDescription());
            this.preparedStatement.setString(3, evento.getDate());
            this.preparedStatement.setInt(4, evento.getAuthor());
            this.preparedStatement.setInt(5, evento.getCategory());
            this.preparedStatement.setString(6, evento.getLocal());
            int affectedRows = this.preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Erro ao inserir evento.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    evento.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Erro ao inserir evento.");
                }
            }

            connection.commit();
            connection.close();

            if (!evento.getPessoas().isEmpty()) {
                EventoPessoaDAO eventoPessoaDAO = new EventoPessoaDAO();

                evento.getPessoas().forEach(pessoa -> {
                    try {
                        eventoPessoaDAO.insert(evento.getId(), pessoa.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }


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

            String sql = "UPDATE eventos SET title = ?, description = ?, date = ?, author = ?, category = ?, local = ? WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, evento.getTitle());
            this.preparedStatement.setString(2, evento.getDescription());
            this.preparedStatement.setString(3, evento.getDate());
            this.preparedStatement.setInt(4, evento.getAuthor());
            this.preparedStatement.setInt(5, evento.getCategory());
            this.preparedStatement.setString(6, evento.getLocal());
            this.preparedStatement.setInt(7, evento.getId());
            this.preparedStatement.executeUpdate();

            connection.commit();
            connection.close();

            EventoPessoaDAO eventoPessoaDAO = new EventoPessoaDAO();
            PessoaDAO pessoaDAO = new PessoaDAO();

            eventoPessoaDAO.selectByEvento(evento.getId()).forEach(eventoPessoa -> {
                if (!evento.getPessoas().contains(pessoaDAO.selectById(eventoPessoa.getId()))) {
                    try {
                        eventoPessoaDAO.delete(evento.getId(), eventoPessoa.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            if (!evento.getPessoas().isEmpty()) {
                evento.getPessoas().forEach(pessoa -> {
                    if (!eventoPessoaDAO.exists(evento.getId(), pessoa.getId())) {
                        try {
                            eventoPessoaDAO.insert(evento.getId(), pessoa.getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Evento evento) {
        try {
            EventoPessoaDAO eventoPessoaDAO = new EventoPessoaDAO();
            eventoPessoaDAO.deleteByEvento(evento.getId());

            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM eventos WHERE id = ?";
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
                evento.setAuthor(resultSet.getInt("author"));
                evento.setCategory(resultSet.getInt("category"));
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

    public ArrayList<Evento> selectNext() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos WHERE date >= NOW() ORDER BY date ASC LIMIT 8";
            this.preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            ArrayList<Evento> eventos = new ArrayList<Evento>();
            while (resultSet.next()) {
                Evento evento = new Evento();
                evento.setId(resultSet.getInt("id"));
                evento.setTitle(resultSet.getString("title"));
                evento.setDescription(resultSet.getString("description"));
                evento.setDate(resultSet.getString("date"));
                evento.setAuthor(resultSet.getInt("author"));
                evento.setCategory(resultSet.getInt("category"));
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

    public ArrayList<Evento> selectLast() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos WHERE date < NOW() ORDER BY date DESC LIMIT 8";
            this.preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            ArrayList<Evento> eventos = new ArrayList<Evento>();
            while (resultSet.next()) {
                Evento evento = new Evento();
                evento.setId(resultSet.getInt("id"));
                evento.setTitle(resultSet.getString("title"));
                evento.setDescription(resultSet.getString("description"));
                evento.setDate(resultSet.getString("date"));
                evento.setAuthor(resultSet.getInt("author"));
                evento.setCategory(resultSet.getInt("category"));
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

    public int count() {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM eventos";
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

    public Evento selectById(int id) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            Evento evento = new Evento();
            while (resultSet.next()) {
                evento.setId(resultSet.getInt("id"));
                evento.setTitle(resultSet.getString("title"));
                evento.setDescription(resultSet.getString("description"));
                evento.setDate(resultSet.getString("date"));
                evento.setAuthor(resultSet.getInt("author"));
                evento.setCategory(resultSet.getInt("category"));
                evento.setLocal(resultSet.getString("local"));
            }

            connection.commit();
            connection.close();

            return evento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Evento> search(String search, String extraCondition) {
        try {
            Connection connection = this.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM eventos WHERE (title LIKE ? OR description LIKE ?)";

            if (Objects.equals(search, "")) {
                sql = "SELECT * FROM eventos WHERE (title IS NOT NULL)";
            }

            if (extraCondition != null) {
                sql += " " + extraCondition;
            }

            this.preparedStatement = connection.prepareStatement(sql);

            if (!Objects.equals(search, "")) {
                this.preparedStatement.setString(1, "%" + search + "%");
                this.preparedStatement.setString(2, "%" + search + "%");
            }
            ResultSet resultSet = this.preparedStatement.executeQuery();

            ArrayList<Evento> eventos = new ArrayList<Evento>();
            while (resultSet.next()) {
                Evento evento = new Evento();
                evento.setId(resultSet.getInt("id"));
                evento.setTitle(resultSet.getString("title"));
                evento.setDescription(resultSet.getString("description"));
                evento.setDate(resultSet.getString("date"));
                evento.setAuthor(resultSet.getInt("author"));
                evento.setCategory(resultSet.getInt("category"));
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
