package com.alexanderhasslund.demo.main.Combat.CombatController;

import com.alexanderhasslund.demo.main.Combat.ResetCombat;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public class CombatEndingController {

    private static int calculateLevels;

    public void decideCombatWinner(List<Player> playerList, List<Monster> monsterList) {
        CombatController combatController = new CombatController(playerList, monsterList);
        ResetCombat resetCombat = new ResetCombat();

        if (playerList.isEmpty()) {
            System.out.println("Seems like you didnt make it further than here... ");
            System.out.println("too bad... better luck next time, hero");


        }
        else {
            System.out.println("You made it through the level!");

            calculateLevels++;
            System.out.println("Restoring health and resource back to full");
            resetCombat.resetPlayerListBackToNormal(playerList);
        }
    }


    public int getCalculateLevels() {
        return calculateLevels;
    }

    public void setCalculateLevels(int calculateLevels) {
        this.calculateLevels = calculateLevels;
    }
}
