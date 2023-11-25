package com.alexanderhasslund.demo.main.Monster;

import com.alexanderhasslund.demo.main.Engine.Color;

import java.util.Random;

public class Monster {

    Random rand = new Random();
    private String typeName;
    private String monsterName;
    private int hp;
    private int resoruce;
    private int damage;
    private int initiative;
    private int defence;
    private int strength;
    private int agility;
    private int intellect;
    private boolean isDead;
    private int givesExperience;
    private int givesCurrency;
    private boolean hasPlayed;
    private int monsterId;


    public String getTypeName() {
        return typeName;
    }

    public boolean isHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getResoruce() {
        return resoruce;
    }

    public void setResoruce(int resoruce) {
        this.resoruce = resoruce;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getGivesExperience() {
        return givesExperience;
    }

    public void setGivesExperience(int givesExperience) {
        this.givesExperience = givesExperience;
    }

    public int getGivesCurrency() {
        return givesCurrency;
    }

    public void setGivesCurrency(int givesCurrency) {
        this.givesCurrency = givesCurrency;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public Monster(String typName, String monsterName, int hp, int resoruce, int damage, int strength, int agility, int intellect, boolean isDead, int givesExperience, int givesCurrency, int defence, boolean hasPlayed, int monsterId) {

        this.typeName = typName;
        this.monsterName = monsterName;
        this.hp = hp;
        this.resoruce = resoruce;
        this.damage = damage;
        this.initiative = rand.nextInt(3,9) * 10;
        this.strength = strength;
        this.agility = agility;
        this.intellect = intellect;
        this.isDead = isDead;
        this.givesExperience = givesExperience;
        this.givesCurrency = givesCurrency;
        this.defence = defence;
        this.hasPlayed = hasPlayed;
        this.monsterId = monsterId;
    }

    @Override
    public String toString() {
        return
                Color.CYAN + typeName + " - " + Color.RESET + monsterName + " -- " +
                " HP = " + hp + " -- " +
                " Defence = " + defence + " -- " +
                " damage = " + damage + " -- " +
                " initiative = " + initiative + " -- " +
                " strength = " + strength + " -- " +
                " agility = " + agility + " -- " +
                " intellect = " + intellect;
    }
}
