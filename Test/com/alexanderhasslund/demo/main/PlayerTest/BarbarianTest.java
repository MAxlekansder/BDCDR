package com.alexanderhasslund.demo.main.PlayerTest;

import com.alexanderhasslund.demo.main.Classes.IClasses;
import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public class BarbarianTest extends PlayerTest implements IClasses, ICombat {

    private final String className;
    private int id;
    private int maxHp;
    private int hp;
    private int damage;
    private int resource;
    private int maxResource;
    private int strength;
    private int agility;
    private int intellect;
    private int level;
    private int defence;
    private int initiative;
    private boolean isDead;
    private boolean hasPlayed;

    private int baseDamage;
    private int baseStrength;
    private int baseAgility;
    private int baseIntellect;
    private int baseDefence;



    public BarbarianTest() {
        //Player barbarian = new Player(getName(),getTalentTreeList(),getInventoryList(),0,0,"BARBARIAN",115,20,100,22,8,0,0,10,55);
        //return barbarian;
        this.className = Color.RED + "BARBARIAN" + Color.RESET;
        this.hp = 115;
        this.id = 0;
        this.damage = 20;
        this.resource = 100;
        this.strength = 22;
        this.agility = 8;
        this.intellect = 0;
        this.level = 1;
        this.defence = 1;
        this.initiative = 55;
        this.isDead = false;
        this.hasPlayed = false;

        this.baseDamage = 20;
        this.baseStrength = 22;
        this.baseAgility = 8;
        this.baseIntellect = 0;
        this.baseDefence = 15;
    }



    @Override
    public void addStatsToPlayer(Player player) {

    }



    @Override
    public void ultimate(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {
        //berserkers rage
        //when hp is below 30 <- activate berserkers rage

        if ( hp <= hp * 0.3) {
            System.out.println("The barbarian gains berserker rage, gaining extra damage");
            while (hp <= hp * 0.3) {
                damage += (int) (damage * 0.07);
            }
        }
    }


    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {
        int temporaryBuffs = 0;
        switch (1) {
            case 1 -> { // a baseline damage spell that adds 3 damage and uses 'resources'

                System.out.println("Cleaves the target with: " + temporaryBuffs);
                strength += 10;
                resource -= 20;
            }
            case 2 -> { //
                System.out.println( "The barbarian muster its rage, gaining defence euqal to: " + (defence + 2));
                resource -= 30;
                defence += 2;
            }
            default -> {System.out.println("Use right input");}
        }
    }

    @Override
    public void setLevelUp(Player player) {

    }


    @Override
    public void attack(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, Monster monster) {
        int monsterChoice = 1;

        for (Monster monster1 : monsterList) {
            System.out.println("CHOICE: "+ monsterChoice+ " " + monster1);
            monsterChoice++;
        }
        System.out.print("Decide what monster you want to hit: ");
        int monsterIndex = Input.intInput() -1;
        // build a miss system? Even for monsters based on something.

        monsterList.get(monsterIndex).setHp(monsterList.get(monsterIndex).getHp() - currentPlayer.getDamage());
        System.out.printf("\nThe barbarian attacks with a hard hitting strike, Dealing %s to monster %s", currentPlayer.getDamage(), monsterList.get(monsterIndex).getMonsterName());
    }




    @Override
    public String toString() {
        return
                Color.RED + className + Color.RESET + ":" +
                        " Hp = " + hp + "  || " +
                        " Damage = " + damage + "  || " +
                        " Resource = " + resource + "  || " +
                        " Strength = " + strength + "  || " +
                        " Agility = " + agility + "  || " +
                        " Intellect = " + intellect + "  || " +
                        " Initiative = " + initiative + "  ||";
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(int maxResource) {
        this.maxResource = maxResource;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public int getBaseAgility() {
        return baseAgility;
    }

    public void setBaseAgility(int baseAgility) {
        this.baseAgility = baseAgility;
    }

    public int getBaseIntellect() {
        return baseIntellect;
    }

    public void setBaseIntellect(int baseIntellect) {
        this.baseIntellect = baseIntellect;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public void setBaseDefence(int baseDefence) {
        this.baseDefence = baseDefence;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getClassName() {
        return className;
    }


    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getResource() {
        return resource;
    }

    @Override
    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getAgility() {
        return agility;
    }

    @Override
    public void setAgility(int agility) {
        this.agility = agility;
    }

    @Override
    public int getIntellect() {
        return intellect;
    }

    @Override
    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public int getInitiative() {
        return initiative;
    }

    @Override
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }


}
