package com.alexanderhasslund.demo.main.PlayerInteraction;

import com.alexanderhasslund.demo.main.Combat.CombatController.CombatController;
import com.alexanderhasslund.demo.main.Combat.CombatController.CombatEndingController;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.File.SaveFile;
import com.alexanderhasslund.demo.main.Monster.MonsterController;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.Shop.ShopMenu;


import java.util.List;

public class MainGameControl {

    private List<Player> playerList;
    //private List<Monster> monsterList;
    private int countPlayers;

    public MainGameControl(List<Player> playerList, int countPlayers) {
        this.playerList = playerList;
        this.countPlayers = countPlayers;
        //  this.monsterList = monsterList;
    }


    public void mainSwitch()  {
        PlayerChoice playerChoice = new PlayerChoice();
        ShopMenu shopMenu = new ShopMenu(playerList); // this line becomes null, seems like something is wrong
        SaveFile saveFile = new SaveFile();
        MonsterController monsterController = new MonsterController(countPlayers);
        CombatController combatController = new CombatController(playerList, monsterController.getMonsterList());
        CombatEndingController combatEndingController = new CombatEndingController();
        GameLevelMenu gameLevelMenu = new GameLevelMenu(combatEndingController.getCalculateLevels(), playerList, countPlayers);


        boolean isMainPlaying = true;
        while (isMainPlaying) {
            System.out.println(playerChoice.mainMenuChoice());
            switch (Input.intInput()) {
                case 1 -> {

                    isMainPlaying = gameLevelMenu.gameViewSwitch();
                    if (!(isMainPlaying && playerList.isEmpty())) {
                        isMainPlaying = true;
                    } else {
                        isMainPlaying = false;
                    }
                }
                case 2 -> {
                    shopMenu.mainShopMenu();
                }
                case 3 -> {
                    saveFile.saveFilePlayer(playerList);
                }
                case 4 -> {
                    checkPlayerStatus();
                }

                case 5 -> {
                    isMainPlaying = false;
                }
                default -> {
                    System.out.println("Use the right input");
                }
            }
        }
    }

    public void checkPlayerStatus() {
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(
                    Color.WHITE + playerList.get(i).getName().toUpperCase() + Color.RESET +
                            Color.WHITE + " Current level: " + Color.PURPLE + playerList.get(i).getLevel() + Color.RESET +
                            Color.WHITE + " Current currency:  " + Color.YELLOW + playerList.get(i).getCurrency() + Color.RESET +
                            Color.WHITE + " Current experience " + Color.CYAN + playerList.get(i).getExperience() + Color.RESET +

                            "  Class =  " + playerList.get(i).getClassName() + "  ||  " +
                            " Damage = " + (playerList.get(i).getBaseDamage() + playerList.get(i).getInventoryList().get(0).getDamage() + playerList.get(i).getInventoryList().get(1).getDamage()) + "  ||  " +
                            " HP = " + (playerList.get(i).getMaxHp()) + " / " + playerList.get(i).getHp() + "  ||  " +
                            " Resource = " + playerList.get(i).getMaxResource() + " / " + playerList.get(i).getResource()+ "  ||  " +
                            " Strength = " + playerList.get(i).getBaseStrength() + "  ||  " +
                            " Agility = " + playerList.get(i).getBaseAgility() + "  ||  " +
                            " Intellect = " + playerList.get(i).getBaseIntellect() + "  ||  " +
                            " Initiative = " + playerList.get(i).getInitiative() + "  ||  " +
                            " Equipped sword = "  +playerList.get(i).getInventoryList().get(0).getItemName() + "  ||  " +
                            " Equipped shield = "  +playerList.get(i).getInventoryList().get(1).getItemName() + "  ||  " +
                            " Equipped potions = "  +playerList.get(i).getInventoryList().get(2).getItemName() + "  ||  "
            );


        }
    }
}
