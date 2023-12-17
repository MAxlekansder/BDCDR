package com.alexanderhasslund.demo.main.Shop.Potions;

import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabaseItemCollector;
import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabasePlayerWriter;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import javax.xml.crypto.Data;
import java.util.List;

public class PotionsShop {

    private List<Player> playerList;

    public PotionsShop(List<Player> playerList) {
        this.playerList = playerList;
    }

    public int potionsMenuSwitch(int playerIndex) {
        DatabaseItemCollector databaseItemCollector = new DatabaseItemCollector();
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        Potion potion = new Potion();
        databaseItemCollector.writingItemPotion(potion);
        PlayerChoice playerChoice = new PlayerChoice();
        int potionPrice = 0;

        System.out.println(playerChoice.potionsShop());
        int potionChoice = Input.intInput();
        boolean isShop = true;

        do {

            switch (potionChoice) {
                case 1 -> {
                    if (playerList.get(playerIndex).getCurrency() >= 50) {
                        playerList.get(playerIndex).getInventoryList().set(2, potion.potionOfHealth());
                        potionPrice= 50;
                        isShop = false;

                        Player currentPlayer = playerList.get(playerIndex);
                        databaseItemCollector.checkItemId(currentPlayer, 2);
                        databasePlayerWriter.addingPlayerInventory(currentPlayer, 2, potionPrice, 2);

                    }
                }
                case 2 -> {
                    if (playerList.get(playerIndex).getCurrency() >= 70) {
                        playerList.get(playerIndex).getInventoryList().set(2, potion.potionOfDefence());
                        potionPrice = 70;
                        isShop = false;


                        Player currentPlayer = playerList.get(playerIndex);
                        databaseItemCollector.checkItemId(currentPlayer, 2);
                        databasePlayerWriter.addingPlayerInventory(currentPlayer, 2, potionPrice, 2);

                    }
                }
                case 3 -> {
                    if (playerList.get(playerIndex).getCurrency() >= 200) {
                        playerList.get(playerIndex).getInventoryList().set(2, potion.potionOfHaste());
                        potionPrice = 200;
                        isShop = false;


                        Player currentPlayer = playerList.get(playerIndex);
                        databaseItemCollector.checkItemId(currentPlayer, 2);
                        databasePlayerWriter.addingPlayerInventory(currentPlayer, 2, potionPrice, 2);

                    }
                }
                 case 5 -> { isShop = false;

                 }
                default -> {
                    System.out.println("Use the right input");
                }
            }
        } while (isShop);

        if (potionPrice != 0) {
            playerList.get(playerIndex).setCurrency(playerList.get(playerIndex).getCurrency() - potionPrice);
            checkPotionSlot(potionPrice, playerIndex);
            return potionPrice;
        }
        return potionPrice;
    }

    public void checkPotionSlot(int potionPrice, int playerIndex) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        int shieldIndex = 1;
        if (potionPrice == 0) {
            System.out.println("see you around...");
        }

        System.out.println(playerList.get(playerIndex).getName()+ " bought " + playerList.get(playerIndex).getInventoryList().get(2).getItemName()
             + " with a cost of: " + potionPrice);
        System.out.println("Balance left: " + (playerList.get(playerIndex).getCurrency()));
        databasePlayerWriter.updatePlayerPurchase(playerList.get(playerIndex));

    }
}
