package org.example.DBSObjects;

import org.example.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCInventory extends DBCClass {

    public DBCInventory(Connection connection) {
        super(connection);
    }

    public void addInventory(Player player) throws SQLException {
        addInventory(player.getInventory());
    }

    public void addInventory(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO public.\"Inventory\" (player_id, items_id) VALUES (?, ?)";
        Integer[] arrayInt = inventory.getItemsId();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, inventory.getPlayerId());
            statement.setArray(2, getConnection().createArrayOf("integer", arrayInt));
            statement.executeUpdate();
            System.out.printf("Inventory with playerId = %d created\n", inventory.getPlayerId());
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteInventory(Player player) throws SQLException {
        this.deleteInventory(player.getInventory());
    }

    public void deleteInventory(Inventory inventory) throws SQLException {
        this.deleteInventory(inventory.getPlayerId());
    }

    public void deleteInventory(int player_id) throws SQLException {
        String sql = "DELETE FROM public.\"Inventory\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player_id);
            statement.executeUpdate();
            System.out.printf("Inventory with id = %d removed", player_id);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateInventory(Inventory inventory) throws SQLException {
        String sql = "UPDATE public.\"Inventory\" SET items_id = ? WHERE player_id = ?";
        Integer[] arrayInt = inventory.getItemsId();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setArray(1, getConnection().createArrayOf("integer", arrayInt));
            statement.setInt(2, inventory.getPlayerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Inventory findInventoryByPlayer(Player player) {
        return findInventoryByPlayerId(player.getId());
    }

    public Inventory findInventoryByPlayerId(int playerId) {
        String sql = "SELECT * FROM public.\"Inventory\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer[] itemsIds = (Integer[]) resultSet.getArray("itemId").getArray();
                return new Inventory(playerId, itemsIds);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Inventory> getAll() throws SQLException {
        String sql = "SELECT * FROM public.\"Inventory\"";
        List<Inventory> inventories = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                Integer[] itemsId = (Integer[]) resultSet.getArray("items_id").getArray();
                Inventory inventory = new Inventory(playerId, itemsId);
                inventories.add(inventory);
            }
        }
        return inventories;
    }
}
