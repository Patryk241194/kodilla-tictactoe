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

    public char getSymbol() {
        return symbol;
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


