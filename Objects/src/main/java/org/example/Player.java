package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    @JsonProperty("id")
    public static int next_id = 1;
    public int id;
    @JsonProperty("nickname")
    public String nickname;
    @JsonProperty("Weapons")
    private Weapon[] weapons;
    @JsonProperty("Backpack")
    private Backpack backpack;
    @JsonProperty("Inventory")
    private Inventory inventory;
    @JsonProperty("Money")
    private Money money;

    private Player() {}

    public Player(String nickname, Weapon wp1, Weapon wp2, Backpack backpack, Inventory inventory, Money money) {
        this.id = next_id++;
        this.nickname = nickname;
        this.weapons = new Weapon[] {wp1, wp2};
        this.backpack = backpack;
        this.inventory = inventory;
        this.money = money;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString(){
        return "id: " + this.id + ", nickname: " + this.nickname + ", wp1: " + this.weapons[0].toString() + ", wp2: " + this.weapons[1].toString() + ", backpack: " + this.backpack.toString() + ", inv: " + this.inventory.toString() + ", money: " + this.money.toString();
    }
    @JsonIgnore
    public void setWp1(Weapon wp1) {
        this.weapons[0] = wp1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeapons(Weapon[] weapons) {
        this.weapons = weapons;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    @JsonIgnore
    public void setWp2(Weapon wp2) {
        this.weapons[1] = wp2;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public Weapon getWp1() {
        return this.weapons[0];
    }
    @JsonIgnore
    public Weapon getWp2() {
        return this.weapons[1];
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }
}
