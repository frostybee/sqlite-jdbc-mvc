package org.bee.sqlite.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A singleton class that manages an SQLite connection.
 *
 * @author Sleiman R.
 */
public class DBConnectionProvider {

    private static final String DB_FOLDER = "data";
    private static DBConnectionProvider instance;

    private DBConnectionProvider() {

    }

    /**
     * Opens a connection to an SQLite database.
     *
     * @param databaseName the name of the SQLite database file (it can also be
     * a relative path.
     * @return a connection to an SQLite database.
     */
    public Connection getConnection(String databaseName) {
        try {
            String connectionString = String.format("jdbc:sqlite:%s/%s", DB_FOLDER, databaseName);
            Class.forName("org.sqlite.JDBC");
            Connection dbConnection = DriverManager.getConnection(connectionString);
            return dbConnection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static DBConnectionProvider getInstance() {
        if (instance == null) {
            instance = new DBConnectionProvider();
        }
        return instance;
    }
}
