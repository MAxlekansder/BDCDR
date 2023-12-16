package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabasePlayerLoader {


    public void loadingPlayerFromDatabase() {
        System.out.println("What save do you want to load?");
        String query = "\n" +
                "select distinct * from dungeonrun.playersave p join dungeonrun.playeractiveclass p2 on p.PlayerId  = p2.playerId \n" +
                "where p.SaveSlotName ='?' \n" +
                "and p2.isDead = 0";
    }


    public String selectedSlot() {
        System.out.print("Choose a slot to load: ");
        int saveLoad = Input.intInput();

        String saveLoadFile = "select saveSlotName\n" +
                "FROM (\n" +
                "select DENSE_RANK() OVER (ORDER BY p.RegistrationDate DESC) as countRow , SaveSlotName\n" +
                "from dungeonrun.playersave p \n" +
                "group by SaveSlotName \n" +
                "order by p.RegistrationDate desc\n" +
                "limit 7\n" +
                ") as test1\n" +
                "where countRow = ?";

        String selectedSlotName = null;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(saveLoadFile)) {

            statement.setInt(1, saveLoad);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    selectedSlotName = resultSet.getString("saveSlotName");

                    System.out.println("Selected Slot Name: " + selectedSlotName);
                } else {
                    System.out.println("No result found for saveLoad = " + saveLoad);
                }
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

        return selectedSlotName;
    }


    public void chooseRunToLoad() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String selectPlayer = "select distinct SaveSlotName, DATE(RegistrationDate) as SaveDate from dungeonrun.playersave p order by RegistrationDate desc limit 7 ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectPlayer)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                int rowCount = 1;
                System.out.println(Color.BLUE +"<--------------------- SAVE SLOTS --------------------->"+ Color.RESET);

                while (resultSet.next()) {

                    String saveSlotName = resultSet.getString("SaveSlotName");
                    String saveSlotDate = resultSet.getString("SaveDate");

                    System.out.println(String.format("SLOT: %-5d SAVE SLOT: %-13s  DATE: %s", rowCount, saveSlotName, saveSlotDate));
                    rowCount++;
                }
                System.out.println(Color.BLUE + "<------------------------------------------------------>"+ Color.RESET);

            }  catch(SQLException e){DatabaseConnector.handleSQL(e);}
        } catch (SQLException e) {DatabaseConnector.handleSQL(e);}
    }

    public List<Player> setUpPlayerListFromSave() {
        List<Player> playerList = new ArrayList<>();

        try (Connection connection =  DatabaseConnector.getConnection()) {
            String loadPlayer = "\n" +
                    "select  p.PlayerName, className, p2.PlayerClassId, p3.BelongsToPartyId, p4.SaveSlotName  from dungeonrun.player p \n" +
                    "join dungeonrun.playeractiveclass p2 on p.PlayerId = p2.playerId\n" +
                    "join dungeonrun.class c on p2.ClassId  = c.classId \n" +
                    "join dungeonrun.playerparty p3 on p.PlayerId  = p3.PlayerId \n" +
                    "join dungeonrun.playersave p4 on p.PlayerId = p4.PlayerId \n" +
                    "where p4.SaveSlotName = ?";
            try (PreparedStatement statement = connection.prepareStatement(loadPlayer)) {

                String saveSlot = selectedSlot();
                statement.setString(1,saveSlot);

                try (ResultSet resultSet = statement.executeQuery()) {

                    System.out.println("Trying to fill playerlist...");

                    while (resultSet.next()) {
                        int playerClassId = resultSet.getInt("PlayerClassId");
                        String className = resultSet.getString("ClassName");
                        Player player = formerClass(className);
                        player.setPlayerId(playerClassId);

                        if (player instanceof Barbarian) { loadBarbarianFromDataBase((Barbarian) player, playerClassId);
                        } else if (player instanceof Rogue) { loadRogueFromDatabase((Rogue) player, playerClassId);
                        } else if (player instanceof Sorcerer) { loadSorcererFromDataBase((Sorcerer) player, playerClassId);}

                        player.setName(resultSet.getString("playerName"));
                        playerList.add(player);
                    }
                }
            } catch (SQLException e) {DatabaseConnector.handleSQL(e); }
        } catch (SQLException e) { DatabaseConnector.handleSQL(e); }
        return playerList;
    }

    public void loadBarbarianFromDataBase(Barbarian barbarian, int playerId) {

    }

    public void loadRogueFromDatabase(Rogue rogue, int playerId) {

    }

    public void loadSorcererFromDataBase(Sorcerer sorcerer, int PlayerId) {

    }

    public Player formerClass(String className) {
        switch(className) {
            case "BARBARIAN" -> { return new Barbarian();}
            case "ROGUE" -> { return new Rogue(); }
            case "SORCERER" -> { return new Sorcerer(); }
            default ->  throw new IllegalStateException("Cant load class from data");
        }
    }
}

