package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection get_connection() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/gameGradle";
        String username = "postgres";
        String password = "1234";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Successful connect to \"gameGradle\"");
            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }
}