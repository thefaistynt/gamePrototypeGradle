package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Backpack extends Item {
    @JsonProperty("slots")
    public Item[][] slots;
    private Backpack(){}
    private int weight;
    private int height;

    @JsonCreator
    public Backpack(@JsonProperty("slots") Item[][] slots, @JsonProperty("weight") int weight, @JsonProperty("height") int height) {
        this.weight = weight;
        this.height = height;
        this.slots = slots;
    }

    public Backpack(String name, int weight, int height) {
        super(name);
        this.weight = weight;
        this.height = height;
    }

    public Backpack(Backpack backpack) {
        super(backpack);
        this.weight = backpack.weight;
        this.height = backpack.height;
        this.slots = new Item[this.weight][this.height];
    }


    public void setSlots(Item[][] slots) {
        this.slots = slots;
    }

    public Item[][] getSlots() {
        return slots;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    @JsonIgnore

    public void putItemInBackpack(Item item, int x, int y) {
        item.setBackpack_id(this.getId());
        slots[x][y] = item;
    }
    @JsonIgnore
    public Integer[] getSlotsIds() {
        System.out.println("slots -> " + this.weight + " - " + this.slots.length + "; " + height + " - " + this.slots[0].length);
        Integer[] ids = new Integer[this.slots.length * this.slots[0].length];
        for (int i = 0; i < this.slots.length; i++){
            for (int j = 0; j < this.slots[0].length; j++) {
                if (this.slots[i][j] == null) {
                    ids[i * this.slots[0].length + j] = 0;
                }else {
                    ids[i * this.slots[0].length + j] = this.slots[i][j].getId();
                }
            }
        }
        return ids;
    }
    @JsonIgnore
    public void setSlotsIds(Integer[] ids) {
        this.slots = new Item[this.weight][this.height];
        for (int i = 0; i < this.slots.length; i++){
            for (int j = 0; j < this.slots[0].length; j++) {
                if (ids[i * this.weight + j] == 0) {
                    this.slots[i][j] = null;
                }
                if (ids[i * this.weight + j] <= 80) {
                    this.putItemInBackpack(Item.items[ids[i * this.weight + j]], i, j);
                } if (ids[i * this.weight + j] <= 180) {
                    this.putItemInBackpack(Item.weapons[ids[i * this.weight + j]], i, j);
                } else {
                    this.putItemInBackpack(Item.backpacks[ids[i * this.weight + j]], i, j);
                }
            }
        }
    }
    public int getWeight() {
        return weight;
    }
    public int getHeight() {
        return height;
    }
}
