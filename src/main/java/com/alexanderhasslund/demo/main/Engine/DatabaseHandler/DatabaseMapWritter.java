package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Maps.GameLevelFloors.CityMarkazh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMapWritter {

    public void writingMap(String mapName) {
        if (checkMapExists(mapName) == 0) {
            try (Connection connection = DatabaseConnector.getConnection()) {
                String addMap = "INSERT INTO dungeonrun.Map (MapName) VALUES(?)";

                try (PreparedStatement statement = connection.prepareStatement(addMap)) {
                    statement.setString(1, mapName);
                    statement.executeUpdate();

                } catch (SQLException e) {
                    DatabaseConnector.handleSQL(e);
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        }
    }


    public int checkMapExists(String mapName) {
        int checkMap = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkEmpty = "SELECT COUNT(*) FROM dungeonrun.Map where MapName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkEmpty)) {

                statement.setString(1, mapName);

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) { checkMap = resultSet.getInt("count(*)");}

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return checkMap;
    }


    public void namingMap() {
        String TheHallsOfKaraz =  "The Halls of Karaz";
        writingMap(TheHallsOfKaraz);
        String upperPlateau = "Upper plateau";
        writingMap(upperPlateau);
        String cityMarkazh = "City Markazh";
        writingMap(cityMarkazh);
        String finalRoom = "Final Room of Kazarak";
        writingMap(finalRoom);
    }
}
