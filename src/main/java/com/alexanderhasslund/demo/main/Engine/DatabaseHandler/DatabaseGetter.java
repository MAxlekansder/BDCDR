package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Engine.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseGetter {

    public void displayingPlayersFromDatabase() {
        try (Connection connection = DatabaseConnector.getConnection()) {

          /*  String highscorePlayer = "select distinct p.playerName, c.className , p2.`level` , p2.MonsterKilled\n" +
                    ", ifnull(m.HasPartyBeatenLevel,0)  as tagteam\n" +
                    ", ifnull(m2.HasPartyBeatenLevel,0) as twinBro\n" +
                    ", ifnull(m3.HasPartyBeatenLevel,0) as inq\n" +
                    ", ifnull(m4.HasPartyBeatenLevel,0) as thaal\n" +
                    "from dungeonrun.player p\n" +
                    "  join dungeonrun.playeractiveclass p2 on p.PlayerId = p2.playerId\n" +
                    "  join dungeonrun.class c on c.classId = p2.ClassId\n" +
                    "  left join dungeonrun.maplevelcompleted m on m.PlayerId = p.PlayerId and m.MapId = 2\n" +
                    "  left join dungeonrun.maplevelcompleted m2 on m.PlayerId = p.PlayerId and m2.MapId = 3\n" +
                    "  left join dungeonrun.maplevelcompleted m3 on m.PlayerId = p.PlayerId and m3.MapId = 4\n" +
                    "  left join dungeonrun.maplevelcompleted m4 on m.PlayerId = p.PlayerId and m4.MapId = 5\n" +
                    "  #left join dungeonrun.maplevelcompleted m2 on m.PlayerId \n" +
                    "  order by tagTeam desc, twinBro desc, inq desc, thaal desc\n" +
                    "  limit 5";
           */

            String highscorePlayer = "SELECT\n" +
                    "    DISTINCT p.playerName,\n" +
                    "    c.className,\n" +
                    "    p2.`level`,\n" +
                    "    p2.MonsterKilled,\n" +
                    "    COUNT(battleId) AS BattleCount,\n" +
                    "    IFNULL(m.HasPartyBeatenLevel, 0) AS tagteam,\n" +
                    "    IFNULL(m2.HasPartyBeatenLevel, 0) AS twinBro,\n" +
                    "    IFNULL(m3.HasPartyBeatenLevel, 0) AS inq,\n" +
                    "    IFNULL(m4.HasPartyBeatenLevel, 0) AS thaal\n" +
                    "FROM\n" +
                    "    dungeonrun.player p\n" +
                    "JOIN dungeonrun.playeractiveclass p2 ON p.PlayerId = p2.playerId\n" +
                    "JOIN dungeonrun.class c ON c.classId = p2.ClassId\n" +
                    "LEFT JOIN dungeonrun.maplevelcompleted m ON m.PlayerId = p.PlayerId AND m.MapId = 2\n" +
                    "LEFT JOIN dungeonrun.maplevelcompleted m2 ON m2.PlayerId = p.PlayerId AND m2.MapId = 3\n" +
                    "LEFT JOIN dungeonrun.maplevelcompleted m3 ON m3.PlayerId = p.PlayerId AND m3.MapId = 4\n" +
                    "LEFT JOIN dungeonrun.maplevelcompleted m4 ON m4.PlayerId = p.PlayerId AND m4.MapId = 5\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT DISTINCT playerId, battleId\n" +
                    "    FROM dungeonrun.monsterplayerfight m5\n" +
                    ") AS cc ON cc.playerId = p.playerId\n" +
                    "-- other conditions and ordering remain the same\n" +
                    "ORDER BY tagteam DESC, twinBro DESC, inq DESC, thaal DESC\n" +
                    "LIMIT 5;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(highscorePlayer)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(Color.YELLOW +"                                                        MOST MONSTERS KILLED");
                System.out.println("<---------------------------------------------------------- HIGHSCORE LIST ---------------------------------------------------------->\n"+ Color.RESET);

                while (resultSet.next()) {
                    String playerName = resultSet.getString("PlayerName");
                    int playerLevel = resultSet.getInt("Level");
                    int monsterkilled = resultSet.getInt("MonsterKilled");
                    int battleCount = resultSet.getInt("BattleCount");
                    int tagTeam = resultSet.getInt("tagteam");
                    int twinbrothers = resultSet.getInt("twinBro");
                    int inq = resultSet.getInt("inq");
                    int thaal = resultSet.getInt("thaal");
                   // String saveDate = resultSet.getString("ChangeDate");

                    System.out.println(String.format("NAME: %-10s LEVEL: %-5d ENCOUNTERS: %-5d MONSTERS KILLED: %-3d TAG TEAM: %-3d TWIN BROTHERS: %-3d THE INQUISITION: %-3d THAAL: %-3d", playerName, playerLevel, battleCount, monsterkilled, tagTeam, twinbrothers, inq, thaal));

                    //System.out.println(String.format("NAME: %-10s LEVEL: %-5d MONSTERS KILLED: %d", playerName, playerLevel, monsterkilled));
                    //System.out.println(String.format("NAME: %-15s " + playerName + "      LEVEL: %-5s " + playerLevel + "      MONSTERS KILLED: " + monsterkilled));
                }
                System.out.println(Color.YELLOW + "\n<------------------------------------------------------------------------------------------------------------------------------------>"+ Color.RESET);


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
