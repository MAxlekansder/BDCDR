package com.alexanderhasslund.demo.main.Shop.Potions;

import com.alexanderhasslund.demo.main.Inventory.Inventory;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public class Potion {

    public Inventory potionOfHaste() {
        Inventory potionOfHaste = new Inventory("Potion of Haste",0,1,0,0,0);
        return potionOfHaste;
    }

    public Inventory potionOfDefence() {
        Inventory potionofDefence = new Inventory("Potion of Defence",0,0,0,25,0);
        return potionofDefence;
    }

    public Inventory potionOfHealth() {
        Inventory potionOfHealth = new Inventory("Potion of Health",0,0,0,0,0);
        return potionOfHealth;
    }

}
