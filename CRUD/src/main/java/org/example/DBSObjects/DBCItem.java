package org.example.DBSObjects;

import org.example.Backpack;
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

public class DBCItem extends DBCClass {
    public DBCItem(Connection connection) {
        super(connection);
    }

    public void addItem(Item item) throws Exception {
        String sql = "INSERT INTO public.\"Items\" (id, name) VALUES (?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, item.getId());
            statement.setString(2, item.getName());
            statement.executeUpdate();
            System.out.printf("Item with id = %d added", item.getId());
        } catch (Exception e) {
            throw e;
        }
    }

    public Item findItemById(int item_id) throws SQLException {
        String sql = "SELECT * FROM public.\"Items\" WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, item_id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            Item item = null;
            if (id <= 80) {
                item = new Item(Item.items[id-1]);
            } else if (id <= 180) {
                item = new Weapon(Item.weapons[id-80-1]);
            } else if (id <= 190) {
                item = new Backpack(Item.backpacks[id-180-1]);
            }
            assert item != null;
            System.out.printf("Item with id = %d founded", item.getId());
            return item;
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateItem(int item_id, String name) {
        String sql = "UPDATE public.\"Backpack\" SET name = ? WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, item_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(int item_id) {
        String sql = "DELETE FROM public.\"Backpack\" WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, item_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getAll() throws SQLException {
        List<Item> result = new ArrayList<>();
        String sql = "SELECT * FROM public.\"Items\"";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Item item = null;
                if (id <= 80) {
                    item = new Item(Item.items[id - 1]);
                } else if (id <= 180) {
                    item = new Weapon(Item.weapons[id - 80 - 1]);
                } else if (id <= 190) {
                    item = new Backpack(Item.backpacks[id - 180 - 1]);
                }
                assert item != null;
                result.add(item);
            }
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

}
