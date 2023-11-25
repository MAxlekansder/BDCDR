package com.alexanderhasslund.demo.main.Combat.CombatController;

import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class InitiativeListView {

    private String className;
    private String combatName;
    private int initiative;
    private int hp;
    private int resource;
    private int id;
    private int defence;
    private int damage;


    public InitiativeListView(String className, String combatName, int initiative, int hp, int resource, int id, int defence, int damage) {
        this.className = className;
        this.combatName = combatName;
        this.initiative = initiative;
        this.hp = hp;
        this.resource = resource;
        this.id = id;
        this.defence = defence;
        this.damage = damage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getCombatName() {
        return combatName;
    }

    public void setCombatName(String combatName) {
        this.combatName = combatName;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public String toString() {
        return "InitiativeListView{" +
                "className='" + className + '\'' +
                ", combatName='" + combatName + '\'' +
                ", initiative=" + initiative +
                ", hp=" + hp +
                ", resource=" + resource +
                ", id=" + id +
                ", defence=" + defence +
                ", damage=" + damage +
                '}';
    }
}

