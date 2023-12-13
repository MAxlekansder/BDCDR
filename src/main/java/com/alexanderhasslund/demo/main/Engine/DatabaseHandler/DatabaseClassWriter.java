package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseClassWriter {

    public void writeActivePlayerClass(List<Player> playerList) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addPlayerActiveClass = "INSERT INTO dungeonrun.PlayerActiveClass (playerId, PlayerClassId, ClassId, HP, Damage, Resource, Strength, Agility, Intellect, Defence, Initiative, Level, Experience, MonsterKilled, isDead) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement statement = connection.prepareStatement(addPlayerActiveClass)) {

                for (Player player : playerList) {
                    statement.setInt(1, databasePlayerWriter.getPlayerId(player));
                    statement.setInt(2, player.getId());
                    statement.setInt(3, getClassId(player));
                    statement.setInt(4, player.getMaxHp());
                    statement.setInt(5, player.getBaseDamage());
                    statement.setInt(6, player.getMaxResource());
                    statement.setInt(7, player.getBaseStrength());
                    statement.setInt(8, player.getBaseAgility());
                    statement.setInt(9, player.getBaseIntellect());
                    statement.setInt(10, player.getBaseDefence());
                    statement.setInt(11, player.getInitiative());
                    statement.setInt(12, player.getLevel());
                    statement.setInt(13, player.getExperience());
                    statement.setInt(14,0);
                    statement.setInt(15, 0);
                    statement.executeUpdate();

                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public void writeClass(Player player) {
        if (checkClassExists(player) == 0) {
            try (Connection connection = DatabaseConnector.getConnection()) {
                String addDataToClasses = "INSERT INTO dungeonrun.class (ClassName, MaxHP, Damage, MaxResource, BaseStrength, BaseAgility, BaseIntellect, BaseDefence, Initiative) VALUES (?,?,?,?,?,?,?,?,?)";

                try (PreparedStatement statement = connection.prepareStatement(addDataToClasses)) {

                    statement.setString(1, player.getClassNameSQL());
                    statement.setInt(2, player.getMaxHp());
                    statement.setInt(3, player.getBaseDamage());
                    statement.setInt(4, player.getMaxResource());
                    statement.setInt(5, player.getBaseStrength());
                    statement.setInt(6, player.getBaseAgility());
                    statement.setInt(7, player.getBaseIntellect());
                    statement.setInt(8, player.getBaseDefence());
                    statement.setInt(9, player.getInitiative());
                    statement.executeUpdate();


                } catch (SQLException e) {
                    DatabaseConnector.handleSQL(e);
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        }

    }

    public int checkClassExists(Player player) {
        int classExists = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkExists = "SELECT COUNT(*) FROM dungeonrun.Class where ClassName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkExists)) {
                statement.setString(1, player.getClassNameSQL());

                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) { classExists = resultSet.getInt("count(*)");}

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return classExists;

    }

    public int getClassId(Player player) {
        int checkClass = 0;

        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkClassId = "SELECT ClassId FROM dungeonrun.Class where ClassName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkClassId)) {
                statement.setString(1, player.getClassNameSQL());
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    checkClass = resultSet.getInt("ClassId");
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return checkClass;
        }



    public void getClassInformation() {
        writeClass(new Barbarian());
        writeClass(new Rogue());
        writeClass(new Sorcerer());
    }

}

