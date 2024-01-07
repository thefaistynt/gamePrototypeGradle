package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Weapon extends Item {

    public double damage;

    private Weapon() {}

    public Weapon(String name, double damage) {
        super(name);
        this.damage = damage;
    }

    public Weapon(Weapon weapon) {
        super(weapon);
        this.damage = weapon.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "("+super.toString()+", "+this.damage+")";
    }
}
