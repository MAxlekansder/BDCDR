package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Inventory.Inventory;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabasePlayerLoader {

    List<Player> playerList = new ArrayList<>();

    public List<Player> getPlayerList() {
        return playerList;
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

                    System.out.println("\nSelected Slot Name: " + selectedSlotName);
                } else {
                    System.out.println("\nNo result found for saveLoad = " + saveLoad);
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
                System.out.println(Color.BLUE + "<--------------------- SAVE SLOTS --------------------->" + Color.RESET);

                while (resultSet.next()) {

                    String saveSlotName = resultSet.getString("SaveSlotName");
                    String saveSlotDate = resultSet.getString("SaveDate");

                    System.out.println(String.format("SLOT: %-5d SAVE SLOT: %-13s  DATE: %s", rowCount, saveSlotName, saveSlotDate));
                    rowCount++;
                }
                System.out.println(Color.BLUE + "<------------------------------------------------------>" + Color.RESET);

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public List<Player> setUpPlayerListFromSave() {


        try (Connection connection = DatabaseConnector.getConnection()) {
            String loadPlayer = "\n" +
                    "select  p.PlayerName, className, p2.PlayerClassId, p3.BelongsToPartyId, p4.SaveSlotName  from dungeonrun.player p \n" +
                    "join dungeonrun.playeractiveclass p2 on p.PlayerId = p2.playerId\n" +
                    "join dungeonrun.class c on p2.ClassId  = c.classId \n" +
                    "join dungeonrun.playerparty p3 on p.PlayerId  = p3.PlayerId \n" +
                    "join dungeonrun.playersave p4 on p.PlayerId = p4.PlayerId \n" +
                    "where p4.SaveSlotName = ?";
            try (PreparedStatement statement = connection.prepareStatement(loadPlayer)) {

                String saveSlot = selectedSlot();
                statement.setString(1, saveSlot);

                try (ResultSet resultSet = statement.executeQuery()) {

                    System.out.println("Trying to fill playerlist... \n");

                    while (resultSet.next()) {
                        int playerClassId = resultSet.getInt("PlayerClassId");
                        String className = resultSet.getString("ClassName");
                        String BelongsToPartyId = resultSet.getString("BelongsToPartyId");
                        Player player = formerClass(className);
                        player.setPlayerId(playerClassId);

                        if (player instanceof Barbarian) {
                            loadBarbarianFromDataBase((Barbarian) player, playerClassId);
                        } else if (player instanceof Rogue) {
                            loadRogueFromDatabase((Rogue) player, playerClassId);
                        } else if (player instanceof Sorcerer) {
                            loadSorcererFromDataBase((Sorcerer) player, playerClassId);
                        }

                        player.setName(resultSet.getString("playerName"));
                        player.setPartyId(BelongsToPartyId);
                        playerList.add(player);
                    }

                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return playerList;
    }

    public void loadBarbarianFromDataBase(Barbarian barbarian, int playerId) {

    }

    public void loadRogueFromDatabase(Rogue rogue, int playerId) {

    }

    public void loadSorcererFromDataBase(Sorcerer sorcerer, int PlayerId) {

    }

    public Player formerClass(String className) {
        switch (className) {
            case "BARBARIAN" -> {
                return new Barbarian();
            }
            case "ROGUE" -> {
                return new Rogue();
            }
            case "SORCERER" -> {
                return new Sorcerer();
            }
            default -> throw new IllegalStateException("Cant load class from data");
        }
    }


    public void updatePlayerList(List<Player> playerList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String getActivePlayerData = "select p.Damage, p.HP, p.Resource, p.Strength, p.Agility, p.Intellect, p.Defence, p.Initiative, p.`level`, p.Experience, p3.Currency from dungeonrun.playeractiveclass p\n" +
                    "join dungeonrun.playerparty p2 on p.playerId = p2.PlayerId \n" +
                    "join dungeonrun.player p3 on p.playerId  = p3.PlayerId \n" +
                    "where p.PlayerClassId = ? and p2.BelongsToPartyId = ?;";
            try (PreparedStatement statement = connection.prepareStatement(getActivePlayerData)) {

                for (Player player : playerList) {

                    statement.setInt(1, player.getPlayerId());
                    statement.setString(2, player.getPartyId());

                    try (ResultSet resultSet = statement.executeQuery()) {

                        while (resultSet.next()) {
                            player.setBaseDamage(resultSet.getInt("Damage"));
                            player.setHp(resultSet.getInt("HP"));
                            player.setResource(resultSet.getInt("Resource"));
                            player.setStrength(resultSet.getInt("Strength"));
                            player.setAgility(resultSet.getInt("Agility"));
                            player.setIntellect(resultSet.getInt("Intellect"));
                            player.setDefence(resultSet.getInt("Defence"));
                            player.setInitiative(resultSet.getInt("Initiative"));
                            player.setLevel(resultSet.getInt("Level"));
                            player.setExperience(resultSet.getInt("Experience"));
                            player.setCurrency(resultSet.getInt("Currency"));

                            player.setDamage(resultSet.getInt("Damage"));
                            player.setMaxHp(resultSet.getInt("HP"));
                            player.setMaxResource(resultSet.getInt("Resource"));
                            player.setBaseStrength(resultSet.getInt("Strength"));
                            player.setBaseAgility(resultSet.getInt("Agility"));
                            player.setBaseIntellect(resultSet.getInt("Intellect"));
                            player.setBaseDefence(resultSet.getInt("Defence"));

                        }
                    }
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

        updatePlayerListInventory(playerList);

    }

    public List<Player> updatePlayerListInventory(List<Player> playerList) {


        for (int i = 0; i < playerList.size(); i++) {
            for (int j = 0; j < 4; j++) {
                playerList.get(i).getInventoryList().add(j, new Inventory("", 0, 0, 0, 0, 0));
            }
        }


        try (Connection connection = DatabaseConnector.getConnection()) {
            String getActivePlayerData = "select * from dungeonrun.playerinventory p \n" +
                    "join dungeonrun.item i on p.itemId = i.ItemId \n" +
                    "join dungeonrun.player p2 on p.PlayerId = p2.PlayerId \n" +
                    "join dungeonrun.playerparty p3 on p.PlayerId  = p3.PlayerId \n" +
                    "where p.PlayerClassId  = ? and p3.BelongsToPartyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(getActivePlayerData)) {

                for (Player player : playerList) {

                    statement.setInt(1, player.getPlayerId());
                    statement.setString(2, player.getPartyId());

                    try (ResultSet resultSet = statement.executeQuery()) {

                        while (resultSet.next()) {
                            String itemName = resultSet.getString("ItemName");
                            int itemDamage = resultSet.getInt("ItemDamage");
                            int itemInitiative = resultSet.getInt("ItemInitiative");
                            int itemLevelLock = resultSet.getInt("ItemLevelLock");
                            int itemDefence = resultSet.getInt("ItemDefence");
                            int itemBlock = resultSet.getInt("itemBlock");
                            int itemSlot = resultSet.getInt("ItemSlot");
                           //player.getInventoryList().add(new Inventory(itemName,itemDamage,));
                            player.getInventoryList().set(itemSlot, new Inventory(itemName, itemDamage, itemInitiative, itemLevelLock, itemDefence, itemBlock));

                        }
                    }
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return playerList;
    }
}

