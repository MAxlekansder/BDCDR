package com.alexanderhasslund.demo.main.Shop.Defence;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;
import java.util.List;
import java.util.stream.IntStream;

public class ShieldsShop {

    private List<Player> playerList;

    public ShieldsShop(List<Player> playerList) {
        this.playerList = playerList;
    }


    public int shieldsShopSwitch (int playerIndex) {
        Shields shields = new Shields();
        PlayerChoice playerChoice = new PlayerChoice();
        int shieldPrice = 0;


        System.out.println(playerChoice.shieldsMenu());
        int shieldChoice = Input.intInput();
        boolean isShop = true;

        do {
            switch (shieldChoice) {
                case 1 -> {
                    if (playerList.get(playerIndex).getLevel() >= 0 && playerList.get(playerIndex).getCurrency() >= 200) {
                        playerList.get(playerIndex).getInventoryList().set(1,shields.standardShield());

                        shieldPrice = 200;
                        isShop = false;

                    } else {
                        System.out.println("... ah, you're not really there yet...");
                        isShop = false;
                    }
                }
                case 2 -> {
                    if (playerList.get(playerIndex).getLevel() >= 2 && playerList.get(playerIndex).getCurrency() >= 500) {
                        playerList.get(playerIndex).getInventoryList().set(1,shields.bulkShield());

                        shieldPrice = 500;
                        isShop = false;
                    } else {
                        System.out.println("looks a bit to heavy for now hero, but with experience and some money of course...");
                        isShop = false;
                    }

                }
                case 3 -> {
                    if (playerList.get(playerIndex).getLevel() >= 5 && playerList.get(playerIndex).getCurrency() >= 1000) {
                        playerList.get(playerIndex).getInventoryList().set(1,shields.spikedShield());

                        shieldPrice = 1000;
                        isShop = false;
                    } else {
                        System.out.println("...HAH, this shield wouldn't just bite the enemies! It would bite you too!");
                        isShop = false;
                    }

                }
                case 4 -> {
                    if (playerList.get(playerIndex).getLevel() >= 10 && playerList.get(playerIndex).getCurrency() >= 3000) {
                        playerList.get(playerIndex).getInventoryList().set(1,shields.divineShield());

                        shieldPrice = 15000;
                        isShop = false;
                    } else {
                        System.out.println("... if the weight of the world doesnt kill you, the fire would ...");
                        isShop = false;
                    }
                }
                case 5 -> isShop = false;
                default -> {
                    System.out.println("Use right input");
                }
            }
            // sets new currency for player - swords
        } while (isShop);

        if (shieldPrice != 0) {
            playerList.get(playerIndex).setCurrency(playerList.get(playerIndex).getCurrency() - shieldPrice);
            checkShieldSlot(shieldPrice, playerIndex);
            return shieldPrice;
        }
        return shieldPrice;
    }


    public void checkShieldSlot(int shieldPrice, int playerIndex) {
        int shieldIndex = 1;
        if (shieldPrice == 0) {
            System.out.println("see you around...");
        }

        System.out.println(playerList.get(playerIndex).getName()+ " bought " + playerList.get(playerIndex).getInventoryList().get(1).getItemName()
                + " with " +playerList.get(playerIndex).getInventoryList().get(1).getInitiative() + " initiative"
                + " and " + playerList.get(playerIndex).getInventoryList().get(1).getDamage() + " damage, with a cost of: " + shieldPrice);
        System.out.println("Balance left: " + (playerList.get(playerIndex).getCurrency()));

    }


}
