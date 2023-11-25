package com.alexanderhasslund.demo.main.Combat.CombatController;

import com.alexanderhasslund.demo.main.Player.Player;
import java.util.Comparator;

public class PlayerInitiativeComperator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        return o1.getInitiative() - o2.getInitiative();
    }
}
