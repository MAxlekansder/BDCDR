package com.alexanderhasslund.demo.main.Combat;

import com.alexanderhasslund.demo.main.Combat.CombatController.CombatController;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterBrute;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.util.ArrayList;
import java.util.List;

public class FirstCombatEncounter {

    private List<Player> playerList;
    private int playerCount; // from older versions, left it in just because



    public FirstCombatEncounter(List<Player> playerList, int playerCount) {
        this.playerList = playerList;
        this.playerCount = playerCount;
    }

    public void firstPlayerFight() {
        List<Monster> firstMonsterList = new ArrayList<>();
        CombatMenu combatMenu = new CombatMenu();
        firstMonsterList.add(new MonsterBrute());
        PlayerChoice playerChoice = new PlayerChoice();

        firstMonsterList.get(0).setHp(10);
        firstMonsterList.get(0).setInitiative(1);

        // move these out
        System.out.println
                ("""
                                        
                        This is your first encounter - and to be nice, the monster you're fighting has,
                        should we say... been modified... 
                                        
                        BUT! to see how the game play plays out, the monster start... 
                                        
                        You now have the option to explore every option during combat
                        """);


        CombatController combatController = new CombatController(playerList, firstMonsterList);
        boolean isPlaying = combatController.initiateFight();

        if (!isPlaying) {

            CombatController combatControllerif = new CombatController(playerList, firstMonsterList);
            System.out.println("\nyou're not getting away that easy...");
            System.out.println("We're going again....");
            firstMonsterList.add(new MonsterBrute());
            firstMonsterList.get(0).setHp(10);
            firstMonsterList.get(0).setInitiative(1);
            firstMonsterList.get(0).setGivesExperience(5);
            combatControllerif.initiateFight();

        } else {

            // move these out
            System.out.println
                    ("""
                            Good job! 
                                            
                            As you might have understood, this was a mockup encounter... but im a bit nice, and might play in your
                            favor... 
                            """);

            for (Player player : playerList) {
                System.out.println(player.getName() + "\033[1;32m gets 200 coins! and some exp\033[0m");
                player.setCurrency(200);
            }

            ResetCombat resetCombat = new ResetCombat();
            resetCombat.resetPlayerListBackToNormal(playerList);
            System.out.println("\nThis might help you... for now...");
            System.out.println(playerChoice.presentMainMenu());
            System.out.println("Press enter to continue ");
            String enter = Input.stringInput();

        }

    }
}
