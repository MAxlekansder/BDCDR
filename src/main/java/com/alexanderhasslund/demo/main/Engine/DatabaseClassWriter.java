package com.alexanderhasslund.demo.main.Engine;

import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseClassWriter {

    public void writeClassToDatabase(List<Player> playerList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToPlayer = "INSERT INTO dungeonrun.classes (PlayerClassId, ClassName, MaxHP, Damage, MaxResource, BaseStrength, BaseAgility, BaseIntellect, BaseDefence, Initiative, isDead, BelongsToPartyId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(addDataToPlayer)) {

                for (Player player : playerList) {
                    preparedStatement.setInt(1,player.getId());
                    preparedStatement.setString(2,player.getClassNameSQL());
                    preparedStatement.setInt(3, player.getMaxHp());
                    preparedStatement.setInt(4, player.getBaseDamage());
                    preparedStatement.setInt(5, player.getMaxResource());
                    preparedStatement.setInt(6, player.getBaseStrength());
                    preparedStatement.setInt(7, player.getBaseAgility());
                    preparedStatement.setInt(8, player.getBaseIntellect());
                    preparedStatement.setInt(9, player.getBaseDefence());
                    preparedStatement.setInt(10, player.getInitiative());
                    preparedStatement.setInt(11,0);
                    preparedStatement.setString(12,player.getPartyId());

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
}
