package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice.saveFileChoice;

public class DatabasePlayerWriter {


    public void writtingPlayersToDatabase(List<Player> playerList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToPlayer = "INSERT INTO dungeonrun.player (PlayerClassId, PlayerName, Currency) VALUES (?,?,?)";


            try (PreparedStatement statement = connection.prepareStatement(addDataToPlayer)) {

                for (Player player : playerList) {
                    statement.setInt(1, player.getId());
                    statement.setString(2, player.getName());
                    statement.setInt(3, player.getCurrency());
                    statement.setInt(4, 0);
                    statement.executeUpdate();

                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public void writingPlayerToParty(List<Player> playerList) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToPlayer = "INSERT INTO dungeonrun.playerParty (PlayerId, BelongsToPartyId) VALUES (?,?)";


            try (PreparedStatement statement = connection.prepareStatement(addDataToPlayer)) {

                for (Player player : playerList) {
                    statement.setInt(1, writePlayerId(player));
                    statement.setString(2, player.getPartyId());
                    statement.executeUpdate();

                    //System.out.println(rowsAffected + "row(s) inserted successfully");
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public void updatePlayerhasDied(Player player) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String updatePlayer = "UPDATE dungeonrun.playeractiveclass p join dungeonrun.playerparty p1 on p.playerId = p1.PlayerId  SET isDead = 1 WHERE " + "PlayerClassId = ? " + "and BelongsToPartyId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayer)) {

                preparedStatement.setInt(1, player.getId());
                preparedStatement.setString(2, player.getPartyId());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public void updatePlayerLevelDatabase(Player player) {

        try (Connection connection = DatabaseConnector.getConnection()) {

            String updatePlayer = "UPDATE dungeonrun.playeractiveclass p join dungeonrun.playerparty p2 on p.PlayerId = p2.PlayerId SET Level = ? WHERE " + "p.playerClassId = ? " + "and p2.BelongsToPartyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayer)) {

                preparedStatement.setInt(1, player.getLevel());
                preparedStatement.setInt(2, player.getId());
                preparedStatement.setString(3, player.getPartyId());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public void updateMonsterKilledByPlayer(Player player, int monsterKilled) {

        try (Connection connection = DatabaseConnector.getConnection()) {
            String updatePlayer = "UPDATE dungeonrun.playeractiveclass p join dungeonrun.playerparty p2 on p.PlayerId = p2.PlayerId SET MonsterKilled = MonsterKilled + ? WHERE " + "p.playerClassId = ? " + "and p2.BelongsToPartyId = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayer)) {

                preparedStatement.setInt(1, monsterKilled); // need to get monster kill count from playerAttack
                preparedStatement.setInt(2, player.getId());
                preparedStatement.setString(3, player.getPartyId());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public void addingPlayerInventory(Player player, int itemSlot, int itemPrice, int itemIndex) {
        DatabaseItemCollector databaseItemCollector = new DatabaseItemCollector();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String addDataToPlayer = "INSERT INTO dungeonrun.playerInventory (PlayerId, ItemId, PlayerClassId, ItemSlot, ItemPrice) VALUES (?,?,?,?,?)";


            try (PreparedStatement statement = connection.prepareStatement(addDataToPlayer)) {

                statement.setInt(1, getPlayerId(player));
                statement.setInt(2, databaseItemCollector.checkItemId(player, itemIndex));
                statement.setInt(3, player.getId());
                statement.setInt(4, itemSlot);
                statement.setInt(5, itemPrice);
                statement.executeUpdate();

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public int writePlayerId(Player player) {
        int playerId = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String selectPlayerId = "select PlayerId from dungeonrun.player p where PlayerClassId  = ?" + " and RegistrationDate in (select max(RegistrationDate) from dungeonrun.player p2 where PlayerClassId  = ? )";
            //String selectPlayerId = "select p.playerId from dungeonrun.player p join dungeonrun.playerparty p2 on p.PlayerId = p2.PlayerId where p.PlayerClassId = ? and p2.BelongsToPartyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(selectPlayerId)) {

                statement.setInt(1, player.getId());
                statement.setString(2, player.getPartyId());

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    playerId = resultSet.getInt("PlayerId");
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

        return playerId;
    }


    public int getPlayerId(Player player) {
        int playerId = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String selectPlayerId = "select p.playerId from dungeonrun.player p join dungeonrun.playerparty p2 on p.PlayerId = p2.PlayerId where p.PlayerClassId = ? and p2.BelongsToPartyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(selectPlayerId)) {

                statement.setInt(1, player.getId());
                statement.setString(2, player.getPartyId());

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    playerId = resultSet.getInt("PlayerId");
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

        return playerId;
    }


    public void writeSaveFile(List<Player> playerList, String saveSlot) {
        if (checkIfSaveFileNameExists(saveSlot) == 0) {

            try (Connection connection = DatabaseConnector.getConnection()) {
                String writeSaveFile = "INSERT INTO dungeonrun.playerSave (PlayerId, SaveSlotName) VALUES (?,?)";

                try (PreparedStatement statement = connection.prepareStatement(writeSaveFile)) {

                    for (Player player : playerList) {
                        statement.setInt(1, writePlayerId(player));
                        statement.setString(2, saveSlot);
                        statement.executeUpdate();
                    }

                } catch (SQLException e) {
                    DatabaseConnector.handleSQL(e);
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } else System.out.println("File name already exists\n");
    }


    public void updateSaveFile(List<Player> playerList) {

        try (Connection connection = DatabaseConnector.getConnection()) {
            String updateSaveFile = "update dungeonrun.playersave p join dungeonrun.playerparty p2 on p.PlayerId = p2.PlayerId  set p2.Registrationdate = current_timestamp where p.playerId = ? and p2.BelongsToPartyId = ?";

            try (PreparedStatement statement = connection.prepareStatement(updateSaveFile)) {

                for (Player player : playerList) {
                    statement.setInt(1, getPlayerId(player));
                    statement.setString(2, player.getPartyId());
                    statement.executeUpdate();
                }
                System.out.println("Slot successfully saved");
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
    }


    public String getSaveFile() {

        System.out.printf("Enter a name for the save: ");
        String saveSlot;
        do {
            saveSlot = (Input.stringInput()).trim();
            if (saveSlot.isEmpty()) {
                System.out.println("Please enter a save name, dont leave it empty");
            }
        } while (saveSlot.isEmpty());


        return saveSlot;
    }

    public int checkIfSaveFileNameExists(String saveSlot) {
        int checkName = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {

            String checkEmpty = "SELECT count(*) FROM dungeonrun.playersave where SaveSlotName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkEmpty)) {

                statement.setString(1, saveSlot);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    checkName = resultSet.getInt("count(*)");
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }
        return checkName;
    }


    public int checkIfFileSlotExists(List<Player> playerList) {
        int checkSlot = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {

            String checkEmpty = "SELECT count(*) FROM dungeonrun.playersave where PlayerId = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkEmpty)) {
                for (Player player : playerList)
                    statement.setInt(1, getPlayerId(player));

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    checkSlot = resultSet.getInt("count(*)");
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

        return checkSlot;
    }

    public void createNewSaveSlotOrOverride(List<Player> playerList) {


        System.out.println("Update current slot or create new?");
        System.out.println(saveFileChoice());

        if (checkIfFileSlotExists(playerList) == 0) {
            int playerAlternative = Input.intInput();
            switch (playerAlternative) {
                case 1 -> {
                    System.out.println("No slot found");
                }
                case 2 -> {
                    String saveSlot = getSaveFile();
                    writeSaveFile(playerList, saveSlot);
                }
                default -> {
                    System.out.println("Returning to game menu");
                }
            }

        } else {
            int playerAlternative = Input.intInput();
            switch (playerAlternative) {
                case 1 -> {
                    updateSaveFile(playerList);
                }
                case 2 -> {
                    String saveSlot = getSaveFile();
                    writeSaveFile(playerList, saveSlot);
                }
                default -> {
                    System.out.println("Returning to game menu");
                }
            }
        }
    }
}



