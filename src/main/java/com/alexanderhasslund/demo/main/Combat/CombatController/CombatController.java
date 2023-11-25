package com.alexanderhasslund.demo.main.Combat.CombatController;
import com.alexanderhasslund.demo.main.Combat.CombatMenu;
import com.alexanderhasslund.demo.main.Combat.ResetCombat;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CombatController {

    private List<Player> playerList;
    private List<Monster> monsterList;

    public CombatController(List<Player> playerList, List<Monster> monsterList) {
        this.playerList = playerList;
        this.monsterList = monsterList;
    }


    public boolean initiateFight() {
        CombatMenu combatMenu = new CombatMenu();
        MonsterAttack monsterAttack = new MonsterAttack();
        boolean isGameOver = false;


        System.out.println("Enter to start combat ");
        String enter = Input.stringInput();
        int countRounds = 0;

        while (!(playerList.isEmpty() || monsterList.isEmpty())) {
            Collections.sort(playerList, new PlayerInitiativeComperator());
            Collections.sort(monsterList, new MonsterInitiativeComperator());
            checkCombatSorted(playerList, monsterList);

            do {
                Collections.sort(playerList, new PlayerInitiativeComperator());
                Collections.sort(monsterList, new MonsterInitiativeComperator());


                if (playerList.isEmpty() || monsterList.isEmpty()) {
                    break;

                } else {

                    Player currentPlayer = playerList.stream().filter(player -> !player.isHasPlayed()).findFirst().orElse(null);
                    Monster currentMonster = monsterList.stream().filter(monster -> !monster.isHasPlayed()).findFirst().orElse(null);


                    if (currentMonster == null) {

                        combatMenu.combatSwitch(playerList, monsterList, currentPlayer, currentMonster);
                        //break;

                    } else if (currentPlayer == null) {

                        monsterAttack.monsterStrikePlayer( monsterList, playerList, currentMonster, currentPlayer);
                        //break;

                    } else {

                        if (currentPlayer.getInitiative() < currentMonster.getInitiative()) {

                            combatMenu.combatSwitch(playerList, monsterList, currentPlayer, currentMonster);

                        } else {

                            monsterAttack.monsterStrikePlayer(monsterList, playerList, currentMonster, currentPlayer);

                        }
                    }
                }
            } while (!(checkPlayerHasPLayed(playerList) && checkMonsterHasPLayed(monsterList)));

            countRounds++;


            System.out.printf("\nend of round  %s \n", countRounds);

            resetPlayerInitiative();

            resetMonsterInitiative(monsterList);

        }

        if (combatMenu.isFled()) {
            ResetCombat resetCombat = new ResetCombat();
            System.out.println("you cowardly fled from the fight... did i already call you a coward? Coward");
            resetCombat.resetPlayerListBackToNormal(playerList);
            return false;

        } else {
            CombatEndingController combatEndingController = new CombatEndingController();
            combatEndingController.decideCombatWinner(playerList, monsterList);
            enter = Input.stringInput();
        }


        if (playerList.isEmpty() && !combatMenu.isFled()) {
            return false;
        } else {
            return true;
        }


    }


    public void resetPlayerInitiative() {
        for (Player player : playerList) {
            player.setHasPlayed(false);
        }
    }

    // move these out?
    public void resetMonsterInitiative(List<Monster> monsterList) {
        for (Monster monster : monsterList) {
            monster.setHasPlayed(false);
        }
    }


    public boolean checkMonsterHasPLayed(List<Monster> monsterList) {

        for (Monster monster : monsterList) {
            if (!monster.isHasPlayed()) {
                return false;
            }
        }
        return true;
    }


    public boolean checkPlayerHasPLayed(List<Player> playerList) {

        for (Player player : playerList) {
            if (!player.isHasPlayed()) {
                return false;
            }
        }
        return true;
    }


    public void checkCombatSorted(List<Player> playerList, List<Monster> monsterList) {


        List<InitiativeListView> initiativeList = new ArrayList<>();

        for (Player player : playerList) {
            initiativeList.add(new InitiativeListView(player.getClassName(), player.getName(), player.getInitiative(), player.getHp(), player.getResource(), player.getId(), player.getDefence(), player.getDamage()));

        }
        for (Monster monster : monsterList) {
            initiativeList.add(new InitiativeListView(monster.getTypeName(), monster.getMonsterName(), monster.getInitiative(), monster.getHp(),monster.getResoruce(), monster.getMonsterId(), monster.getDefence(), monster.getDamage()));
        }

        Collections.sort(initiativeList, new CombatViewInitiativComperator());

        System.out.println("\033[1;33mINITIATIVE LIST highest in list starts (lowest initiative starts)---------------->\033[0m");
        for (InitiativeListView initiativeListView : initiativeList) {
            System.out.println(
                     initiativeListView.getClassName() + "   ||     "
                    +" initiative = " +initiativeListView.getInitiative() + "   ||     "
                    +" Name = " + initiativeListView.getCombatName() + "   ||     "
                    +" HP = " +initiativeListView.getHp()  + "   ||     "
            );
        }
        System.out.println("\nINITIATIVE LIST ----------------------------------------------------------------->");


    }
}

