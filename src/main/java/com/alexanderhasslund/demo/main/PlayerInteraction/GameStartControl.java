package com.alexanderhasslund.demo.main.PlayerInteraction;

import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Combat.FirstCombatEncounter;
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
        StringLore stringLore = new StringLore();


        stringManipulator.manipulateString(stringLore.intro());
        stringLore.gameTitle();
        System.out.println(stringLore.gameIntroductionClasses());

        Barbarian barbarian = new Barbarian();
        System.out.println(barbarian);
        Rogue rogue = new Rogue();
        System.out.println(rogue);
        Sorcerer sorcerer = new Sorcerer();
        System.out.println(sorcerer);

        System.out.println(stringLore.ultimateClassPresentation());
        stringManipulator.manipulateString(stringLore.gameIntroductionRules());
        System.out.println("Press enter to continue, where you start the game and choose class");

        String enter = Input.stringInput();
        startUpMenu();
    }


    public void startUpMenu() {
        PlayerChoice playerChoice = new PlayerChoice();
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
                    Path path = Paths.get("Gamesave.txt");

                    if (Files.exists(path)) {

                        MainGameControl mainGameControl = new MainGameControl(saveFile.loadFilePlayer(), saveFile.loadFilePlayer().size());
                        mainGameControl.mainSwitch();
                    } else {

                        System.out.println("No file found");

                    }

                }
                case 3 -> {
                    System.out.println("Feature comming soon!");
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


        stringManipulator.manipulateString(playerChoice.firstFightIntroduction());
        firstCombatEncounter.firstPlayerFight();
        mainGameControl.mainSwitch();
    }

}

