package com.kodilla.backend;

import java.io.Serializable;

public class PlayerStats implements Serializable {
    private int wins;
    private int losses;

    public PlayerStats() {
        wins = 0;
        losses = 0;
    }

    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinsToLossesRatio() {
        if (getLosses() != 0) {
            return (double) getWins() / getLosses();
        } else {
            return -1.0;
        }
    }
}
