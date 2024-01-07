package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    @JsonIgnore
    private static final int width = 10;
    @JsonIgnore
    private static final int height = 40;

    private Item[][] items;

    private int player_id;

    @JsonCreator
    public Inventory(@JsonProperty("id") int playerId, @JsonProperty("items") Item[][] items) {
        this.player_id = playerId;
        this.items = items;
    }

    public Inventory(int playerId, Integer[] items_id) {
        this.player_id = playerId;
        setItemsId(items_id);
    }
    @JsonIgnore
    public void setPlayerId(int playerId) {
        this.player_id = playerId;
    }
    @JsonIgnore
    public int getPlayerId() {
        return player_id;
    }

    private Inventory(){}

    public void setItems(Item[][] items) {
        this.items = items;
    }

    public Item[][] getItems() {
        return items;
    }
    @JsonIgnore
    public void putItemInInventory(Item item, int x, int y) {
        item.setInventory_id(this.player_id);
        items[x][y] = item;
    }
    @JsonIgnore
    public Integer[] getItemsId() {
        Integer[] ids = new Integer[items.length * items[0].length];
        for (int i = 0; i < items.length; i++){
            for (int j = 0; j < items[0].length; j++) {
                if (this.items[i][j] == null) {
                    ids[i * this.items.length + j] = 0;
                }else {
                    ids[i * this.items.length + j] = this.items[i][j].getId();
                }
            }
        }
        return ids;
    }
    @JsonIgnore
    public void setItemsId(Integer[] input) {
        this.items = new Item[width][height];
        for (int i = 0; i < this.items.length; i++){
            for (int j = 0; j < this.items[0].length; j++) {
                if (input[i * this.items.length + j] == 0) {
                    this.items[i][j] = null;
                }
                if (input[i * this.items.length + j] <= 80) {
                    this.putItemInInventory(Item.items[input[i * this.items.length + j]], i, j);
                } if (input[i * this.items.length + j] <= 180) {
                    this.putItemInInventory(Item.weapons[input[i * this.items.length + j]], i, j);
                } else {
                    this.putItemInInventory(Item.backpacks[input[i * this.items.length + j]], i, j);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                if (items[i][j] != null) {
                    sb.append(items[i][j].toString()).append(", ");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append('}');

        return sb.toString();
    }
}
