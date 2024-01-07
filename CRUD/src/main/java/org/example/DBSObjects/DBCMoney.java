package org.example.DBSObjects;

import org.example.Inventory;
import org.example.Money;
import org.example.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCMoney extends DBCClass {
    public DBCMoney(Connection connection) {
        super(connection);
    }

    public void addMoney(Player player) throws SQLException {
        addMoney(player.getMoney());
    }
    public void addMoney(Money money) throws SQLException {
        String sql = "INSERT INTO public.\"Money\" (player_id, dollars, euro, rubles) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, money.getId());
            statement.setInt(2, money.getDollars());
            statement.setInt(3, money.getEuro());
            statement.setInt(4, money.getRubles());
            statement.executeUpdate();
            System.out.printf("Money with playerId = %d created", money.getId());
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteMoney(Player player) throws SQLException {
        this.deleteMoney(player.getMoney().getId());
    }

    public void deleteMoney(Money money) throws SQLException {
        this.deleteMoney(money.getId());
    }

    public void deleteMoney(int playerId) throws SQLException {
        String sql = "DELETE FROM public.\"Money\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            statement.executeUpdate();
            System.out.printf("Money with id = %d removed", playerId);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateMoney(Money money) throws SQLException {
        String sql = "UPDATE public.\"Money\" SET dollars = ?, euro = ?, rubles = ? WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, money.getDollars());
            statement.setInt(2, money.getEuro());
            statement.setInt(3, money.getRubles());
            statement.setInt(4, money.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Money findMoneyByPlayer(Player player) {
        return findMoneyByPlayerId(player.getId());
    }

    public Money findMoneyByPlayerId(int playerId) {
        String sql = "SELECT * FROM public.\"Money\" WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int dollars = resultSet.getInt(2);
                int euro = resultSet.getInt(3);
                int rubles = resultSet.getInt(4);
                return new Money(playerId, dollars, euro, rubles);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Money> getAll() throws SQLException {
        String sql = "SELECT * FROM public.\"Inventory\"";
        List<Money> monies = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                int dollars = resultSet.getInt(2);
                int euro = resultSet.getInt(3);
                int rubles = resultSet.getInt(4);
                Money money = new Money(playerId, dollars, euro, rubles);
                monies.add(money);
            }
        }
        return monies;
    }
}
