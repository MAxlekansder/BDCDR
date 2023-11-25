package com.alexanderhasslund.demo.main.Monster;

import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterBrute;
import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterRanger;
import com.alexanderhasslund.demo.main.Monster.BasicMonsters.MonsterSpellWeaver;
import com.alexanderhasslund.demo.main.Monster.Boss.LastBossThaal.Thaal;
import com.alexanderhasslund.demo.main.Monster.Boss.TagTeam.TagTeam;
import com.alexanderhasslund.demo.main.Monster.Boss.TheTwinBrothers.Bram;
import com.alexanderhasslund.demo.main.Monster.Boss.TheTwinBrothers.Ohrum;
import com.alexanderhasslund.demo.main.Monster.Boss.theInquisition.theInquisition;
import com.alexanderhasslund.demo.main.Monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterController {

    private List<Monster> monsterList;
    private int countFloors;
    private int countMonsters;
    private int countPlayers;


    public MonsterController(int countPlayers) {
        this.countPlayers = countPlayers;
        this.monsterList = new ArrayList<>();
    }


    public void monsterValueController() {
        Random rand = new Random();

        countMonsters = (getCountPlayers() + rand.nextInt(1,2));

        List<Monster> allMonsters = new ArrayList<>();
        allMonsters.add(new MonsterRanger());
        allMonsters.add(new MonsterBrute());
        allMonsters.add(new MonsterSpellWeaver());
        randomizeMonster(countMonsters, allMonsters);
    }


    public void randomizeMonster(int countMonsters, List<Monster> allMonsters) {
        Random rand = new Random();

        for (int i = 0; i < countMonsters; i++) {
            int monsterIndex = rand.nextInt(allMonsters.size());
            Monster firstMonster = allMonsters.get(monsterIndex);

            if (firstMonster instanceof MonsterBrute) {
                firstMonster = new MonsterBrute();
            } else if (firstMonster instanceof MonsterRanger) {
                firstMonster = new MonsterRanger();
            } else if (firstMonster instanceof MonsterSpellWeaver) {
                firstMonster = new MonsterSpellWeaver();
            }

            firstMonster.setMonsterId(i);
            monsterList.add(firstMonster);
        }
    }

    public void chooseBossFight(int calculateLevels) {

        switch(calculateLevels) {
            case 1 -> {generateTagTeamBoss();}
            case 2 -> {generateTwinBrotherBoss();}
            case 3 -> {generateTheInquisition();}
            case 4 -> {generateThall();}
            default -> {
                System.out.println("no input found");
            }
        }
    }

    public void generateThall() {

        monsterList.add(new Thaal());

    }

    public void generateTheInquisition() {

        monsterList.add(new theInquisition());
    }

    public void generateTagTeamBoss() {

        monsterList.add(new TagTeam());


    }

    public void generateTwinBrotherBoss() {

        monsterList.add(new Bram());
        monsterList.add(new Ohrum());

        for (Monster monster : monsterList) {
            monster.setMonsterId(+1);
        }
    }


    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public int getCountFloors() {
        return countFloors;
    }

    public void setCountFloors(int countFloors) {
        this.countFloors = countFloors;
    }

    public int getCountMonsters() {
        return countMonsters;
    }

    public void setCountMonsters(int countMonsters) {
        this.countMonsters = countMonsters;
    }

    public int getCountPlayers() {
        return countPlayers;
    }

}
