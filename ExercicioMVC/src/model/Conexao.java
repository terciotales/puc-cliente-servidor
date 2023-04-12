package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private long resultId = 0;

    // Conecta ao banco de dados
    public void conectar() throws Exception {
        try {
            // Carrega o driver MySQL, cada banco de dados tem seu próprio driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Configura a conexão com o banco de dados
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/clientes?" + "user=admin&password=admin");

            // As instruções permitem emitir consultas SQL ao banco de dados
            this.statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fecha a conexão
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executar(String sql) {
        try {
            String[] generatedColumns = {"ID"};

            this.connection.setAutoCommit(false);
            this.preparedStatement = connection.prepareStatement(sql, generatedColumns);
            this.preparedStatement.executeUpdate();
            this.resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                this.resultId = resultSet.getLong(1);
            }

            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public ResultSet consultar(String sql) {
        try {
            this.resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.resultSet;
    }

    public int getResultId() {
        return Integer.parseInt(String.valueOf(this.resultId));
    }
}
