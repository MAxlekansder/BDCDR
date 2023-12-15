package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice.saveFileChoice;

public class DatabasePlayerLoader {


    public void loadingPlayerFromDatabase() {
        System.out.println("What save do you want to load?");
        int playerLoad = 0;
        boolean isChoice = true;
        while (isChoice) {
            switch (playerLoad) {

            }
        }
    }




    public int chooseRunToLoad() {
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

                test();

            }  catch(SQLException e){DatabaseConnector.handleSQL(e);}
        } catch (SQLException e) {DatabaseConnector.handleSQL(e);}
        return 0;

    }

    public int test() {

        System.out.print("Choose a slot to load: ");
        int saveLoad = Input.intInput();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String selectPlayer = "select countRow, saveSlotName\n" +
                    "FROM (\n" +
                    "select DENSE_RANK() OVER (ORDER BY p.RegistrationDate DESC) as countRow , SaveSlotName \n" +
                    "from dungeonrun.playersave p \n" +
                    "group by SaveSlotName \n" +
                    "order by p.RegistrationDate desc\n" +
                    "limit 7\n" +
                    ") as test1\n" +
                    "where countRow = ?";

            try (PreparedStatement statement = connection.prepareStatement(selectPlayer)) {

                statement.setInt(1,saveLoad);
                statement.executeQuery();

            }  catch(SQLException e){DatabaseConnector.handleSQL(e);}
        } catch (SQLException e) {DatabaseConnector.handleSQL(e);}

        return saveLoad;
    }


}

 