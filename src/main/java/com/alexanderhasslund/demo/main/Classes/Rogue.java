package com.alexanderhasslund.demo.main.Classes;

import com.alexanderhasslund.demo.main.Combat.ResetCombat;
import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Rogue extends Player implements IClasses, ICombat, Serializable {

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



    public Rogue() {
        this.className = Color.GREEN + "ROGUE" + Color.RESET;
        this.maxHp = 100;
        this.hp = 100; // change here and check all control values
        this.id = 0;
        this.damage = 15;
        this.resource = 200;
        this.maxResource = 200;
        this.strength = 8;
        this.agility = 20;
        this.intellect = 2;
        this.level = 1;
        this.defence = 6;
        this.initiative = 40;
        this.isDead = false;
        this.hasPlayed = false;

        //setters for reseting when leveling
        this.baseStrength = 8;
        this.baseAgility = 20;
        this.baseIntellect = 2;
        this.baseDefence = 6;
        this.baseDamage = 15;

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

        currentPlayer.setBaseStrength(currentPlayer.getBaseStrength() + (int)(currentPlayer.getLevel() / 5));
        currentPlayer.setBaseAgility(currentPlayer.getBaseAgility() + (int)(currentPlayer.getLevel() / 4));
        currentPlayer.setBaseIntellect(currentPlayer.getBaseIntellect() + (int)(currentPlayer.getLevel() / 3.5));

        currentPlayer.setBaseDamage(currentPlayer.getBaseDamage() + (int)(currentPlayer.getBaseAgility() / 9));
        currentPlayer.setBaseDefence(currentPlayer.getBaseDefence() + (int) (currentPlayer.getBaseStrength() * 9));
        currentPlayer.setMaxHp(currentPlayer.getMaxHp() + (int) (currentPlayer.getBaseStrength() * 0.1));

    }



    @Override
    public void ultimate(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {

        if (currentPlayer.getResource() >= 100) {
            System.out.println("The rogue strikes in a quick sequence, dealing double damage to: ");
            int calcRogueUltimate = (currentPlayer.getInventoryList().get(0).getDamage() + currentPlayer.getDamage() - 4);

            for (Monster monster : monsterList) {

                monster.setHp(monster.getHp() - calcRogueUltimate); // based on sword damage
                System.out.println(monster.getMonsterName() + " lost " + calcRogueUltimate + " hp!");

            }
            currentPlayer.setResource(currentPlayer.getResource() - 100);
        } else {
            System.out.printf("The %s %s doesnt have enough energy to perform Fools elusiveness \n\n", currentPlayer.getClassName(), currentPlayer.getName());
        }
    }


    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {
        System.out.println(PlayerChoice.spellsRogue());
        int rogueSpells = Input.intInput();
        Random random = new Random();

        int randomMonster = random.nextInt(monsterList.size());
        int randomCurrency = random.nextInt(5,20);

            switch (rogueSpells) {
            case 1 -> {
                if (currentPlayer.getResource() >= 20) {
                    System.out.printf("Backstabs a random target, dealing: %s extra damage and gaining: %s extra defence \n", 3, 2);
                    monsterList.get(randomMonster).setHp(monsterList.get(randomMonster).getHp() - currentPlayer.getDamage() + 3);
                    currentPlayer.setDefence(currentPlayer.getDefence() + 3);
                } else {
                    System.out.println("The rogue doesnt have enough energy");
                }
            }
            case 2 -> {
                if (currentPlayer.getResource() >= 30) {
                    System.out.printf("Pick pockets the target gaining: %s gold", randomCurrency);
                    currentPlayer.setResource(currentPlayer.getResource() - 30);
                } else {
                    System.out.println("The rogue doesnt have enough energy");
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

        int rogueDamage = calculateDamage(playerList, currentPlayer, monsterList, monsterIndex);
        monsterList.get(monsterIndex).setHp(monsterList.get(monsterIndex).getHp() - rogueDamage);

        System.out.printf("The rogue attacks with a swift slash, Dealing %s to monster %s \n\n", rogueDamage, monsterList.get(monsterIndex).getMonsterName());
    }


    public int calculateDamage(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, int monsterIndex) {
        int calculateHeroDamage =
                currentPlayer.getDamage()
                + currentPlayer.getInventoryList().get(0).getDamage()
                + currentPlayer.getInventoryList().get(1).getDamage()
                + (currentPlayer.getAgility() / 7)
                + (currentPlayer.getStrength() / 10);

        return calculateHeroDamage;
    }

    @Override
    public String toString() {
        return
                Color.GREEN + className + Color.RESET + ":" +
                        "     Hp = " + hp + "  || " +
                        " Damage = " + damage + "  || " +
                        " Resource = " + resource + "  || " +
                        " Strength = " + strength + "  || " +
                        " Agility = " + agility + "  || " +
                        " Intellect = " + intellect + "  || " +
                        " Initiative = " + initiative + "  ||";
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
    public int getDefence() {
        return defence;
    }

    @Override
    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    @Override
    public int getMaxResource() {
        return maxResource;
    }

    @Override
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

    public int getdefence() {
        return defence;
    }

    public void setdefence(int defence) {
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
