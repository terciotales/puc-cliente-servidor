package sicof.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection = null;

    public DBConnection() {

    }

    public Connection getConnection() {
        try {
            // Carrega o driver MySQL, cada banco de dados tem seu próprio driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return null;
        }

        try {
            // Configura a conexão com o banco de dados
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/sicof?" + "user=admin&password=admin");
        } catch (SQLException e) {
            return null;
        }

        return this.connection;
    }
}
