package com.alexanderhasslund.demo.main.Combat;

import com.alexanderhasslund.demo.main.Monster.Monster;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Flee {


    public void chanceOfFleeing(List<Monster> monsterList) {

        if (!monsterList.isEmpty()) {
            monsterList.removeAll(monsterList);
            System.out.println("You got away safetly!");
        } else {
            System.out.println("No monsters to flee from!");
        }

    }

}
