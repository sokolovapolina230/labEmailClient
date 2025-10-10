package emailclient.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:emailclient.db");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

