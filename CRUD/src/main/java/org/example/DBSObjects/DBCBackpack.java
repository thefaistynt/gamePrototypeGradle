package org.example.DBSObjects;

import org.example.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBCBackpack extends DBCClass {
    public DBCBackpack(Connection connection) {
        super(connection);
    }

    public void addBackpack(Player player) throws Exception {
        addBackpack(player.getBackpack());
    }

    public void addBackpack(Backpack backpack) throws Exception {
        String sql = "INSERT INTO public.\"Backpack\" (player_id, item_id, slots) VALUES (?, ?, ?)";
        Integer[] arrayInt = backpack.getSlotsIds();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, backpack.getPlayer_id());
            statement.setInt(2, backpack.getId());
            statement.setArray(3, getConnection().createArrayOf("integer", arrayInt));
            statement.executeUpdate();
            System.out.printf("Backpack with playerId = %d created", backpack.getPlayer_id());
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteBackpack(Player player) throws SQLException {
        this.deleteBackpack(player.getBackpack().getPlayer_id());
    }

    public void deleteBackpack(Backpack backpack) throws SQLException {
        deleteBackpack(backpack.getPlayer_id());
    }

    public void deleteBackpack(int player_id) throws SQLException {
        String sql = "DELETE FROM public.\"Backpack\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player_id);
            statement.executeUpdate();
            System.out.printf("Inventory with id = %d removed", player_id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateBackpack(Backpack backpack) throws SQLException {
        String sql = "UPDATE public.\"Backpack\" SET slots = ? WHERE player_id = ?";
        Integer[] arrayInt = backpack.getSlotsIds();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setArray(1, getConnection().createArrayOf("integer", arrayInt));
            statement.setInt(2, backpack.getPlayer_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Backpack findBackpackByPlayer(Player player) {
        return findBackpackByPlayerId(player.getId());
    }

    public Backpack findBackpackByPlayerId(int playerId) {
        String sql = "SELECT * FROM public.\"Backpack\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int item_id = resultSet.getInt("item_id");
                Integer[] itemsIds = (Integer[]) resultSet.getArray("slots").getArray();
                Backpack backpack = new Backpack(Item.backpacks[item_id - Item.items.length - Item.weapons.length]);
                backpack.setSlotsIds(itemsIds);
                return backpack;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Backpack> getAll() throws SQLException {
        String sql = "SELECT * FROM public.\"Backpack\"";
        List<Backpack> backpacks = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                int itemId = resultSet.getInt("item_id");
                Integer[] itemsId = (Integer[]) resultSet.getArray("slots").getArray();
                Backpack backpack = new Backpack(Item.backpacks[itemId - Item.items.length - Item.weapons.length]);
                backpack.setPlayer_id(playerId);
                backpack.setSlotsIds(itemsId);
                backpacks.add(backpack);
            }
        }
        return backpacks;
    }
}
