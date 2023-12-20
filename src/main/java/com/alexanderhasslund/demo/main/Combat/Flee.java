package com.alexanderhasslund.demo.main.Combat;

import com.alexanderhasslund.demo.main.Engine.DatabaseHandler.DatabasePlayerWriter;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Flee {


    public void chanceOfFleeing(List<Monster> monsterList, List<Player>  playerList) {
        DatabasePlayerWriter databasePlayerWriter = new DatabasePlayerWriter();
        if (!monsterList.isEmpty()) {
            databasePlayerWriter.updatePartyHasFled(playerList, monsterList);
            monsterList.removeAll(monsterList);
            System.out.println("You got away safetly!");
        } else {
            System.out.println("No monsters to flee from!");
        }

    }

}
