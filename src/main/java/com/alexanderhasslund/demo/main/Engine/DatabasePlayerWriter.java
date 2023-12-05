package com.alexanderhasslund.demo.main.Engine;

import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabasePlayerWriter {


    public void writtingPlayersToDatabase(List<Player> playerList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToPlayer = "INSERT INTO dungeonrun.player (PlayerClassId, PlayerName, Experience, Currency, Level, BelongsToPartyId, MonsterKilled) VALUES (?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(addDataToPlayer)) {

                for (Player player : playerList) {
                    preparedStatement.setInt(1, player.getId());
                    preparedStatement.setString(2, player.getName());
                    preparedStatement.setInt(3, player.getExperience());
                    preparedStatement.setInt(4, player.getCurrency());
                    preparedStatement.setInt(5, player.getLevel());
                    preparedStatement.setString(6, player.getPartyId());
                    preparedStatement.setInt(7,0);

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

    public void updatePlayerhasDied(Player player) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String updatePlayer = "UPDATE dungeonrun.classes SET isDead = 1 WHERE " + "PlayerClassId = ? " + "and BelongsToPartyId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayer)) {

                preparedStatement.setInt(1, player.getId());
                preparedStatement.setString(2, player.getPartyId());
                preparedStatement.executeUpdate();

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


    public void updateMonsterKilledByPlayer(Player player,int monsterKilled) {

        try (Connection connection = DatabaseConnector.getConnection()) {
            String updatePlayer = "UPDATE dungeonrun.player SET MonsterKilled = MonsterKilled + ? WHERE " + "playerClassId = ? " + "and BelongsToPartyId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayer)) {

                preparedStatement.setInt(1, monsterKilled); // need to get monster kill count from playerAttack
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
