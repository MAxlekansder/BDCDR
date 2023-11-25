package com.alexanderhasslund.demo.main.Monster.Boss.theInquisition;

import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Monster.IMonster;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;
import java.util.Random;

public class theInquisition extends Monster implements IMonster, ICombat {


    public theInquisition() {
        super("\033[1;36mBOSS\033[0m","The inqusition", 250, 0, 6,0,0,0,false,200,1500,0,false,0);
    }



    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster monster) {

        Random random = new Random();
        int randomPlayer = random.nextInt(playerList.size());

        System.out.printf("Banishes the player to the abyss and dealing 10 damage, setting %s %s initiative last!\n", playerList.get(randomPlayer).getClassName(), playerList.get(randomPlayer).getName());
        playerList.get(randomPlayer).setInitiative(99);
        playerList.get(randomPlayer).setHp(playerList.get(randomPlayer).getHp() - 20);
        System.out.println("Oh... what a shame... ");
    }



    @Override
    public void attack(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {

        Random random = new Random();
        int chanceOfSpell = random.nextInt(15);

        if (chanceOfSpell < 2) {


            int randPlayer = random.nextInt(playerList.size());
            int calculateDodge = random.nextInt(1, 6) * 10;


            int calculatePlayerDodge = playerList.get(randPlayer).getDefence();
            int dodgeChance = calculateDodge + calculatePlayerDodge;
            int scalingDodgeChance = 50 + (int) Math.round(playerList.get(randPlayer).getLevel() * 1.3);



            if (dodgeChance < scalingDodgeChance) {
                System.out.println("The Inquisition, strikes one by one, dealing: " + currentMonster.getDamage() + " damage");
                for (int i = 0; i < 6; i++) {
                    playerList.get(randPlayer).setHp(playerList.get(randPlayer).getHp()
                            - currentMonster.getDamage());
                }

                System.out.printf("And player: %s has %s HP left \n", playerList.get(randPlayer).getName(), playerList.get(randPlayer).getHp());

            } else {

                System.out.printf("The Inquisition misses %s player %s \n", playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getName());
            }

        } else {
            spells(playerList, currentPlayer, monsterList, currentMonster);
        }
    }
}
