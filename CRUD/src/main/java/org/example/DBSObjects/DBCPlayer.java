package org.example.DBSObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.*;

public class DBCPlayer extends DBCClass {

    public DBCPlayer(Connection connection) {
        super(connection);
    }

    public void addPlayer(Player player) throws Exception {
        String sql = "INSERT INTO public.\"Player\" (id, nickname) VALUES (?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player.getId());
            statement.setString(2, player.getNickname());
            statement.executeUpdate();
            System.out.println("Player created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (player.getInventory() != null) {
            DBCInventory inventory = new DBCInventory(getConnection());
            inventory.addInventory(player);
        }
        if (player.getWeapons()[0] != null) {
            DBCWeapon weapon = new DBCWeapon(getConnection());
            weapon.addWeapons(player);
        }
        if (player.getBackpack() != null) {
            DBCBackpack backpack = new DBCBackpack(getConnection());
            backpack.addBackpack(player);
        }
        if (player.getMoney() != null) {
            DBCMoney money = new DBCMoney(getConnection());
            money.addMoney(player);
        }
    }

    public Player findPlayerById(String name) {
        String sql = "SELECT * FROM public.\"Player\" WHERE nickname = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            int id = resultSet.getInt(1);
            String nickname = resultSet.getString(2);
            DBCWeapon weapon = new DBCWeapon(getConnection());
            Weapon[] weapons = weapon.findWeaponsByPlayerId(id);
            DBCInventory dbcInventory = new DBCInventory(getConnection());
            Inventory inventory = dbcInventory.findInventoryByPlayerId(id);
            DBCBackpack dbcBackpack = new DBCBackpack(getConnection());
            Backpack backpack = dbcBackpack.findBackpackByPlayerId(id);
            DBCMoney dbcMoney = new DBCMoney(getConnection());
            Money money = dbcMoney.findMoneyByPlayerId(id);
            Player player = new Player(nickname, weapons[0], weapons[1], backpack, inventory, money);
            System.out.println("Player getted");
            return player;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Player findPlayerById(int playerId) {
        String sql = "SELECT * FROM public.\"Player\" WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            String nickname = null;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                nickname = resultSet.getString(2);
            }
            DBCWeapon weapon = new DBCWeapon(getConnection());
            Weapon[] weapons = weapon.findWeaponsByPlayerId(id);
            DBCInventory dbcInventory = new DBCInventory(getConnection());
            Inventory inventory = dbcInventory.findInventoryByPlayerId(id);
            DBCBackpack dbcBackpack = new DBCBackpack(getConnection());
            Backpack backpack = dbcBackpack.findBackpackByPlayerId(id);
            DBCMoney dbcMoney = new DBCMoney(getConnection());
            Money money = dbcMoney.findMoneyByPlayerId(id);
            Player player = new Player(nickname, weapons[0], weapons[1], backpack, inventory, money);
            System.out.println("Player getted");
            return player;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePlayer(Player player) throws SQLException {
        String sql = "UPDATE public.\"Player\" SET nickname = ? WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player.getId());
            statement.setString(2, player.nickname);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePlayerById(int playerId) throws SQLException {
        String sql = "DELETE FROM public.\"Player\" WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            statement.executeUpdate();
            System.out.println("Player deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DBCInventory dbcInventory = new DBCInventory(getConnection());
        dbcInventory.deleteInventory(playerId);
        DBCMoney dbcMoney = new DBCMoney(getConnection());
        dbcMoney.deleteMoney(playerId);
        DBCWeapon dbcWeapon = new DBCWeapon(getConnection());
        dbcWeapon.deleteWeapons(playerId);
        DBCBackpack dbcBackpack = new DBCBackpack(getConnection());
        dbcBackpack.deleteBackpack(playerId);
    }

    @Override
    public List<Player> getAll() throws SQLException {
        String sql = "SELECT * FROM players";
        List<Player> players = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nickname = resultSet.getString(2);
                DBCWeapon weapon = new DBCWeapon(getConnection());
                Weapon[] weapons = weapon.findWeaponsByPlayerId(id);
                DBCInventory dbcInventory = new DBCInventory(getConnection());
                Inventory inventory = dbcInventory.findInventoryByPlayerId(id);
                DBCBackpack dbcBackpack = new DBCBackpack(getConnection());
                Backpack backpack = dbcBackpack.findBackpackByPlayerId(id);
                DBCMoney dbcMoney = new DBCMoney(getConnection());
                Money money = dbcMoney.findMoneyByPlayerId(id);
                Player player = new Player(nickname, weapons[0], weapons[1], backpack, inventory, money);
                players.add(player);
            }
            return players;
        } catch (Exception e) {
            throw e;
        }
    }


}
