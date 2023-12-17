package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Maps.GameLevelFloors.CityMarkazh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMapWritter {

    public void writingMap(String mapName, int sortingId) {
        if (checkMapExists(mapName) == 0) {
            try (Connection connection = DatabaseConnector.getConnection()) {
                String addMap = "INSERT INTO dungeonrun.Map (MapName, SortingId) VALUES(?,?)";

                try (PreparedStatement statement = connection.prepareStatement(addMap)) {
                    statement.setString(1, mapName);
                    statement.setInt(2,sortingId);
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

    //this is the biggest bandage in the world for making the logic work...
    public void namingMap() {
        String TheHallsOfKaraz =  "The Halls of Karaz";
        int sortingId = 1;
        writingMap(TheHallsOfKaraz, sortingId);

        String upperPlateau = "Upper plateau";
        sortingId = 2;
        writingMap(upperPlateau, sortingId);

        String cityMarkazh = "City Markazh";
        sortingId = 3;
        writingMap(cityMarkazh, sortingId);

        String finalRoom = "Final Room of Kazarak";
        sortingId = 4;
        writingMap(finalRoom, sortingId);

        String firstIntro = "Intro floor";
        sortingId = 1;
        writingMap(firstIntro, sortingId);
    }


    public int getMapId(int mapId) {
        int getMap = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkEmpty = "select m.mapId from dungeonrun.map m join dungeonrun.maplevelcompleted m2 on m.SortingId = m2.SortingId";

            try (PreparedStatement statement = connection.prepareStatement(checkEmpty)) {

                statement.setInt(1, mapId);

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) { getMap = resultSet.getInt("count(*)");}

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return getMap;
    }
}
