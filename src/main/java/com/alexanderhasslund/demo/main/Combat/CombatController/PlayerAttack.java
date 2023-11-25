package com.alexanderhasslund.demo.main.Combat.CombatController;

import com.alexanderhasslund.demo.main.Classes.IClasses;
import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.util.Collections;
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

    public void fightMonster(List<Player> playerList, List<Monster> monsterList, Player currentPlayer, Monster currentMonster) {
        PlayerChoice playerChoice = new PlayerChoice();
        playerChoice.abilityChoice();

        int fightSequence = Input.intInput();
        switch (fightSequence) {

            case 1 -> {
                IntStream.range(0, playerList.size()).filter(index -> index == currentPlayer.getId()).forEach(index -> {  //filter(index -> index == playerIndex)
                            if (currentPlayer instanceof ICombat) {
                                ((ICombat) currentPlayer).attack(playerList, currentPlayer, monsterList, currentMonster);
                            }
                        }
                );
                currentPlayer.setHasPlayed(true);
                checkMonsterhasDied(monsterList, playerList, currentPlayer);
            }

            case 2 -> {
                IntStream.range(0, playerList.size()).filter(index -> index == currentPlayer.getId()).forEach(index -> {  //filter(index -> index == playerIndex)
                    if (currentPlayer instanceof ICombat) {
                        ((IClasses) currentPlayer).spells(playerList, currentPlayer, monsterList);
                    }
                });
                currentPlayer.setHasPlayed(true);
                checkMonsterhasDied(monsterList, playerList, currentPlayer);
            }
            case 3 -> {
                IntStream.range(0, playerList.size()).filter(index -> index == currentPlayer.getId()).forEach(index -> {  //filter(index -> index == playerIndex)
                    if (currentPlayer instanceof ICombat) {
                        ((IClasses) currentPlayer).ultimate(playerList, currentPlayer, monsterList);

                        currentPlayer.setHasPlayed(true);
                        checkMonsterhasDied(monsterList, playerList, currentPlayer);

                    }
                });
            }
            case 4 -> {
                System.out.println("back to menu");
            }
        }
    }


    public void checkMonsterhasDied(List<Monster> monsterList, List<Player> playerList, Player currentPlayer) {


        //for (int i = 0; i < monsterList.size(); i++) {
        for (int i = monsterList.size() -1; i >=0; i--) {
            countDeadMonsters++;
            if (monsterList.get(i).getHp() <= 0) {

                System.out.printf("\nMonster %s died!\n", monsterList.get(i).getMonsterName());


                for (int j = 0; j < playerList.size(); j++) {

                    playerList.get(j).setCurrency(playerList.get(j).getCurrency() + monsterList.get(i).getGivesCurrency());
                    playerList.get(j).setExperience( playerList.get(j).getExperience() +monsterList.get(i).getGivesExperience());
                }
                monsterList.remove(monsterList.get(i));
            }

        }

        playerList.forEach( player -> ((IClasses) currentPlayer).setLevelUp(player)); //filter(index -> index == playerIndex)

        //((IClasses) currentPlayer).setLevelUp(currentPlayer);

    }

}


