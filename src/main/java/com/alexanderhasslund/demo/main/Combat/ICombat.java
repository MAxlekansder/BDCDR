package com.alexanderhasslund.demo.main.Combat;

import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public interface ICombat {

    public void attack(List<Player> playerList, Player player, List<Monster> monsterList, Monster monster);


}
