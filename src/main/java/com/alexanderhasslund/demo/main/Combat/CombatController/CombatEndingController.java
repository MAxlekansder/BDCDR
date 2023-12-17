package com.alexanderhasslund.demo.main.Combat.CombatController;

import com.alexanderhasslund.demo.main.Combat.ResetCombat;
import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabasePlayerWriter;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public class CombatEndingController {

    private static int calculateLevels;

    public void decideCombatWinner(List<Player> playerList, List<Monster> monsterList) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
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

            for (Monster monster : monsterList) {
                if (monster.getTypeName().equals("\033[1;36mBOSS\033[0m") || monster.getTypeName().equals("\033[1;36mFINAL BOSS\033[0m")) {
                    databasePlayerWriter.insertPlayerClearedLevel(playerList, monsterList, calculateLevels);
                }
            }
        }
    }


    public int getCalculateLevels() {
        return calculateLevels;
    }

    public void setCalculateLevels(int calculateLevels) {
        this.calculateLevels = calculateLevels;
    }
}


