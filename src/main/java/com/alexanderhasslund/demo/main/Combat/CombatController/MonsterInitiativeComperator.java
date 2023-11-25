package com.alexanderhasslund.demo.main.Combat.CombatController;

import com.alexanderhasslund.demo.main.Monster.Monster;

import java.util.Comparator;

public class MonsterInitiativeComperator implements Comparator<Monster> {
    @Override
    public int compare(Monster o1, Monster o2) {
        return o1.getInitiative() - o2.getInitiative();
    }
}
