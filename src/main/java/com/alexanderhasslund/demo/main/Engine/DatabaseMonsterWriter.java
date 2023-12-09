package com.alexanderhasslund.demo.main.Engine;

import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseMonsterWriter {

    public void writeClassToDatabase(List<Player> playerList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToClasses = "INSERT INTO dungeonrun.monster ( PlayerClassId, ClassName, MaxHP, Damage, MaxResource, BaseStrength, BaseAgility, BaseIntellect, BaseDefence, Initiative, isDead, BelongsToPartyId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement statement = connection.prepareStatement(addDataToClasses)) {

                for (Player player : playerList) {

                    statement.setInt(2, player.getId());
                    statement.setString(3, player.getClassNameSQL());
                    statement.setInt(4, player.getMaxHp());
                    statement.setInt(5, player.getBaseDamage());
                    statement.setInt(6, player.getMaxResource());
                    statement.setInt(7, player.getBaseStrength());
                    statement.setInt(8, player.getBaseAgility());
                    statement.setInt(9, player.getBaseIntellect());
                    statement.setInt(10, player.getBaseDefence());
                    statement.setInt(11, player.getInitiative());
                    statement.setInt(12, 0);
                    statement.setString(13, player.getPartyId());

                    int rowsAffected = statement.executeUpdate();

                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }
}
