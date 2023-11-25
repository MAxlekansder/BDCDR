package com.alexanderhasslund.demo.main.Monster.Boss.TheTwinBrothers;

import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Monster.IMonster;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;
import java.util.Random;

public class Ohrum extends Monster implements IMonster, ICombat {


    public Ohrum() {
        super("\033[1;36mBOSS\033[0m", "THE TWIN BROTHERS - Oh'rum", 80, 0, 10, 0, 60, 0, false, 50,1000 , 0, false, 0);
    }

    @Override
    public void spells(List<Player> playerList, Player player, List<Monster> monsterList, Monster currentMonster) {
        //Twin brother pact

        System.out.println("THE TWIN BROTHERS REALIGNS WITH THE STARS");
        System.out.println("Ohrum heals himself and his brother, building up more HP overtime");
        for (Monster monster : monsterList) {
            monster.setHp(monster.getHp() + 15);
            System.out.println(monster.getMonsterName()+ "Current HP: " + monster.getHp());
        }
    }


    @Override
    public void attack(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {

        Random random = new Random();
        int chanceOfSpell = random.nextInt(4);

        if (chanceOfSpell >= 2) {

            int randPlayer = random.nextInt(playerList.size());
            int calculateDodge = random.nextInt(1, 6) * 10;

            int calculatePlayerDodge = playerList.get(randPlayer).getDefence();
            int dodgeChance = calculateDodge + calculatePlayerDodge;
            int scalingDodgeChance = 50 + (int) Math.round(playerList.get(randPlayer).getLevel() * 1.3);


            if (dodgeChance < scalingDodgeChance) {
                System.out.printf("%s, strikes, dealing: %s damage\n", currentMonster.getMonsterName(), currentMonster.getDamage());
                playerList.get(randPlayer).setHp(playerList.get(randPlayer).getHp()
                        - currentMonster.getDamage());
                System.out.printf("And player: %s has %s HP left \n", playerList.get(randPlayer).getName(), playerList.get(randPlayer).getHp());

            } else {

                System.out.printf("%s misses %s player %s \n", currentMonster.getMonsterName(), playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getName());
            }

        } else {
            spells(playerList, currentPlayer, monsterList, currentMonster);
        }
    }

}


/*   Didn't have time to finish this idea... to bad, could have been the best boss


        Random random = new Random();



        int playerIndex = random.nextInt(playerList.size());
        System.out.println("twin brothers pact - with a synchronised lift, both brothers flies into the air, attacks with a symmetrical attack. Stricking at the same time");

        for (Monster monsterSpecial : monsterList) {
            playerList.get(playerIndex).setHp(playerList.get(playerIndex).getHp() - monster.getDamage() + 2);
            System.out.println(monsterSpecial.getMonsterName() + " Strikes for: " + (monsterSpecial.getDamage() + 2));
        }

        System.out.println("THE TWIN BROTHERS REALIGNS WITH THE STARS");

        for (int i = 0; i < monsterList.size();i++) {
            if (monsterList.get(i).getHp() > monsterList.get(i+1).getHp() && !(monsterList.size() == 1)) {
                monsterList.get(i+1).setHp(monsterList.get(i).getHp());


                System.out.printf(" TWIN PACT, BLOOD SOUL -  %s draws strength from his brother setting HP to %s\n", monsterList.get(i).getMonsterName(), monsterList.get(i).getHp());

            } else if (monsterList.get(i).getHp() < monsterList.get(i+1).getHp() && !(monsterList.size() == 1)) {
                monsterList.get(i).setHp(monsterList.get(i+1).getHp());
                System.out.printf(" TWIN PACT, BLOOD SOUL -  %s draws strength from his brother setting HP to %s\n", monsterList.get(i+1).getMonsterName(), monsterList.get(i+1).getHp());
            } else {
                System.out.println("No alignment could be made");
            }
        }*/