package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;
import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseGetter {

    public void gettingPlayersFromDatabase() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String selectPlayer = "SELECT PlayerId, PlayerName, Experience, Level FROM dungeonrun.player ORDER BY Level";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectPlayer)) {

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int playerId = resultSet.getInt("playerId");
                    String playerName = resultSet.getString("PlayerName");
                    int playerExperience = resultSet.getInt("Experience");
                    int playerLevel = resultSet.getInt("Level");

                    System.out.println("NAME: " + playerName + "LEVEL: " + playerLevel);
                }

            }  catch(SQLException e){
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }
}
