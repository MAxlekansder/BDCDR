package com.alexanderhasslund.demo.main.Monster.BasicMonsters;

import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.IMonster;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;
import java.util.Random;


public class MonsterBrute extends Monster implements IMonster, ICombat {



    public MonsterBrute() {
        super("\033[1;36mMONSTER\033[0m","VERMIN OGRE", 50,0,8,20,13,0,false, 60,70,20,false,0);
    }

    @Override
    public void spells(List<Player> playerList, Player player, List<Monster> monsterList, Monster monster) {
        //slam
    }

    @Override
    public void attack(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {
         Random random = new Random();

        int randPlayer = random.nextInt(playerList.size());
        int calculateDodge = random.nextInt(1, 6) * 10;

        int calculatePlayerDodge = playerList.get(randPlayer).getDefence();
        int dodgeChance = calculateDodge + calculatePlayerDodge;
        int scalingDodgeChance = 60 + (int) Math.round(playerList.get(randPlayer).getLevel() *  1.3);


        if (dodgeChance < scalingDodgeChance) {
            System.out.println("The brute ⚔\uFE0Ehits⚔\uFE0E for: " + currentMonster.getDamage() + " damage");

            playerList.get(randPlayer).setHp(playerList.get(randPlayer).getHp()
                    - currentMonster.getDamage());

            System.out.printf("And player: %s %s has %s HP left \n", playerList.get(randPlayer).getName(), playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getHp());
        } else {
            System.out.printf("The brute misses %s player %s \n", playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getName());
        }
    }



}

