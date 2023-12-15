package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    //this is setup for laptop connector database
    private static final String URL = "jdbc:mariadb://localhost:3307/dungeonrun";
    private static final String USER = "javaExecuter";
    private static final String PASSWORD = "javaExecuter";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

    public static void closeConnection(Connection connection) {

        try {
            if (connection != null)  {
                connection.close();
            }
        } catch (SQLException e) {

        }
    }

    public static void handleSQL(SQLException e) {
        System.err.println("SQL broke at: " + e.getMessage());
    }
}
