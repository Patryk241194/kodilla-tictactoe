package com.kodilla;

public class Player {

    private String username;
    private char userSymbol;

    public Player(String username, char userSymbol) {
        this.username = username;
        if (userSymbol != 'x' && userSymbol != 'o') {
            throw new IllegalArgumentException("Invalid player symbol. You must choose 'x' or 'o'.");
        } else {
            this.userSymbol = userSymbol;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getUserSymbol() {
        return userSymbol;
    }

    public void setUserSymbol(char userSymbol) {
        this.userSymbol = userSymbol;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                '}';
    }
}
