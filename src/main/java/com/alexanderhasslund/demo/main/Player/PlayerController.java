package com.alexanderhasslund.demo.main.Player;
import com.alexanderhasslund.demo.main.Classes.Barbarian;
import com.alexanderhasslund.demo.main.Classes.Rogue;
import com.alexanderhasslund.demo.main.Classes.Sorcerer;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Inventory.Inventory;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.util.ArrayList;
import java.util.List;


public class PlayerController {
    private List<Player> playerList;
    private int countPlayers;

    public PlayerController() {
        this.playerList = new ArrayList<>();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public int getCountPlayers() {
        return countPlayers;
    }

    public void setCountPlayers(int countPlayers) {
        this.countPlayers = countPlayers;
    }



    public void playerCount() {
        boolean isMany = true;
        while(isMany) {
            System.out.print("\nPlease enter number of players: ");
            int playerC = Input.intInput();
            if(playerC>3) {
                System.out.println("Can't be more than 3");
            } else {
                setCountPlayers(playerC);
                isMany = false;
            }
        }

    }


    public void chooseClass() {
        for (int i = 0; i < getCountPlayers(); i++) {

            PlayerChoice playerChoice = new PlayerChoice();
            System.out.println(playerChoice.nameAndClass());
            boolean choice = true;
            do {
                switch (Input.intInput()) {
                    case 1 -> {addPlayer( new Barbarian());choice = false;}
                    case 2 -> {addPlayer( new Rogue());choice = false;}
                    case 3 -> {addPlayer( new Sorcerer());choice = false;}
                    default -> {System.out.println("Use right input");}

                }
            } while (choice);
        }
    }

    public void choosePlayerName() {
        for ( int i = 0; i < getCountPlayers(); i++) {
            System.out.printf("Enter character name, for player %s: ", (i + 1));
            String playerName;
            do {
                 playerName = (Input.stringInput()).trim();
                if (playerName.isEmpty()) {
                    System.out.println("Please enter a real name, dont leave it empty");
                }
            } while (playerName.isEmpty());

            playerList.get(i).setName(playerName);
            playerList.get(i).setId(i);
        }


        System.out.print("\n");

        for (int i = 0; i < playerList.size(); i ++) {
            for (int j = 0; j < 4; j++) {
                playerList.get(i).getInventoryList().add(j,new Inventory("",0,0,0,0,0));
            }
        }
    }


    public List<Player> getPlayerInformation() {
        for (int i = 0; i < playerList.size(); i++) {

            System.out.println(
                    " Name = " + playerList.get(i).getName() + "  || "
                  + " Class name = " + playerList.get(i).getClassName() + "  || "
                  + " Damage = " + playerList.get(i).getDamage() + "  || "
                  + " Currency = " + playerList.get(i).getCurrency() + "  || "
                  + " Experience = " + playerList.get(i).getExperience() + "  || "
                  + " ID = " + playerList.get(i).getId()
            );

            //System.out.println(playerList.get(i));
        }
        return playerList;
    }


}


