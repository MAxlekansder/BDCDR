package com.alexanderhasslund.demo.main.Combat.CombatController;

import java.util.Comparator;

public class CombatViewInitiativComperator implements Comparator<InitiativeListView> {


    @Override
    public int compare(InitiativeListView o1, InitiativeListView o2) {

        return o1.getInitiative() - o2.getInitiative();

    }
}
