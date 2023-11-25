package com.alexanderhasslund.demo.main.PlayerInteraction;

import com.alexanderhasslund.demo.main.Combat.CombatMenu;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.Maps.GameLevelOptions.GameLevelFour;
import com.alexanderhasslund.demo.main.Maps.GameLevelOptions.GameLevelOne;
import com.alexanderhasslund.demo.main.Maps.GameLevelOptions.GameLevelThree;
import com.alexanderhasslund.demo.main.Maps.GameLevelOptions.GameLevelTwo;

import java.util.List;

public class GameLevelMenu {

    private int calculateLevels;
    private List<Player> playerList;
    private int countPlayers;


    public GameLevelMenu(int calculateLevels, List<Player> playerList, int countPlayers) {
        this.playerList = playerList;
        this.countPlayers = countPlayers;
        this.calculateLevels = calculateLevels;
    }

    public boolean gameViewSwitch() {
        GameLevelOne gameLevelOne = new GameLevelOne(calculateLevels, playerList, countPlayers);
        GameLevelTwo gameLevelTwo = new GameLevelTwo(calculateLevels, playerList, countPlayers);
        GameLevelThree gameLevelThree = new GameLevelThree(calculateLevels, playerList, countPlayers);
        GameLevelFour gameLevelFour = new GameLevelFour(calculateLevels, playerList, countPlayers);

        boolean isGameLevel = true;
        boolean isPlayerEncounter = true;

        while (isGameLevel) {
            switch (calculateLevels) {
                case 1 -> {
                    isPlayerEncounter = gameLevelOne.gameSwitchOne();
                    if (isPlayerEncounter) {
                        calculateLevels++;
                        isGameLevel = false;
                    }
                    isGameLevel = false;

                }
                case 2 -> {
                    isPlayerEncounter = gameLevelTwo.gameSwitchTwo();
                    if (isPlayerEncounter) {
                        calculateLevels++;
                        isGameLevel = false;
                    }
                    isGameLevel = false;
                }
                case 3 -> {
                    isPlayerEncounter = gameLevelThree.gameSwitchThree();
                    if (isPlayerEncounter) {
                        calculateLevels++;
                        isGameLevel = false;
                    }
                    isGameLevel = false;
                }
                case 4 -> {
                    gameLevelFour.gameSwitchFour();
                    if (isPlayerEncounter) {
                        calculateLevels++;
                        isGameLevel = false;
                    }
                    isGameLevel = false;
                }
                case 5 -> {
                    isGameLevel = false;
                }
                default -> {
                    System.out.println("Use the right input");
                }
            }
        }
        return isPlayerEncounter;
    }

}
