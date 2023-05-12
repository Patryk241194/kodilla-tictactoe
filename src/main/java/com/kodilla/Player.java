package com.kodilla;

public interface Player {
    String getName();

    void setName(String name);

    char getSymbol();

    void setSymbol(char symbol);

    char setOppositeSymbol(Player player);
}