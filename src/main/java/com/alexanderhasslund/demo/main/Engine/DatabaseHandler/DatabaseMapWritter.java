package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Maps.GameLevelFloors.CityMarkazh;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    //this is the biggest bandage in the world for making the logic work...
    public void TheHallsOfKaraz() {
        String TheHallsOfKaraz = "The Halls of Karaz";

        writingMap(TheHallsOfKaraz);

    }
    public void upperPlateau() {
        String upperPlateau = "Upper plateau";

        writingMap(upperPlateau);
    }

    public void cityMarkazh() {

        String cityMarkazh = "City Markazh";

        writingMap(cityMarkazh);
    }

    public void finalRoom() {
        String finalRoom = "Final Room of Kazarak";

        writingMap(finalRoom);
    }


    public void firstIntro() {
        String firstIntro = "Intro floor";

        writingMap(firstIntro);
    }




    public int getMapId(int mapId) {
        int getMap = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkEmpty = "select m.mapId from dungeonrun.map m join dungeonrun.maplevelcompleted m2 on m.SortingId = m2.SortingId";

            try (PreparedStatement statement = connection.prepareStatement(checkEmpty)) {

                statement.setInt(1, mapId);

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) { getMap = resultSet.getInt("mapId");}

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return getMap;
    }

    public int getMapLevel (List<Player> playerList) {
        int mapId = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            //String findMapId = "select distinct max(mapId) as mapId from dungeonrun.maplevelcompleted m join dungeonrun.playerparty p on m.PlayerId = p.PlayerId where p.BelongsToPartyId = ?";
            String findMapId = "select distinct max(m.MapId) as MapId from dungeonrun.maplevelcompleted m join dungeonrun.playerparty p on m.PlayerId = p.PlayerId join dungeonrun.`map` m2 on m.MapId = m2.MapId where p.BelongsToPartyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(findMapId)) {
                statement.setString(1, playerList.get(0).getPartyId());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mapId = resultSet.getInt("MapId");
                    }
                }

            } catch (SQLException e) { DatabaseConnector.handleSQL(e); }
        } catch (SQLException e) { DatabaseConnector.handleSQL(e); }
        return mapId;
    }

    public int getMapLevelPerPlayer (Player player) {
        int mapId = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            //String findMapId = "select distinct max(mapId) as mapId from dungeonrun.maplevelcompleted m join dungeonrun.playerparty p on m.PlayerId = p.PlayerId where p.BelongsToPartyId = ?";
            String findMapId = "select distinct max(m.MapId) as MapId from dungeonrun.maplevelcompleted m join dungeonrun.playerparty p on m.PlayerId = p.PlayerId join dungeonrun.`map` m2 on m.MapId = m2.MapId where p.BelongsToPartyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(findMapId)) {
                statement.setString(1, player.getPartyId());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        mapId = resultSet.getInt("MapId");
                    }
                }

            } catch (SQLException e) { DatabaseConnector.handleSQL(e); }
        } catch (SQLException e) { DatabaseConnector.handleSQL(e); }
        return mapId;
    }
}
