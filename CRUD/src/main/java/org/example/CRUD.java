package org.example;

import org.example.DBSObjects.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CRUD {
    private final DBCPlayer dbcplayer;
    private final DBCBackpack dbcbackpack;
    private final DBCInventory dbcinventory;
    private final DBCWeapon dbcweapon;
    private final DBCMoney dbcmoney;
    private final DBCItem dbcitem;

    public CRUD(Connection connection) throws SQLException {
        dbcplayer = new DBCPlayer(connection);
        dbcbackpack = new DBCBackpack(connection);
        dbcinventory = new DBCInventory(connection);
        dbcweapon = new DBCWeapon(connection);
        dbcmoney = new DBCMoney(connection);
        dbcitem = new DBCItem(connection);
    }

    public void addPlayer(Player player) throws Exception {
        dbcplayer.addPlayer(player);
    }

    public void addItem(Item item) throws Exception {
        dbcitem.addItem(item);
    }

    public void addBackpack(Backpack backpack) throws Exception {
        dbcbackpack.addBackpack(backpack);
    }

    public void addMoney(Money money) throws SQLException {
        dbcmoney.addMoney(money);
    }

    public void addInventory(Inventory inventory) throws SQLException {
        dbcinventory.addInventory(inventory);
    }

    public void addWeapon(Player player, Weapon weapon, int position) throws Exception {
        dbcweapon.addWeapon(player, weapon, position);
    }

    public Player findPlayer(int playerId) {
        return dbcplayer.findPlayerById(playerId);
    }

    public Player findPlayer(String name) {
        return dbcplayer.findPlayerById(name);
    }

    public Weapon[] findWeapons(int playerId) {
        return dbcweapon.findWeaponsByPlayerId(playerId);
    }

    public Inventory findInventory(int playerId) {
        return dbcinventory.findInventoryByPlayerId(playerId);
    }

    public Item findItemById(int itemId) throws SQLException {
        return dbcitem.findItemById(itemId);
    }

    public Money findMoney(int playerId) {
        return dbcmoney.findMoneyByPlayerId(playerId);
    }

    public void updatePlayer(Player player, String newName) throws SQLException {
        player.nickname = newName;
        dbcplayer.updatePlayer(player);
    }

    public void updatePlayer(Player player, Inventory inventory) throws SQLException {
        player.setInventory(inventory);
        inventory.setPlayerId(player.getId());
        dbcplayer.updatePlayer(player);
    }

    public void updatePlayer(Player player, Weapon weapon, int position) throws SQLException {
        switch (position) {
            case 0:
                player.setWp1(weapon);
                break;
            case 1:
                player.setWp2(weapon);
                break;
        }
        weapon.setPlayer_id(player.getId());
        dbcplayer.updatePlayer(player);
    }

    public void updatePlayer(Player player, Money money) throws SQLException {
        money.setId(player.getId());
        player.setMoney(money);
        dbcplayer.updatePlayer(player);
    }

    public void updatePlayer(Player player, Backpack backpack) throws SQLException {
        backpack.setPlayer_id(player.getId());
        player.setBackpack(backpack);
        dbcplayer.updatePlayer(player);
    }

    public void updateItem(Item item, String newName) {
        item.name = newName;
        dbcitem.updateItem(item.getId(), newName);
    }

    public void deletePlayer(int playerId) throws SQLException {
        dbcplayer.removePlayerById(playerId);
    }

    public void deleteInventoryById(int player_id) throws SQLException {
        dbcinventory.deleteInventory(player_id);
    }

    public void deleteWeapon(int player_id, int position) throws Exception {
        dbcweapon.deleteWeapon(player_id, position);
    }

    public void deleteItem(int item_id) throws SQLException {
        dbcitem.deleteItem(item_id);
    }

    public List<Player> getAllPlayers() throws SQLException {
        return dbcplayer.getAll();
    }

    public List<Item> getAllItems() throws SQLException {
        return dbcitem.getAll();
    }

    public List<Money> getAllMoneys() throws  SQLException {
        return dbcmoney.getAll();
    }

    public List<Weapon[]> getAllWeapons() throws SQLException {
        return dbcweapon.getAll();
    }

    public List<Inventory> getAllInventories() throws SQLException {
        return dbcinventory.getAll();
    }

    public List<Backpack> getAllBackpacks() throws SQLException {
        return dbcbackpack.getAll();
    }
}
