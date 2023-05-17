package com.kodilla;

public class NPC implements Player {
    private String name;
    private char symbol;

    public NPC(Player player) {
        this.name = "Computer";
        this.symbol = setOppositeSymbol(player);
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
        if (player.getSymbol() == 'x') {
            return 'o';
        } else if (player.getSymbol() == 'o') {
            return 'x';
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
