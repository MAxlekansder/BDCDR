package com.alexanderhasslund.demo.main.File;

import com.alexanderhasslund.demo.main.Player.Player;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveFile {
    private static int countFile = 1;

    public void saveFilePlayer(List<Player> playerList) {


        try {
            String saveName = "Gamesave.txt";
            FileOutputStream fos = new FileOutputStream(saveName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(playerList);

            fos.close();
            countFile++;
            //saveFiles.add(saveName);
            System.out.println("successfully saved\n");

        } catch (Exception e) {
            System.out.println("Couldn't save file");
        }

    }

    public List<Player> loadFilePlayer() {
        List<Player> loadedPlayerList = new ArrayList<>();

        try {
            FileInputStream fin = new FileInputStream("Gamesave.txt");
            ObjectInputStream objIn = new ObjectInputStream(fin);
            loadedPlayerList = (List<Player>) objIn.readObject();
        } catch (Exception e) {
            System.out.println("File not loaded correctly");
        }
        return loadedPlayerList;
    }


    public void saveHistoricPlayer(List<Player> playerList) {


        try {
            //String dateNow = String.valueOf(LocalDateTime.now());

            String firstLine = "Le grand Finale ";
            String middelDate = LocalDate.now().toString();
            String suffix = ".txt";


            String saveName = firstLine.concat(middelDate).concat(suffix);
            FileOutputStream fos = new FileOutputStream(saveName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(playerList);

            fos.close();
            System.out.println("successfully saved\n");

        } catch (Exception e) {
            System.out.println("Couldn't save file");
        }
    }
}
