package com.alexanderhasslund.demo.main.Maps.GameLevelFloors;

import com.alexanderhasslund.demo.main.Combat.CombatController.CombatController;
import com.alexanderhasslund.demo.main.Engine.StringManipulator;
import com.alexanderhasslund.demo.main.Monster.MonsterController;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.StringLore;

import java.util.List;

public class CityMarkazh {

    private int calculateLevels;
    private List<Player> playerList;
    private int countPlayers;


    public CityMarkazh(int calculateLevels, List<Player> playerList, int countPlayers) {

        this.calculateLevels = calculateLevels;
        this.playerList = playerList;
        this.countPlayers = countPlayers;
    }

    public boolean startUpGameThree() {
        MonsterController monsterController = new MonsterController(countPlayers);
        CombatController combatController = new CombatController(playerList, monsterController.getMonsterList());
        StringManipulator stringManipulator = new StringManipulator();
        monsterController.monsterValueController();

        stringManipulator.manipulateString(StringLore.theInnerCity());
        boolean checkPlayerEncounter = false;

        if (combatController.initiateFight()) {
            stringManipulator.manipulateString(StringLore.bossTheInquisition());

            monsterController.chooseBossFight(calculateLevels);
            checkPlayerEncounter = combatController.initiateFight();
            stringManipulator.manipulateString(StringLore.theFinalRoomThaal());
        } else System.out.println("As the inquisition have watch you the entire time, in unison you hear the most chilling laugh, maybe another day...");



        return checkPlayerEncounter;
    }
}

