package com.alexanderhasslund.demo.main.Maps.GameLevelFloors;

import com.alexanderhasslund.demo.main.Combat.CombatController.CombatController;
import com.alexanderhasslund.demo.main.Engine.StringManipulator;
import com.alexanderhasslund.demo.main.Monster.MonsterController;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;
import com.alexanderhasslund.demo.main.PlayerInteraction.StringLore;

import java.util.List;

public class UpperPlateau {
    private int calculateLevels;
    private List<Player> playerList;
    private int countPlayers;


    public UpperPlateau(int calculateLevels, List<Player> playerList, int countPlayers) {

        this.calculateLevels = calculateLevels;
        this.playerList = playerList;
        this.countPlayers = countPlayers;
    }

    public boolean startUpGameTwo() {
        MonsterController monsterController = new MonsterController(countPlayers);
        CombatController combatController = new CombatController(playerList, monsterController.getMonsterList());
        StringManipulator stringManipulator = new StringManipulator();
        monsterController.monsterValueController();

        stringManipulator.manipulateString(StringLore.reachingUpperPlateau());
        boolean checkPlayerEncounter = false;

        if (combatController.initiateFight()) {
            stringManipulator.manipulateString(StringLore.bossTwinBrothers());

            monsterController.chooseBossFight(calculateLevels);
            checkPlayerEncounter = combatController.initiateFight();
        } else System.out.println("As you run out, a ringing 'OUHM' deafening you, maybe another day...");

        return checkPlayerEncounter;

    }
}
