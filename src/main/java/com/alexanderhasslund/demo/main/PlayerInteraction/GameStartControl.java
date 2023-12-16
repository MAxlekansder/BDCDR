package com.alexanderhasslund.demo.main.PlayerInteraction;

import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Combat.FirstCombatEncounter;
import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.*;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Engine.StringManipulator;
import com.alexanderhasslund.demo.main.File.SaveFile;
import com.alexanderhasslund.demo.main.Player.PlayerController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GameStartControl {


    public void startIntroductionGame() {
        StringManipulator stringManipulator = new StringManipulator();
        DatabaseMonsterWriter databaseMonsterWriter = new DatabaseMonsterWriter();
        DatabaseClassWriter databaseClassWriter = new DatabaseClassWriter();
        DatabaseMapWritter databaseMapWritter = new DatabaseMapWritter();
        StringLore stringLore = new StringLore();


        //stringManipulator.manipulateString(stringLore.intro());
        stringLore.gameTitle();
        //System.out.println(stringLore.gameIntroductionClasses());

        Barbarian barbarian = new Barbarian();
        System.out.println(barbarian);
        Rogue rogue = new Rogue();
        System.out.println(rogue);
        Sorcerer sorcerer = new Sorcerer();
        System.out.println(sorcerer);
        databaseMonsterWriter.writingBoss();
        databaseMonsterWriter.writingBasicMonster();
        databaseClassWriter.getClassInformation();
        databaseMapWritter.namingMap();

        System.out.println(stringLore.ultimateClassPresentation());
        //stringManipulator.manipulateString(stringLore.gameIntroductionRules());
        System.out.println("Press enter to continue, where you start the game and choose class");

        String enter = Input.stringInput();
        startUpMenu();
    }


    public void startUpMenu() {
        PlayerChoice playerChoice = new PlayerChoice();
        DatabaseGetter databaseGetter = new DatabaseGetter();
        DatabasePlayerLoader databasePlayerLoader = new DatabasePlayerLoader();
        SaveFile saveFile = new SaveFile();
        boolean isPlaying = true;


        // introduce same logic here as in playerChoice with blacking out continue save...
        do {
            playerChoice.startMenuChoice();
            switch (Input.intInput()) {
                case 1 -> {

                    startIntroductionChoice();

                }
                case 2 -> {

                    if (databaseGetter.checkIfPlayersExists() > 0) {
                        databasePlayerLoader.chooseRunToLoad();
                        databasePlayerLoader.setUpPlayerListFromSave();

                    } else {
                        System.out.println("No file found");
                    }
                }
                case 3 -> {

                    databaseGetter.displayingPlayersFromDatabase();
                }
                case 4 -> {
                    isPlaying = false;
                }
            }
        } while (isPlaying);
    }



    public void startIntroductionChoice() {
        PlayerController playerController = new PlayerController();
        StringManipulator stringManipulator = new StringManipulator();
        PlayerChoice playerChoice = new PlayerChoice();

        playerController.playerCount();
        FirstCombatEncounter firstCombatEncounter = new FirstCombatEncounter(playerController.getPlayerList(), playerController.getCountPlayers());
        playerController.chooseClass();
        playerController.choosePlayerName();
        playerController.getPlayerInformation();
        MainGameControl mainGameControl = new MainGameControl(playerController.getPlayerList(), playerController.getCountPlayers());


        //stringManipulator.manipulateString(playerChoice.firstFightIntroduction());
        firstCombatEncounter.firstPlayerFight();
        mainGameControl.mainSwitch();
    }

}

