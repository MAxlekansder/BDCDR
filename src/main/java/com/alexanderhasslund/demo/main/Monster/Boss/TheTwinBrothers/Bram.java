package com.alexanderhasslund.demo.main.Monster.Boss.TheTwinBrothers;

import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Monster.IMonster;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;
import java.util.Random;

public class Bram extends Monster implements IMonster, ICombat {

    public Bram() {
        super("\033[1;36mBOSS\033[0m","THE TWIN BROTHERS - Bram",80,0,10,0,40,0,false,50,1000,0,false,0);
    }

    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {

        System.out.println("Bram strengthens him self and his brother, granting staggering damage");
        for (Monster monster : monsterList) {
            monster.setDamage(monster.getDamage() + 5);
            System.out.println(monster.getMonsterName()+ " Current damage: " + monster.getDamage());
        }

    }

    @Override
    public void attack(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {
        Random random = new Random();
        int chanceOfSpell = random.nextInt(8);

        if (chanceOfSpell > 2) {

            int randPlayer = random.nextInt(playerList.size());
            int calculateDodge = random.nextInt(1, 6) * 10;

            int calculatePlayerDodge = playerList.get(randPlayer).getDefence();
            int dodgeChance = calculateDodge + calculatePlayerDodge;
            int scalingDodgeChance = 50 + (int) Math.round(playerList.get(randPlayer).getLevel() * 1.3);


            if (dodgeChance < scalingDodgeChance) {
                System.out.print("THE TWIN BROTHERS - Bram, strikes with its halberd, dealing: " + currentMonster.getDamage() + " damage\n");
                playerList.get(randPlayer).setHp(playerList.get(randPlayer).getHp()
                        - currentMonster.getDamage());
                System.out.printf("And player: %s %s has %s HP left \n", playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getName(), playerList.get(randPlayer).getHp());

            } else {

                System.out.printf("Bram misses %s player %s \n", playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getName());
            }

        } else {
            spells(playerList, currentPlayer, monsterList, currentMonster);
        }
    }


}
