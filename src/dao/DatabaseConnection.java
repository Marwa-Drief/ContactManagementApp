package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:C:/Users/Marwa/Desktop/contacts.db"
    		+ ""
    		+ "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection();
             var stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS contacts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "prenom TEXT," +
                "telephone TEXT," +
                "email TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}