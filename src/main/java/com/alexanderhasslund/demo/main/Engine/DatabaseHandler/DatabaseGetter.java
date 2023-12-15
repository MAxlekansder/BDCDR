package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Engine.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseGetter {

    public void displayingPlayersFromDatabase() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String selectPlayer = "select p.playerName, c.className , p2.`level` , p2.MonsterKilled from dungeonrun.player p \n" +
                                  "join dungeonrun.playeractiveclass p2 on p.PlayerId = p2.playerId \n" +
                                  "join dungeonrun.class c on c.classId = p2.ClassId \n" +
                                  "order by p2.MonsterKilled desc\n" +
                                  "limit 5";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectPlayer)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(Color.YELLOW +"                    MOST MONSTERS KILLED");
                System.out.println("<--------------------- HIGHSCORE LIST --------------------->"+ Color.RESET);

                while (resultSet.next()) {
                    String playerName = resultSet.getString("PlayerName");
                    int playerLevel = resultSet.getInt("Level");
                    int monsterkilled = resultSet.getInt("MonsterKilled");
                   // String saveDate = resultSet.getString("ChangeDate");


                    System.out.println(String.format("NAME: %-10s LEVEL: %-5d MONSTERS KILLED: %d", playerName, playerLevel, monsterkilled));
                    //System.out.println(String.format("NAME: %-15s " + playerName + "      LEVEL: %-5s " + playerLevel + "      MONSTERS KILLED: " + monsterkilled));
                }
                System.out.println(Color.YELLOW + "<---------------------------------------------------------->"+ Color.RESET);


            }  catch(SQLException e){DatabaseConnector.handleSQL(e);}
        } catch (SQLException e) {DatabaseConnector.handleSQL(e);}
    }



    public int checkIfPlayersExists(){
        int checkPlayer = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {

            String checkIfPlayerTableIsempty = "select COUNT(*) from dungeonrun.playerSave";

            try (PreparedStatement statement = connection.prepareStatement(checkIfPlayerTableIsempty)) {

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) { checkPlayer = resultSet.getInt("count(*)");}

            } catch (SQLException e) {DatabaseConnector.handleSQL(e);}
        } catch (SQLException e) {DatabaseConnector.handleSQL(e);}

        return checkPlayer;
    }
}
