package com.alexanderhasslund.demo.main.Engine;

import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DatabaseWriter {


    public void writtingPlayersToDatabase(List<Player> playerList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToPlayer = "INSERT INTO dungeonrun.player (PlayerId, PlayerName, Experience, Currency, Level, Class, BelongsToPartyId) VALUES (?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(addDataToPlayer)) {

                for (Player player : playerList) {
                    preparedStatement.setInt(1, player.getId());
                    preparedStatement.setString(2, player.getName());
                    preparedStatement.setInt(3, player.getExperience());
                    preparedStatement.setInt(4, player.getCurrency());
                    preparedStatement.setInt(5, player.getLevel());
                    preparedStatement.setString(6, player.getClassNameSQL());
                    preparedStatement.setString(7, player.getPartyId());

                    int rowsAffected = preparedStatement.executeUpdate();

                    //System.out.println(rowsAffected + "row(s) inserted successfully");
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }

    public void updatePlayerLevelDatabase(Player player) {

        try (Connection connection = DatabaseConnector.getConnection()) {
            String updatePlayer = "UPDATE dungeonrun.player SET Level = ? WHERE " + "playerId = ? " + "and BelongsToPartyId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayer)) {

                        preparedStatement.setInt(1, player.getLevel());
                        preparedStatement.setInt(2,player.getId());
                        preparedStatement.setString(3,player.getPartyId());
                        preparedStatement.executeUpdate();

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

    }
}
