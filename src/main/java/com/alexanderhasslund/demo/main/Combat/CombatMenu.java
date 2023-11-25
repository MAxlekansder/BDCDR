package com.alexanderhasslund.demo.main.Combat;

import com.alexanderhasslund.demo.main.Combat.CombatController.PlayerAttack;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;

import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.util.List;

public class CombatMenu {

    private boolean isFled;

    public boolean isFled() {
        return isFled;
    }

    public void setFled(boolean fled) {
        this.isFled = fled;
    }



    public void combatSwitch(List<Player> playerList, List<Monster> monsterList, Player currentPlayer, Monster currentMonster) {
        PlayerChoice playerChoice = new PlayerChoice();
        PlayerAttack playerAttack = new PlayerAttack();
        System.out.printf("Player %s %s turn: Health - \033[1;37m%s\033[0m resoruce - \033[1;37m%s\033[0m"
                , currentPlayer.getClassName(),currentPlayer.getName(), currentPlayer.getHp(), currentPlayer.getResource());

        int fightInput = 0;
        boolean isFighting = true;
        boolean isViewing = true;


        while (isFighting) {
            isViewing = true;
            while (isViewing) {
                System.out.println(playerChoice.fightSequence());
                fightInput = Input.intInput();
                switch (fightInput) {
                    case 1 -> {

                        System.out.println(playerChoice.abilityChoice());
                        playerAttack.fightMonster(playerList, monsterList, currentPlayer, currentMonster);

                        isFighting = false;
                        isViewing = false;

                    }
                    case 2 -> {
                        System.out.println(playerChoice.potionChoice());
                        usePotion(currentPlayer);
                    }

                    case 3 -> {

                        getStatusActivePlayerAndMosnter(playerList, monsterList);
                        isViewing = false;
                    }
                    case 4 -> {
                        Flee flee = new Flee();
                        flee.chanceOfFleeing(monsterList);
                        isViewing = false;
                        isFighting = false;
                        isFled = true;

                    }
                    default -> {
                        System.out.println("use the right input");
                    }
                }
            }
        }
    }

    public void getStatusActivePlayerAndMosnter(List<Player> playerList, List<Monster> monsterList) {
        for (Player player : playerList) {
            System.out.println(
                    player.getClassName()
                    + " || Player = " +player.getName()
                    + " || HP = " + player.getHp()
                    + " || Resource = " + player.getResource()
                    + " || Damage = " + (player.getDamage() + player.getInventoryList().get(0).getDamage() + player.getInventoryList().get(1).getDamage())
                    + " || Defence = " + (player.getDefence() + player.getInventoryList().get(0).getDefence() + player.getInventoryList().get(1).getDefence())
                    + " || Initiative = " + player.getInitiative() +"\n"
            );
        }

        for (Monster monster : monsterList) {
            System.out.println(
                    Color.CYAN +"MONSTER" + Color.RESET
                    + " // Type = "+ monster.getMonsterName()
                    + " // HP = " + monster.getHp()
                    + " // ID = " + (monster.getMonsterId() +1)
                    + " // Damage = " + monster.getDamage()
                    + " // Defence = "  + monster.getDefence()
                    + " // Initiative = "  + monster.getInitiative());
        }
    }

    public void usePotion(Player currentPlayer) {
        int potionChoice = Input.intInput();
        boolean isPotionMenu = true;
        do {
            System.out.printf("Current potion equipped: %s",currentPlayer.getInventoryList().get(2).getItemName().toUpperCase());
            switch (potionChoice) {

                case 1 -> {

                    if ( currentPlayer.getInventoryList().get(2).getItemName().equals("Potion of Haste")) {
                        System.out.printf("%s %s used potion of Haste! Setting them first ahead, quick arent we?", currentPlayer.getClassName(), currentPlayer.getName());
                        currentPlayer.setInitiative(1);
                        currentPlayer.getInventoryList().remove(2);
                    }

                    if ( currentPlayer.getInventoryList().get(2).getItemName().equals("Potion of Defence")) {
                        System.out.printf("%s %s used potion of defence! you start feeling almost like a stone...",currentPlayer.getClassName(), currentPlayer.getName());
                        currentPlayer.setDefence(currentPlayer.getDefence() + 20);
                        currentPlayer.getInventoryList().remove(2);
                    }

                    if ( currentPlayer.getInventoryList().get(2).getItemName().equals("Potion of Health")) {
                        System.out.printf("%s %s used potion of health! Refreshing...",currentPlayer.getClassName(), currentPlayer.getName());
                        int newHp = currentPlayer.getHp() + 40;

                        if (newHp > currentPlayer.getMaxHp()) {
                            currentPlayer.setHp(currentPlayer.getMaxHp());
                        } else {
                            currentPlayer.setHp(newHp);
                        }
                        currentPlayer.getInventoryList().remove(2);

                    }
                    isPotionMenu = false;
                }
                case 2 -> {
                    isPotionMenu = false;
                }
                default -> {
                    System.out.println("use the right input");
                }
            }
        } while (isPotionMenu);
    }
}
