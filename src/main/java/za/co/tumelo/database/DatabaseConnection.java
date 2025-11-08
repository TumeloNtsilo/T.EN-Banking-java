package za.co.tumelo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:sqlite:bank.db";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Connected to the database");
            return conn;

        } catch (SQLException e) {
            throw new RuntimeException("Connection failed" + e.getMessage());
        }

    }
}