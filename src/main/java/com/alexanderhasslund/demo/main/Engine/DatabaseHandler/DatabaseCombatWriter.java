package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class DatabaseCombatWriter {



    public void playerAttackMonster(Player player, List<Monster> monsterList, int monsterIndex, int playerDamageDone, int calculateLevel, String battleId, String typeOfAttack, int countRounds) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        DatabaseMonsterWriter databaseMonsterWriter = new DatabaseMonsterWriter();

        Monster monster = monsterList.get(monsterIndex);

        try (Connection connection = DatabaseConnector.getConnection()) {
            String addFight = "INSERT INTO dungeonrun.monsterplayerfight (playerId, monsterId, MapId, AttackingUnit, Attacked, MonsterFightId, DamageDone, DamageTaken, TypeOfAbility, HasFled, GameRound, BattleId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(addFight)) {

                statement.setInt(1, databasePlayerWriter.writePlayerId(player));
                statement.setInt(2, databaseMonsterWriter.getMonsterId(monster));
                statement.setInt(3, calculateLevel);
                statement.setString(4, player.getClassNameSQL());
                statement.setString(5, monster.getMonsterName());
                statement.setInt(6,monster.getMonsterId());
                statement.setInt(7, playerDamageDone);
                statement.setInt(8,monster.getHp());
                statement.setString(9, typeOfAttack);
                statement.setInt(10,0);
                statement.setInt(11,countRounds);
                statement.setString(12, battleId);
                statement.executeUpdate();

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }

    /*
    public void playerAttackManyMonster(Player player, List<Monster> monsterList, int playerDamageDone, int calculateLevel, String battleId, String typeOfAttack, int countRounds) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        DatabaseMonsterWriter databaseMonsterWriter = new DatabaseMonsterWriter();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String addFight = "INSERT INTO dungeonrun.monsterplayerfight (playerId, monsterId, MapId, AttackingUnit, Attacked, MonsterFightId, DamageDone, DamageTaken, TypeOfAbility, HasFled, GameRound, BattleId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(addFight)) {

                for (Monster monster : monsterList) {
                    statement.setInt(1, databasePlayerWriter.getPlayerId(player));
                    statement.setInt(2, databaseMonsterWriter.getMonsterId(monster));
                    statement.setInt(3, calculateLevel);
                    statement.setString(4, player.getClassNameSQL());
                    statement.setString(5, monster.getMonsterName());
                    statement.setInt(6,monster.getMonsterId());
                    statement.setInt(7, playerDamageDone);
                    statement.setInt(8,monster.getHp() - playerDamageDone);
                    statement.setString(9, typeOfAttack);
                    statement.setInt(10,0);
                    statement.setInt(11,countRounds);
                    statement.setString(12, battleId);
                    statement.executeUpdate();
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }*/


    public void MonsterAttackPlayer(Monster monster, List<Player> playerList, int playerIndex, int monsterDamageDone, int calculateLevel, String battleId, String typeOfAttack, int countRounds) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        DatabaseMonsterWriter databaseMonsterWriter = new DatabaseMonsterWriter();
        Player player = playerList.get(playerIndex);

        try (Connection connection = DatabaseConnector.getConnection()) {
            String addFight = "INSERT INTO dungeonrun.monsterplayerfight (playerId, monsterId, MapId, AttackingUnit, Attacked, MonsterFightId, DamageDone, DamageTaken, TypeOfAbility, HasFled, GameRound, BattleId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(addFight)) {

                statement.setInt(1, databasePlayerWriter.writePlayerId(player));
                statement.setInt(2, databaseMonsterWriter.getMonsterId(monster));
                statement.setInt(3, calculateLevel);
                statement.setString(4, monster.getMonsterName());
                statement.setString(5, player.getClassNameSQL());
                statement.setInt(6,monster.getMonsterId());
                statement.setInt(7, monsterDamageDone);
                statement.setInt(8,player.getHp());
                statement.setString(9, typeOfAttack);
                statement.setInt(10,0);
                statement.setInt(11,countRounds);
                statement.setString(12, battleId);
                statement.executeUpdate();

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }

    public void monsterAttackManyPlayer(Monster monster, List<Player> playerList, int monsterDamageDone) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        DatabaseMonsterWriter databaseMonsterWriter = new DatabaseMonsterWriter();


        try (Connection connection = DatabaseConnector.getConnection()) {
            String addFight = "INSERT INTO dungeonrun.monsterplayerfight (playerId, monsterId, MapId, AttackingUnit, Attacked, MonsterFightId, DamageDone, DamageTaken, BattleId) VALUES(?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(addFight)) {

                for(Player player : playerList) {
                    statement.setInt(1, databasePlayerWriter.writePlayerId(player));
                    statement.setInt(2, databaseMonsterWriter.getMonsterId(monster));
                    statement.setInt(3, 0);
                    statement.setInt(4, databaseMonsterWriter.getMonsterId(monster));
                    statement.setInt(5, databasePlayerWriter.writePlayerId(player));
                    statement.setInt(6, monster.getMonsterId());
                    statement.setInt(7, monsterDamageDone);
                    statement.setInt(8, player.getHp() - monsterDamageDone);
                    statement.setString(9, "x");
                    statement.executeUpdate();
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public String getBattleId() {

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generator = random.ints(leftLimit, rightLimit +1 ).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generator;
    }
}
