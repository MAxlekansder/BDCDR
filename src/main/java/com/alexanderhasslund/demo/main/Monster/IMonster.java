package com.alexanderhasslund.demo.main.Monster;

import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public interface IMonster {

    public void spells(List<Player> playerList, Player player, List<Monster> monsterList, Monster monster);

}
