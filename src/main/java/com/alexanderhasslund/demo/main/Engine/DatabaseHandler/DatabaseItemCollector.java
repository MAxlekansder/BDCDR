package com.alexanderhasslund.demo.main.Engine.DatabaseHandler;

import com.alexanderhasslund.demo.main.Inventory.Inventory;
import com.alexanderhasslund.demo.main.Player.Player;
import com.alexanderhasslund.demo.main.Shop.Defence.Shields;
import com.alexanderhasslund.demo.main.Shop.Potions.Potion;
import com.alexanderhasslund.demo.main.Shop.Weapon.Swords;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseItemCollector {

    public void writingItem(Inventory inventory) {

        if (checkItemExists(inventory) == 0) {
            try (Connection connection = DatabaseConnector.getConnection()) {

                String addItem = "INSERT INTO dungeonrun.item (ItemName, ItemDamage, ItemInitiative, ItemLevelLock, ItemDefence, ItemBlock) VALUES (?,?,?,?,?,?)";

                try (PreparedStatement statement = connection.prepareStatement(addItem)) {

                    statement.setString(1, inventory.getItemName());
                    statement.setInt(2, inventory.getDamage());
                    statement.setInt(3, inventory.getBlock());
                    statement.setInt(4, inventory.getLevelLock());
                    statement.setInt(5, inventory.getDefence());
                    statement.setInt(6, inventory.getBlock());

                    int rowsAffected = statement.executeUpdate();

                } catch (SQLException e) {
                    DatabaseConnector.handleSQL(e);
                }
            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }
        }
    }

    public int checkItemExists(Inventory inventory) {

        int countTotalItem = 0;

        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkEmpty = "SELECT COUNT(*) FROM dungeonrun.Item where ItemName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkEmpty)) {

                statement.setString(1, inventory.getItemName());

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    countTotalItem = resultSet.getInt("count(*)");
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

        return countTotalItem;

    }

    public int checkItemId (Player player, int itemIndex) {

        int checkSword = 0;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkEmpty = "SELECT ItemId FROM dungeonrun.Item where ItemName = ?";

            try (PreparedStatement statement = connection.prepareStatement(checkEmpty)) {

                statement.setString(1, player.getInventoryList().get(itemIndex).getItemName());

                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    checkSword = resultSet.getInt("ItemId");
                }

            } catch (SQLException e) {
                DatabaseConnector.handleSQL(e);
            }

        } catch (SQLException e) {
            DatabaseConnector.handleSQL(e);
        }

        return checkSword;
    }

    public void writingItemSword(Swords swords) {
        writingItem(swords.standardSword());
        writingItem(swords.fastSword());
        writingItem(swords.sharpSword());
        writingItem(swords.divineSword());
    }

    public void writingItemShiled(Shields shields) {
        writingItem(shields.standardShield());
        writingItem(shields.spikedShield());
        writingItem(shields.bulkShield());
        writingItem(shields.divineShield());
    }

    public void writingItemPotion(Potion potion) {
        writingItem(potion.potionOfDefence());
        writingItem(potion.potionOfHaste());
        writingItem(potion.potionOfHealth());
    }
}
