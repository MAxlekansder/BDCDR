package com.alexanderhasslund.demo.main.Combat;

import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public class ResetCombat {

    public void resetPlayerListBackToNormal(List<Player> playerList) {
        Barbarian barbarian = new Barbarian();
        Sorcerer sorcerer = new Sorcerer();
        Rogue rogue = new Rogue();

        for (Player player : playerList) {
            if (player instanceof Barbarian) {
                player.setResource(barbarian.getMaxResource());
                player.setAgility(player.getBaseAgility());
                player.setStrength(player.getBaseStrength());
                player.setIntellect(player.getIntellect());
                player.setBaseDamage(player.getBaseDamage());
                player.setInitiative(barbarian.getInitiative());
                player.setHp(player.getMaxHp());
                player.setHasPlayed(false);

            } else if (player instanceof Rogue) {
                player.setResource(rogue.getMaxResource());
                player.setAgility(player.getBaseAgility());
                player.setStrength(player.getBaseStrength());
                player.setIntellect(player.getIntellect());
                player.setBaseDamage(player.getBaseDamage());
                player.setInitiative(rogue.getInitiative());
                player.setHp(player.getMaxHp());
                player.setHasPlayed(false);

            } else if (player instanceof Sorcerer) {
                player.setResource(player.getMaxResource());
                player.setAgility(player.getBaseAgility());
                player.setStrength(player.getBaseStrength());
                player.setIntellect(player.getIntellect());
                player.setBaseDamage(player.getBaseDamage());
                player.setInitiative(sorcerer.getInitiative());
                player.setHp(player.getMaxHp());
                player.setHasPlayed(false);
            } else {
                System.out.println("No class found... ");
            }
        }
    }

}

