package com.alexanderhasslund.demo.main.PlayerTest;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Inventory.Inventory;

import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.util.ArrayList;
import java.util.List;

public class PlayerControllerTest {

    private List<PlayerTest> playerListTest;
    private int countPlayers;

    public PlayerControllerTest() {
        this.playerListTest = new ArrayList<>();
    }

    public List<PlayerTest> getPlayerList() {
        return playerListTest;
    }

    public void addPlayer(PlayerTest playerTest) {
        playerListTest.add(playerTest);
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
            addPlayer( new BarbarianTest());
    }

    public void choosePlayerName() {
        for ( int i = 0; i < getCountPlayers(); i++) {
            System.out.printf("Enter character name, for player %s: ", (i + 1));
            playerListTest.get(i).setName(Input.stringInput());
            playerListTest.get(i).setId(i);
        }

        System.out.print("\n");

        for (int i = 0; i < playerListTest.size(); i ++) {
            for (int j = 0; j < 4; j++) {
                playerListTest.get(i).getInventoryList().add(j,new Inventory("",0,0,0,0,0));
            }
        }
    }


    public List<PlayerTest> getPlayerInformation() {
        for (int i = 0; i < playerListTest.size(); i++) {

            System.out.println(
                    " Name = " + playerListTest .get(i).getName() + "  || "
                            + " Class name = " + playerListTest.get(i).getClassName() + "  || "
                            + " Damage = " + playerListTest.get(i).getDamage() + "  || "
                            + " Currency = " + playerListTest.get(i).getCurrency() + "  || "
                            + " Experience = " + playerListTest.get(i).getExperience() + "  || "
                            + " ID = " + playerListTest.get(i).getId()
            );

            //System.out.println(playerList.get(i));
        }
        return playerListTest;
    }

}
