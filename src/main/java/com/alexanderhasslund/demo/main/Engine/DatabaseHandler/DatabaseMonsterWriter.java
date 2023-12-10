package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabaseConnector;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseMonsterWriter {

    public void writeMonsterToDatabase(List<Monster> monsterList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToClasses = "INSERT INTO dungeonrun.monster (MonsterType, MonsterName, MonsterHP, MonsterResource, MonsterDamage, MonsterInititaive, MonsterDefence, MonsterStrength, MonsterAgility, MonsterIntellect, MonsterIsDead, MonsterGivesExperience, MonsterGivesCurrency) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement statement = connection.prepareStatement(addDataToClasses)) {

                for (Monster monster : monsterList) {

                    statement.setString(1, monster.getTypeName());
                    statement.setString(2, monster.getMonsterName());
                    statement.setInt(3, monster.getHp());
                    statement.setInt(4, monster.getResoruce());
                    statement.setInt(5, monster.getDamage());
                    statement.setInt(6, monster.getInitiative());
                    statement.setInt(7, monster.getDefence());
                    statement.setInt(8, monster.getStrength());
                    statement.setInt(9, monster.getAgility());
                    statement.setInt(10, monster.getIntellect());
                    statement.setInt(11,0);
                    statement.setInt(12, monster.getGivesExperience());
                    statement.setInt(13, monster.getGivesCurrency());

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
