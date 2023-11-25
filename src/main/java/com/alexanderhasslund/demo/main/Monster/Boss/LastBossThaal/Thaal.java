package com.alexanderhasslund.demo.main.Monster.Boss.LastBossThaal;

import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Monster.IMonster;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;
import java.util.Random;

public class Thaal extends Monster implements IMonster, ICombat {


    public Thaal() {
        super("\033[1;36mFINAL BOSS\033[0m","THE GRAND EMPEROR - Thaal",300,0,15,0,0,0,false,200,2000,0,false,0);
    }

    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {
        System.out.println("GRASP AND CONSUME - LIFE DRAIN, Thaal grabs every champion and damages everyone, healing himself " );
        for (Player player : playerList) {
            player.setHp(player.getHp() - (currentMonster.getDamage() - (player.getDefence() /10)));

        }
        if (currentMonster.getHp() < 300) {
            currentMonster.setHp(currentMonster.getHp() + 20);
        }
    }

    @Override
    public void attack(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {
        Random random = new Random();
        int chanceOfSpell = random.nextInt(10);

        if (chanceOfSpell <= 5) {

            int randPlayer = random.nextInt(playerList.size());
            int calculateDodge = random.nextInt(1, 6) * 10;

            int calculatePlayerDodge = playerList.get(randPlayer).getDefence();
            int dodgeChance = calculateDodge + calculatePlayerDodge;
            int scalingDodgeChance = 50 + (int) Math.round(playerList.get(randPlayer).getLevel() * 1.3);


            if (dodgeChance < scalingDodgeChance) {
                System.out.println("Thaal, strikes with a mighty blow, dealing: " + currentMonster.getDamage() + " damage");
                playerList.get(randPlayer).setHp(playerList.get(randPlayer).getHp()
                        - (currentMonster.getDamage() - (int) (playerList.get(randPlayer).getDefence() / 10)));
                System.out.printf("And player: %s has %s HP left \n", playerList.get(randPlayer).getName(), playerList.get(randPlayer).getHp());

            } else {

                System.out.printf("Thaal misses %s player %s \n", playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getName());
            }

        } else {
            spells(playerList, currentPlayer, monsterList, currentMonster);
        }
    }
}
