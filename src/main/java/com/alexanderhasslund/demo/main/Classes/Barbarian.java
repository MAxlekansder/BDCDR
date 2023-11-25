package com.alexanderhasslund.demo.main.Classes;
import com.alexanderhasslund.demo.main.Combat.ICombat;
import com.alexanderhasslund.demo.main.Engine.Color;
import com.alexanderhasslund.demo.main.Engine.Input;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.PlayerInteraction.PlayerChoice;

import java.io.Serializable;
import java.util.List;


public class Barbarian extends Player implements IClasses, ICombat, Serializable {

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


    public Barbarian() {
        //Player barbarian = new Player(getName(),getTalentTreeList(),getInventoryList(),0,0,"BARBARIAN",115,20,100,22,8,0,0,10,55);
        //return barbarian;
        this.className = Color.RED + "BARBARIAN" + Color.RESET;
        this.maxHp = 115;
        this.hp = 115;
        this.id = 0;
        this.damage = 20;
        this.resource = 100;
        this.maxResource = 100;
        this.strength = 22;
        this.agility = 8;
        this.intellect = 0;
        this.level = 1;
        this.defence = 15;
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

    public void addStatsToPlayer(Player currentPlayer) {


        currentPlayer.setBaseStrength(currentPlayer.getBaseStrength() + (int)(currentPlayer.getLevel() / 4));
        currentPlayer.setBaseAgility(currentPlayer.getBaseAgility() + (int)(currentPlayer.getLevel() / 4));
        currentPlayer.setBaseIntellect(currentPlayer.getBaseIntellect() + (int)(currentPlayer.getLevel() / 2.2));

        currentPlayer.setBaseDamage(currentPlayer.getBaseDamage() + (int)(currentPlayer.getBaseStrength() / 10));
        currentPlayer.setBaseDefence(currentPlayer.getBaseDefence() + (int) (currentPlayer.getBaseStrength()/ 7));
        currentPlayer.setMaxHp(currentPlayer.getMaxHp() + (int) (currentPlayer.getBaseStrength() * 0.5));

    }



    @Override
    public void ultimate(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {
        int monsterChoice = 1;

        for (Monster monster1 : monsterList) {
            System.out.println("CHOICE :" + monsterChoice+ " " + monster1);
            monsterChoice++;
        }
        System.out.print("Decide what monster you want to hit: ");
        int monsterIndex = Input.intInput() -1;

        int calcBarbarianUltimate = ((monsterList.get(monsterIndex).getHp() * 100) / currentPlayer.getHp());

        if (currentPlayer.getResource() >= 100) {
            if (calcBarbarianUltimate <= 25) {
                System.out.printf("\nThe barbarian executes %s, to even out the odds \n", monsterList.get(monsterIndex).getMonsterName());
                monsterList.get(monsterIndex).setHp(0);
                currentPlayer.setResource(currentPlayer.getResource() - 100);
            } else {
                System.out.printf("\nThe barbarian tried to execute %s, but failed\n", monsterList.get(monsterIndex).getMonsterName());
                System.out.println("No rage was spent");
            }

        } else {
            System.out.printf("%s doesnt have enough rage to perform berserkers rage\n", currentPlayer.getClassName());
        }

    }


    @Override
    public void spells(List<Player> playerList, Player currentPlayer, List<Monster> monsterList) {
        System.out.println(PlayerChoice.spellsBarbarian());
        int barbarianSpells = Input.intInput();
        boolean isSpell = true;

        while (isSpell) {
            switch (barbarianSpells) {
                case 1 -> { // a baseline damage spell that adds 3 damage and uses 'resources'
                    if (currentPlayer.getResource() >= 20) {
                        System.out.printf("%s crys a war cry, increasing the entire groups strength by 10 \n", currentPlayer.getClassName(), 10);
                        for (Player player : playerList) {
                            player.setStrength(player.getStrength() + 10);
                        }
                        currentPlayer.setResource(currentPlayer.getResource() - 20);
                        isSpell = false;
                    } else {
                        System.out.println("The barbarian doesnt enough rage");
                    }

                }
                case 2 -> {
                    if (currentPlayer.getResource() >= 10) {
                        System.out.println("The barbarian muster its rage, gaining defence euqal to: " + (defence + 2));
                        currentPlayer.setDefence(currentPlayer.getDefence() + 10);
                        currentPlayer.setResource(currentPlayer.getResource() - 10);
                        isSpell = false;
                    }
                    else {
                        System.out.println("The barbarian doesnt enough rage");
                    }
                }
                    default -> {
                        System.out.println("Use right input");
                    }
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

        int barbarianDamage = calculateDamage(playerList, currentPlayer, monsterList, monsterIndex);
        monsterList.get(monsterIndex).setHp(monsterList.get(monsterIndex).getHp() -  barbarianDamage);

        System.out.printf("\nThe barbarian attacks with a hard hitting strike, Dealing %s to monster %s \n", barbarianDamage, monsterList.get(monsterIndex).getMonsterName());
    }

    public int calculateDamage(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, int monsterIndex) {
        int calculateHeroDamage =
                currentPlayer.getDamage()
                + currentPlayer.getInventoryList().get(0).getDamage()
                + currentPlayer.getInventoryList().get(1).getDamage()
                + (currentPlayer.getStrength() / 7);

        return calculateHeroDamage;
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
    public int getMaxResource() {
        return maxResource;
    }

    @Override
    public void setMaxResource(int maxResource) {
        this.maxResource = maxResource;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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


