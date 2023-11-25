package com.alexanderhasslund.demo.main.Classes;
import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Sorcerer extends Player implements IClasses, ICombat, Serializable {

    private final String className;
    private int id;

    private int hp;
    private int damage;
    private int resource;
    private int strength;
    private int agility;
    private int intellect;
    private int level;
    private int defence;
    private int initiative;
    private boolean isDead;
    private boolean hasPlayed;

    private int maxResource;
    private int maxHp;

    private int baseStrength;
    private int baseAgility;
    private int baseIntellect;
    private int baseDefence;
    private int baseDamage;

    public Sorcerer() {
        this.className = Color.BLUE + "SORCERER" + Color.RESET;
        this.hp = 90;
        this.id = 0;
        this.damage = 10; //find a good formula;
        this.resource = 150;
        this.strength = 3;
        this.agility = 5;
        this.intellect = 22;
        this.level = 1;
        this.initiative = 70;
        this.defence = 5;
        this.maxHp = 90;
        this.maxResource = 150;
        this.isDead = false;
        this.hasPlayed = false;

        this.baseStrength = 3;
        this.baseAgility = 5;
        this.baseIntellect = 22;
        this.baseDefence = 5;
        this.baseDamage = 10;

    }

    @Override
    public void setLevelUp(Player currentPlayer) {

        int PlayerCurrentExperienceExperince = currentPlayer.getExperience();
        currentPlayer.setExperience(0);

        for (int i = PlayerCurrentExperienceExperince; i > 0; i--) {
            currentPlayer.setExperience(currentPlayer.getExperience()+1);

            if (currentPlayer.getExperience() == 100) { // fix better logic for leveling...
                currentPlayer.setLevel(currentPlayer.getLevel() +1);
                System.out.printf("%s %s just leveled up to level %s! \n", currentPlayer.getClassName(), currentPlayer.getName(), currentPlayer.getLevel());
                currentPlayer.setExperience(0);
                addStatsToPlayer(currentPlayer);
            }
        }
    }


    @Override
    public void addStatsToPlayer(Player currentPlayer) {

        currentPlayer.setBaseStrength(currentPlayer.getBaseStrength() + (int)(currentPlayer.getLevel() / 10));
        currentPlayer.setBaseAgility(currentPlayer.getBaseAgility() + (int)(currentPlayer.getLevel() / 10));
        currentPlayer.setBaseIntellect(currentPlayer.getBaseIntellect() + (int)(currentPlayer.getLevel() / 3));

        currentPlayer.setBaseDamage(currentPlayer.getBaseDamage() + (currentPlayer.getIntellect() / 8));
        currentPlayer.setMaxResource(currentPlayer.getMaxResource() + (currentPlayer.getIntellect() / 5));
        currentPlayer.setBaseDefence(currentPlayer.getBaseDefence() + (int) (currentPlayer.getIntellect() * 0.2));
        currentPlayer.setMaxHp(currentPlayer.getMaxHp() + (int) (currentPlayer.getBaseStrength() * 0.5));
    }



    @Override
    public void ultimate(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {

        if (currentPlayer.getResource() >= 100) {
            System.out.println("The sorcerer muster all its power and blast all monster in range: ");

            for (Monster monster : monsterList) {
                monster.setHp(monster.getHp() - (int) (currentPlayer.getDamage() + (level * 1.3))); // guessing the damage gets fucked with the multiplier
                System.out.println("CLEAVING EACH MONSTER FOR: " + ((int) (currentPlayer.getDamage() + (level * 1.3))));
            }
            currentPlayer.setResource(currentPlayer.getResource() - 100);
        } else {
            System.out.printf("The %s %s doesnt have enough mana to perform dragons breath!", currentPlayer.getClassName(), currentPlayer.getName());
        }
    }

    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {
        System.out.println(PlayerChoice.spellsSorcerer());
        int sorcererSpells = Input.intInput();

        switch (sorcererSpells) {
            case 1 -> {
                if (currentPlayer.getResource() >= 40) {
                    System.out.println("Sorcerer gets a barrier, shielding for 30 damage: ");
                    currentPlayer.setHp(currentPlayer.getHp() + 30);
                    currentPlayer.setResource(currentPlayer.getResource() - 40);
                } else {
                    System.out.println("The sorcerer doesnt have enough mana");
                }
            }
            case 2 -> {
                System.out.println("Striking two targets at the same time, dealing damage based on your intellect");
                //int monsterChoice = 1;
                if (currentPlayer.getResource() >= 70) {
                    for (int i = 0; i < 2; i++) {
                        int monsterChoice = 1;
                        for (Monster monster1 : monsterList) {
                            System.out.println("CHOICE: " + monsterChoice + " " + monster1);
                            monsterChoice++;
                        }
                        System.out.print("Decide what monster you want to hit: ");
                        int monsterIndex = Input.intInput() - 1;
                        // build a miss system? Even for monsters based on something.

                        monsterList.get(monsterIndex).setHp(monsterList.get(monsterIndex).getHp() -
                                (currentPlayer.getDamage() + currentPlayer.getInventoryList().get(0).getDamage() + currentPlayer.getInventoryList().get(1).getDamage()
                                        + (currentPlayer.getBaseStrength() / 7)
                                        + currentPlayer.getLevel()));
                    }
                    currentPlayer.setResource(currentPlayer.getResource() - 70);
                } else {
                    System.out.println("The sorcerer doesnt have enough mana");
                }
            }
            default -> {
                System.out.println("Use right input");
            }
        }
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

        int sorcererDamage = calculateDamage(playerList, currentPlayer,monsterList, monsterIndex);
        monsterList.get(monsterIndex).setHp(monsterList.get(monsterIndex).getHp() - sorcererDamage);

        System.out.printf("The Sorcerer attacks with all element aligned, Dealing %s to monster %s \n", sorcererDamage, monsterList.get(monsterIndex).getMonsterName());
    }

    public int calculateDamage(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, int monsterIndex) {
        int calculateHeroDamage =
                currentPlayer.getDamage()
                + currentPlayer.getInventoryList().get(0).getDamage()
                + currentPlayer.getInventoryList().get(1).getDamage()
                + (currentPlayer.getIntellect() / 5);

        return calculateHeroDamage;
    }

    @Override
    public boolean isHasPlayed() {
        return hasPlayed;
    }

    @Override
    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    @Override
    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    @Override
    public int getBaseStrength() {
        return baseStrength;
    }

    @Override
    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    @Override
    public int getBaseAgility() {
        return baseAgility;
    }

    @Override
    public void setBaseAgility(int baseAgility) {
        this.baseAgility = baseAgility;
    }

    @Override
    public int getBaseIntellect() {
        return baseIntellect;
    }

    @Override
    public void setBaseIntellect(int baseIntellect) {
        this.baseIntellect = baseIntellect;
    }

    @Override
    public int getBaseDefence() {
        return baseDefence;
    }

    @Override
    public void setBaseDefence(int baseDefence) {
        this.baseDefence = baseDefence;
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(int maxResource) {
        this.maxResource = maxResource;
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
    public int getDefence() {
        return defence;
    }

    @Override
    public void setDefence(int defence) {
        this.defence = defence;
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
    public int getInitiative() {
        return initiative;
    }

    @Override
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void setDead(boolean dead) {
        isDead = dead;
    }


    @Override
    public String toString() {
        return
                Color.BLUE+ className + Color.RESET + ":" +
                "  Hp = " + hp + "   || " +
                " Damage = " + damage + "  || " +
                " Resource = " + resource + "  || " +
                " Strength = " + strength + "  || " +
                " Agility = " + agility + "  || " +
                " Intellect = " + intellect + "  || " +
                " Initiative = " + initiative + "  ||";
    }

}
