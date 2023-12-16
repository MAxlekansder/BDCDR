package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice.saveFileChoice;

public class DatabasePlayerLoader {


    public void loadingPlayerFromDatabase() {
        System.out.println("What save do you want to load?");
        String query = "\n" +
                "select distinct * from dungeonrun.playersave p join dungeonrun.playeractiveclass p2 on p.PlayerId  = p2.playerId \n" +
                "where p.SaveSlotName ='?' \n" +
                "and p2.isDead = 0";
    }


    public String test(int saveLoad) {
        String saveLoadFile = "";
        String test = "select saveSlotName\n" +
                "FROM (\n" +
                "select DENSE_RANK() OVER (ORDER BY p.RegistrationDate DESC) as countRow , SaveSlotName\n" +
                "from dungeonrun.playersave p \n" +
                "group by SaveSlotName \n" +
                "order by p.RegistrationDate desc\n" +
                "limit 7\n" +
                ") as test1\n" +
                "where countRow = ?";
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(test)) {

                statement.setInt(1,saveLoad);

            } catch (SQLException e) { DatabaseConnector.handleSQL(e); }
        } catch (SQLException e) {DatabaseConnector.handleSQL(e);}



        return saveLoadFile;
    }


    public void chooseRunToLoad() {
       int saveLoad = 0;
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

                System.out.print("Choose a slot to load: ");
                 saveLoad = Input.intInput();

                test(saveLoad);
            }  catch(SQLException e){DatabaseConnector.handleSQL(e);}
        } catch (SQLException e) {DatabaseConnector.handleSQL(e);}


    }

    public List<Player> setUpPlayerListFromSave() {
        List<Player> playerList = new ArrayList<>();

        try (Connection connection =  DatabaseConnector.getConnection()) {
            String loadPlayer = "SELECT * FROM PLAYER";
            try (PreparedStatement statement = connection.prepareStatement(loadPlayer)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        //set values for player here...
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

