package com.alexanderhasslund.demo.main;
import com.alexanderhasslund.demo.main.PlayerInteraction.GameStartControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        GameStartControl gameStartControl = new GameStartControl();

        //gameStartControl.startIntroductionGame();
        String url = "jdbc:mariadb://localhost:3307/dungeonrun";
        String user = "root";
        String password = "1337";

        // Try to connect
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the MariaDB server (Version: " + connection.getMetaData().getDatabaseProductVersion() + ")");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


