package com.alexanderhasslund.demo.main.Shop.Weapon;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;
import java.util.List;

public class SwordsShop {

    private List<Player> playerList;

    public SwordsShop(List<Player> playerList) {
        this.playerList = playerList;
    }


    public int swordsShopSwitch (int playerIndex) {
        Swords swords = new Swords();
        PlayerChoice playerChoice = new PlayerChoice();
        int swordPrice = 0;

        System.out.println(playerChoice.swordsMenu());
        int swordChoice = Input.intInput();
        boolean isShop = true;

        do {
            switch (swordChoice) {
                case 1 -> {
                    if (playerList.get(playerIndex).getLevel() >= 0 && playerList.get(playerIndex).getCurrency() >= 200) {
                        playerList.get(playerIndex).getInventoryList().set(0,swords.standardSword());

                        swordPrice = 200;
                        isShop = false;

                    } else {
                        System.out.println("... ah, you're not really there yet...");
                        isShop = false;
                    }
                }
                case 2 -> {
                    if (playerList.get(playerIndex).getLevel() >= 2 && playerList.get(playerIndex).getCurrency() >= 500) {
                        playerList.get(playerIndex).getInventoryList().set(0,swords.fastSword());

                        swordPrice = 500;
                        isShop = false;
                    } else {
                        System.out.println("With haste hero!! as far as you come for now, just hold on a bit more...");
                        isShop = false;
                    }

                }
                case 3 -> {
                    if (playerList.get(playerIndex).getLevel() >= 5 && playerList.get(playerIndex).getCurrency() >= 1000) {
                        playerList.get(playerIndex).getInventoryList().set(0,swords.sharpSword());

                        swordPrice = 1000;
                        isShop = false;
                    } else {
                        System.out.println("ah, the sword of thousands truths... A gift from the crouching tiger, hidden dragon");
                        isShop = false;
                    }

                }
                case 4 -> {
                    if (playerList.get(playerIndex).getLevel() >= 10 && playerList.get(playerIndex).getCurrency() >= 3000) {
                        playerList.get(playerIndex).getInventoryList().set(0,swords.divineSword());

                        swordPrice = 15000;
                        isShop = false;
                    } else {
                        System.out.println("... only a fool would reach for divinity...");
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

        if (swordPrice != 0) {
            playerList.get(playerIndex).setCurrency(playerList.get(playerIndex).getCurrency() - swordPrice);
            checkSwordSlot(swordPrice, playerIndex);
            return swordPrice;
        }
        return swordPrice;
    }


    public void checkSwordSlot(int swordPrice, int playerIndex) {

            if (swordPrice == 0) {
                System.out.println("see you around...");
            }

        System.out.println(playerList.get(playerIndex).getName()+ " bought " + playerList.get(playerIndex).getInventoryList().get(0).getItemName()
                + " with " +playerList.get(playerIndex).getInventoryList().get(0).getInitiative() + " initiative"
                + " and " + playerList.get(playerIndex).getInventoryList().get(0).getDamage() + " damage, with a cost of: " + swordPrice);
        System.out.println("Balance left: " + (playerList.get(playerIndex).getCurrency()));

        /*
        playerList.forEach(player -> {
            System.out.println(player.getName() + " bought " + playerList.get(playerIndex).getInventoryList().get(0).getItemName()
                    + " with " +playerList.get(playerIndex).getInventoryList().get(0).getInitiative() + " initiative"
                    + " and " + playerList.get(playerIndex).getInventoryList().get(0).getDamage() + " damage, with a cost of: " + swordPrice);
            System.out.println("Balance left: " + (player.getCurrency()));
        }); */
    }
}
