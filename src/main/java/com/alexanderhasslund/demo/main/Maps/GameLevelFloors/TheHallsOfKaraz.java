package com.alexanderhasslund.demo.main.Maps.GameLevelFloors;
import com.alexanderhasslund.demo.main.Combat.CombatController.CombatController;
import com.alexanderhasslund.demo.main.Combat.CombatMenu;
import com.alexanderhasslund.demo.main.Engine.StringManipulator;
import com.alexanderhasslund.demo.main.Monster.MonsterController;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.StringLore;

import java.util.List;

public class TheHallsOfKaraz  {

    private int calculateLevels;
    private List<Player> playerList;
    private int countPlayers;



    public TheHallsOfKaraz(int calculateLevels, List<Player> playerList, int countPlayers) {

        this.calculateLevels = calculateLevels;
        this.playerList = playerList;
        this.countPlayers = countPlayers;
    }


    public boolean startUpGameOne() {
        MonsterController monsterController = new MonsterController(countPlayers);
        CombatController combatController = new CombatController(playerList, monsterController.getMonsterList());
        StringManipulator stringManipulator = new StringManipulator();
        CombatMenu combatMenu = new CombatMenu();
        monsterController.monsterValueController();
        boolean checkPlayerEncounter = false;


        if (combatController.initiateFight()) {
            stringManipulator.manipulateString(StringLore.bossTagTeam());

                monsterController.chooseBossFight(calculateLevels);
                checkPlayerEncounter = combatController.initiateFight();

        } else System.out.println("As you run out, you still here the screeches of a horde, maybe another day...");


       return checkPlayerEncounter;

    }

}
