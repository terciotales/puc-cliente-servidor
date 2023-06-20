package com.corpevents.main.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por fazer a conexão com o banco de dados
 */
public class DBConnection {

    public DBConnection() {

    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost/corpevents?" + "user=admin&password=admin");
        // Configura a conexão com o banco de dados

        return connection;
    }
}
