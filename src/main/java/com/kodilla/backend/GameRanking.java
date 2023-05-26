package com.kodilla.backend;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class GameRanking {
    private Map<Player, PlayerStats> playerStatsMap;

    public GameRanking() {
        playerStatsMap = new HashMap<>();
    }

    public void addWin(Player player) {
        PlayerStats playerStats = playerStatsMap.getOrDefault(player, new PlayerStats());
        playerStats.addWin();
        playerStatsMap.put(player, playerStats);
    }

    public void addLoss(Player player) {
        PlayerStats playerStats = playerStatsMap.getOrDefault(player, new PlayerStats());
        playerStats.addLoss();
        playerStatsMap.put(player, playerStats);
    }


    public void displayPlayerRanking(@NotNull Player player) {
        System.out.println("\nGameRanking for player: " + player.getName());
        PlayerStats playerStats = playerStatsMap.get(player);
        if (playerStats != null) {
            int wins = playerStats.getWins();
            int losses = playerStats.getLosses();
            double ratio = playerStats.getWinsToLossesRatio();
            System.out.println("Wins: " + wins + ", Losses: " + losses + ", W/L ratio: " + ((ratio != -1.0) ? ratio : "-"));
        } else {
            System.out.println("Player not found in the ranking.");
        }
    }

    public void displayFullRanking() {
        System.out.println("\nGameRanking:");
        List<Player> sortedPlayers = new ArrayList<>(playerStatsMap.keySet());
        sortedPlayers.sort(Comparator.comparingDouble(player -> playerStatsMap.get(player).getWinsToLossesRatio()).reversed());
        for (Player player : sortedPlayers) {
            PlayerStats playerStats = playerStatsMap.get(player);
            System.out.println(player.getName() + " - Wins: " + playerStats.getWins() + ", Losses: " + playerStats.getLosses() + ", W/L ratio: " + playerStats.getWinsToLossesRatio());
        }
    }

/*    public void saveRankings(String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(playerStatsMap);
            System.out.println("Game ranking saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game ranking: " + e.getMessage());
        }
    }

    public void loadRankings(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            playerStatsMap = (Map<Player, PlayerStats>) objectInputStream.readObject();
            System.out.println("Game ranking loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game ranking: " + e.getMessage());
        }
    }*/

    private class PlayerStats {
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
}