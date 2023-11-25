package com.alexanderhasslund.demo.main.Shop;

import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;
import com.alexanderhasslund.demo.main.Shop.Defence.ShieldsShop;
import com.alexanderhasslund.demo.main.Shop.Potions.PotionsShop;
import com.alexanderhasslund.demo.main.Shop.Weapon.SwordsShop;

import java.util.List;

public class ShopMenu {

    private List<Player> playerList;

    public ShopMenu(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void mainShopMenu() {
        SwordsShop swordsShop = new SwordsShop(playerList);
        ShieldsShop shieldsShop = new ShieldsShop(playerList);
        PotionsShop potionsShop = new PotionsShop(playerList);
        PlayerChoice playerChoice = new PlayerChoice();

        System.out.println(playerChoice.mainShop());
        //choosePlayerShop();
        int playerIndex = choosePlayerShop();

        System.out.println("\nName: " + playerList.get(playerIndex).getName() + " Current currency: " + playerList.get(playerIndex).getCurrency());
        // if playerIndex == -1 break...
        boolean isBuying = true;
        do {
            System.out.println(playerChoice.insideShop());
            switch (Input.intInput()) {
                case 1 -> {
                    swordsShop.swordsShopSwitch(playerIndex);
                    //swords
                }
                case 2 -> {
                    shieldsShop.shieldsShopSwitch(playerIndex);
                    //Shields
                }
                case 3 -> {
                    potionsShop.potionsMenuSwitch(playerIndex);
                    // potions
                }
                case 4 -> {
                    isBuying = false;
                }
            }
        } while (isBuying);
    }

    // build a safety check here to make sure wrong name isnt put in ->
    public int choosePlayerShop() {

        playerList.forEach(player -> System.out.println("PLAYERLIST: " + player.getName().toUpperCase() + " " + player.getClassName() + " " + player.getLevel()));
        boolean isChoosing = true;
        int playerChoice = 0;

        while (isChoosing) {
            System.out.println("Choose player to use shop: ");
            String playerChoiceName = Input.stringInput().toLowerCase();
            for (int i = 0; i < playerList.size(); i++) {

                if (playerList.get(i).getName().toLowerCase().equals(playerChoiceName)) {
                    //System.out.println(" Name: " + playerList.get(i).getName() + " Current currency: " + playerList.get(i).getCurrency());
                    playerChoice = i;
                    isChoosing = false;
                }
            }
        }
        return playerChoice;
    }

}


