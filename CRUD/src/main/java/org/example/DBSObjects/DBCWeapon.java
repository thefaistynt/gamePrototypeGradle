package org.example.DBSObjects;

import org.example.Item;
import org.example.Player;
import org.example.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBCWeapon extends DBCClass {
    public DBCWeapon(Connection connection) {
        super(connection);
    }

    public void addWeapon(Player player, int position) throws Exception {
        try {
            Weapon weapon = player.getWeapons()[position];
        } catch (Exception e) {
            throw new Exception("Weapon index out of bounds");
        }
        addWeapon(player, player.getWeapons()[position], position);
    }

    public void addWeapon(Player player, Weapon weapon, int position) throws Exception {
        addWeapon(player.getId(), weapon, position);
    }

    public void addWeapon(int player_id, Weapon weapon, int position) throws Exception {
        if (weapon == null) {
            return;
        }
        String sql = "INSERT INTO public.\"Weapon\" (player_id, item_id, damage, position) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player_id);
            statement.setInt(2, weapon.getId());
            statement.setDouble(3, weapon.getDamage());
            statement.setInt(4, position);
            statement.executeUpdate();
            System.out.printf("Weapon%d with playerId = %d created\n", position+1, player_id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void addWeapons(Player player) throws Exception {
        addWeapon(player, 0);
        addWeapon(player, 1);
    }

    public void deleteWeapon(Player player, int position) throws Exception {
        try {
            Weapon weapon = player.getWeapons()[position];
            this.deleteWeapon(weapon, position);
        } catch (Exception e) {
            throw new Exception("Weapon index out of bounds");
        }
        deleteWeapon(player.getId(), position);
    }

    public void deleteWeapon(int player_id, int position) throws Exception {
        String sql = "DELETE FROM public.\"Weapon\" WHERE player_id = ?, position = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player_id);
            statement.setInt(2, position);
            statement.executeUpdate();
            System.out.printf("Inventory with id = %d removed", player_id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteWeapon(Weapon weapon, int position) throws Exception {
        deleteWeapon(weapon.getPlayer_id(), position);
    }

    public void updateWeaponsDamage(Player player) throws SQLException {
        String sql = "UPDATE public.\"Weapon\" SET damage = ? WHERE player_id = ?, item_id = ?, position = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setDouble(1, player.getWp1().getDamage());
            statement.setInt(2, player.getWp1().getPlayer_id());
            statement.setInt(3, player.getWp1().getId());
            statement.setDouble(4, 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "UPDATE public.\"Weapon\" SET damage = ? WHERE player_id = ?, item_id = ?, position = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setDouble(1, player.getWp2().getDamage());
            statement.setInt(2, player.getWp2().getPlayer_id());
            statement.setInt(3, player.getWp2().getId());
            statement.setDouble(4, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWeaponDamage(Weapon weapon, int position, double newDamage) throws SQLException {
        String sql = "UPDATE public.\"Weapon\" SET damage = ? WHERE player_id = ?, item_id = ?, position = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setDouble(1, newDamage);
            statement.setInt(2, weapon.getPlayer_id());
            statement.setInt(3, weapon.getId());
            statement.setDouble(4, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Weapon[] findWeaponsByPlayer(Player player) {
        return findWeaponsByPlayerId(player.getId());
    }

    public Weapon[] findWeaponsByPlayerId(int playerId) {
        String sql = "SELECT * FROM public.\"Weapon\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            Weapon[] weapons = new Weapon[]{null, null};
            while (resultSet.next()) {
                Weapon item = Weapon.weapons[resultSet.getInt(2) - Item.items.length];
                Weapon weapon = new Weapon(item);
                weapon.setDamage(resultSet.getDouble(3));
                weapon.setPlayer_id(resultSet.getInt(1));
                weapons[resultSet.getInt(4)] = weapon;
            }
            return weapons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Weapon findWeaponByPlayerAndPosition(Player player, int position) {
        return findWeaponByPlayerIdAndPosition(player.getId(), position);
    }

    public Weapon findWeaponByPlayerIdAndPosition(int playerId, int position) {
        String sql = "SELECT * FROM public.\"Weapon\" WHERE player_id = ?, position = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            statement.setInt(2, position);
            ResultSet resultSet = statement.executeQuery();
            Weapon item = Weapon.weapons[resultSet.getInt(2)];
            Weapon weapon = new Weapon(item);
            weapon.setDamage(resultSet.getDouble(3));
            weapon.setPlayer_id(playerId);
            return weapon;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWeapons(int player_id) throws SQLException {
        String sql = "DELETE FROM public.\"Weapon\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player_id);
            statement.executeUpdate();
            System.out.printf("Inventory with id = %d removed", player_id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Weapon[]> getAll() throws SQLException {
        String sql = "SELECT * FROM public.\"Weapon\"";
        Map<Integer, Weapon[]> weapons = new HashMap<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int playerId = resultSet.getInt(1);
                int itemId = resultSet.getInt(2);
                double damage = resultSet.getDouble(3);
                int position = resultSet.getInt(4);
                if (!weapons.containsKey(playerId)) {
                    weapons.put(playerId, new Weapon[2]);
                }
                weapons.get(playerId)[position] = new Weapon(Weapon.weapons[itemId - Item.items.length - 1]);
            }
        }

        List<Weapon[]> result = new ArrayList<>();
        for (int i  : weapons.keySet()) {
            result.add(i, weapons.get(i));
        }
        return result;
    }
}
