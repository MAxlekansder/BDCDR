package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabaseConnector;
import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterBrute;
import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterRanger;
import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterSpellWeaver;
import com.alexanderhasslund.demo.main.Monster.Boss.LastBossThaal.Thaal;
import com.alexanderhasslund.demo.main.Monster.Boss.TagTeam.TagTeam;
import com.alexanderhasslund.demo.main.Monster.Boss.TheTwinBrothers.Bram;
import com.alexanderhasslund.demo.main.Monster.Boss.TheTwinBrothers.Ohrum;
import com.alexanderhasslund.demo.main.Monster.Boss.theInquisition.theInquisition;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.Shop.Defence.Shields;
import com.alexanderhasslund.demo.main.Shop.Potions.Potion;
import com.alexanderhasslund.demo.main.Shop.Weapon.Swords;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseMonsterWriter {

    public void writeMonster(Monster monster) {
        if (checkMonsterExists(monster) == 0 ) {
            try (Connection connection = DatabaseConnector.getConnection()) {
                String addDataToClasses = "INSERT INTO dungeonrun.monster (MonsterType, MonsterName, MonsterHP, MonsterResource, MonsterDamage, MonsterInititaive, MonsterDefence, MonsterStrength, MonsterAgility, MonsterIntellect, MonsterIsDead, MonsterGivesExperience, MonsterGivesCurrency) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                try (PreparedStatement statement = connection.prepareStatement(addDataToClasses)) {

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
                    statement.setInt(11, 0);
                    statement.setInt(12, monster.getGivesExperience());
                    statement.setInt(13, monster.getGivesCurrency());
                    statement.executeUpdate();


                } catch (SQLException e) {
                    DatabaseConnector.handleSQL(e);
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        }
    }

    public int checkMonsterExists(Monster monster) {
        int monsterExists = 0;

        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkExists = "SELECT COUNT(*) FROM dungeonrun.monster where MonsterName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkExists)) {
                statement.setString(1, monster.getMonsterName());

                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) { monsterExists = resultSet.getInt("count(*)");}

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return monsterExists;
    }

    public int getMonsterId (Monster monster) {
        int checkMonster = 0;

        try(Connection connection = DatabaseConnector.getConnection()) {
            String checkMonsterId = "SELECT MonsterId FROM dungeonrun.monster where MonsterName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkMonsterId)) {
                statement.setString(1, monster.getMonsterName());
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    checkMonster = resultSet.getInt("MonsterId");
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return checkMonster;
    }

    public void writingBasicMonster() {
        writeMonster(new MonsterBrute());
        writeMonster(new MonsterRanger());
        writeMonster(new MonsterSpellWeaver());
    }

    public void writingBoss() {
        writeMonster(new TagTeam());
        writeMonster(new Ohrum());
        writeMonster(new Bram());
        writeMonster(new theInquisition());
        writeMonster(new Thaal());
    }



}
