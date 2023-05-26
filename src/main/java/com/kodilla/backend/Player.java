package com.kodilla.backend;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private transient char symbol;

    public Player(String name, char userSymbol) {
        this.name = name;
        this.symbol = userSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char setOppositeSymbol(Player player) {
        if (player.getSymbol() == Symbol.X) {
            return Symbol.O;
        } else if (player.getSymbol() == Symbol.O) {
            return Symbol.X;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;

        return getName().equals(player.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}


