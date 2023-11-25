package com.alexanderhasslund.demo.main.Monster.Boss.TagTeam;

import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Monster.IMonster;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;
import java.util.Random;

public class TagTeam extends Monster implements IMonster, ICombat {



    public TagTeam() {
        super("\033[1;36mBOSS\033[0m","THE VERMIN TAG TEAM: Skarrik, Rasknitt and Deathrattler", 90,0,12,40,25, 0, false, 50,500,0,false,0);
    }


    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {
        Random random = new Random();
      // tag team frenzy
        // either they always attack as 3 or this spell would be doing the tag team damage and have a multiplier?
        int randPlayer = random.nextInt(playerList.size());
        int randDamage = random.nextInt(1,3) * 5;

        System.out.println("Skarrik, Rasknitt and deathrattler cordinates a flanking attack, all three attacks at once - tag team frenzy");
        System.out.println("Doing massive damage towards " + playerList.get(randPlayer).getClassName() + " " + playerList.get(randPlayer).getName());
        for (int i = 0; i < 3; i++) {
            playerList.get(randPlayer).setHp(playerList.get(randPlayer).getHp() - randDamage);
            System.out.println("Damage: " + randDamage);
        }
    }


    @Override
    public void attack(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster currentMonster) {
        Random random = new Random();
        int chanceOfSpell = random.nextInt(10);

        if (chanceOfSpell < 2) {

            int randPlayer = random.nextInt(playerList.size());
            int calculateDodge = random.nextInt(1, 6) * 10;

            int calculatePlayerDodge = playerList.get(randPlayer).getDefence();
            int dodgeChance = calculateDodge + calculatePlayerDodge;
            int scalingDodgeChance = 50 + (int) Math.round(playerList.get(randPlayer).getLevel() * 1.3);


            if (dodgeChance < scalingDodgeChance) {
                System.out.println("The Tag team, strikes one by one, dealing: " + currentMonster.getDamage() + " damage");
                playerList.get(randPlayer).setHp(playerList.get(randPlayer).getHp()
                        - (currentMonster.getDamage() - (int) (playerList.get(randPlayer).getDefence() / 8)));
                System.out.printf("And player: %s has %s HP left \n", playerList.get(randPlayer).getName(), playerList.get(randPlayer).getHp());

            } else {

                System.out.printf("The Tag team misses %s player %s \n", playerList.get(randPlayer).getClassName(), playerList.get(randPlayer).getName());
            }

        } else {
            spells(playerList, currentPlayer, monsterList, currentMonster);
        }

    }



}
