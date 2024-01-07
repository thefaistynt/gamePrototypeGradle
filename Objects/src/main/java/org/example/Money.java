package org.example;

public class Money {
    private int playerId;
    private int dollars;
    private int euro;
    private int rubles;

    private Money(){}

    public Money(int playerId, int dollars, int euro, int rubles) {
        this.playerId = playerId;
        this.dollars = dollars;
        this.euro = euro;
        this.rubles = rubles;
    }
    @Override
    public String toString() {
        return "("+this.rubles+", "+this.dollars+", "+this.euro+")";
    }


    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public void setEuro(int euro) {
        this.euro = euro;
    }

    public void setId(int playerId) {
        this.playerId = playerId;
    }

    public int getId() {
        return playerId;
    }

    public void setRubles(int rubles) {
        this.rubles = rubles;
    }

    public int getDollars() {
        return dollars;
    }

    public int getEuro() {
        return euro;
    }

    public int getRubles() {
        return rubles;
    }
}
