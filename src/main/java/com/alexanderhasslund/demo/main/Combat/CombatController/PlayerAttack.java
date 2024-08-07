package com.alexanderhasslund.demo.main.Combat.CombatController;

import com.alexanderhasslund.demo.main.Classes.IClasses;
import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabaseMapWritter;
import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabasePlayerLoader;
import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabasePlayerWriter;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.util.List;
import java.util.stream.IntStream;

public class PlayerAttack {
    private int countDeadMonsters; // no usages else where - ignored

    public int getCountDeadMonsters() {
        return countDeadMonsters;
    }

    public void setCountDeadMonsters(int countDeadMonsters) {
        this.countDeadMonsters = countDeadMonsters;
    }

    private List<Player> playerList;

    public void fightMonster(List<Player> playerList, List<Monster> monsterList, Player currentPlayer, Monster currentMonster, int calculateLevel, int countRounds, String battleId) {
        PlayerChoice playerChoice = new PlayerChoice();
        playerChoice.abilityChoice();

        int fightSequence = Input.intInput();
        switch (fightSequence) {

            case 1 -> {
                IntStream.range(0, playerList.size()).filter(index -> index == currentPlayer.getId()).forEach(index -> {  //filter(index -> index == playerIndex)
                            if (currentPlayer instanceof ICombat) {
                                ((ICombat) currentPlayer).attack(playerList, currentPlayer, monsterList, currentMonster, calculateLevel, countRounds, battleId);
                            }
                        }
                );
                currentPlayer.setHasPlayed(true);
                checkMonsterhasDied(monsterList, playerList, currentPlayer, calculateLevel);
            }

            case 2 -> {
                IntStream.range(0, playerList.size()).filter(index -> index == currentPlayer.getId()).forEach(index -> {  //filter(index -> index == playerIndex)
                    if (currentPlayer instanceof ICombat) {
                        ((IClasses) currentPlayer).spells(playerList, currentPlayer, monsterList, calculateLevel, countRounds, battleId);
                    }
                });
                currentPlayer.setHasPlayed(true);
                checkMonsterhasDied(monsterList, playerList, currentPlayer, calculateLevel);
            }
            case 3 -> {
                IntStream.range(0, playerList.size()).filter(index -> index == currentPlayer.getId()).forEach(index -> {  //filter(index -> index == playerIndex)
                    if (currentPlayer instanceof ICombat) {
                        ((IClasses) currentPlayer).ultimate(playerList, currentPlayer, monsterList, calculateLevel, countRounds, battleId);

                        currentPlayer.setHasPlayed(true);
                        checkMonsterhasDied(monsterList, playerList, currentPlayer, calculateLevel);

                    }
                });
            }
            case 4 -> {
                System.out.println("back to menu");
            }
        }
    }


    public void checkMonsterhasDied(List<Monster> monsterList, List<Player> playerList, Player currentPlayer, int calculateLevels) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        DatabaseMapWritter databaseMapWritter = new DatabaseMapWritter();
        int currency = 0;
        Monster monster = monsterList.get(0);


        if (monster.getTypeName().equals("\033[1;36mBOSS\033[0m") && monster.getHp() < 0 || monster.getTypeName().equals("\033[1;36mFINAL BOSS\033[0m") && monster.getHp() < 0) {
            databasePlayerWriter.insertPlayerClearedLevel(playerList, monster, databaseMapWritter.getMapLevel(playerList) + 1);
        }


        for (int i = monsterList.size() - 1; i >= 0; i--) {


            countDeadMonsters++;
            if (monsterList.get(i).getHp() <= 0) {

                System.out.printf("\nMonster %s died!\n", monsterList.get(i).getMonsterName());


                for (int j = 0; j < playerList.size(); j++) {

                    playerList.get(j).setCurrency(playerList.get(j).getCurrency() + monsterList.get(i).getGivesCurrency());
                    playerList.get(j).setExperience(playerList.get(j).getExperience() + monsterList.get(i).getGivesExperience());
                }
                databasePlayerWriter.updateMonsterKilledByPlayer(currentPlayer, 1);
                databasePlayerWriter.updatePlayerExperience(playerList);
                databasePlayerWriter.updatePlayerCurrency(playerList);


                monsterList.remove(monsterList.get(i));

            }

        }


        playerList.forEach(player -> ((IClasses) currentPlayer).setLevelUp(player)); databasePlayerWriter.updatePlayerLevelDatabase(playerList); //filter(index -> index == playerIndex)


    }

}


