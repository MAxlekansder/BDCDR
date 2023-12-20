package com.alexanderhasslund.demo.main.Classes;
import com.alexanderhasslund.demo.main.Monster.Monster;
import com.alexanderhasslund.demo.main.Player.Player;

import java.util.List;

public interface IClasses {

    public int calculateDamage(List<Player> playerList, Player currentPlayer, List<Monster> monsterList, int monsterIndex);
    public void ultimate(List<Player> playerList, Player player, List<Monster> monsterList, int calculateLevel, int countRounds, String battleId);
    public void spells(List<Player> playerList, Player player, List<Monster> monsterList,  int calculateLevel, int countRounds, String battleId);
    public void setLevelUp(Player player);
    public void addStatsToPlayer(Player player);

}
