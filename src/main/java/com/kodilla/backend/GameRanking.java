package com.kodilla.backend;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class GameRanking implements Serializable {
    private final Map<Player, PlayerStats> playerStatsMap;
    private final File rankingSave;

    public GameRanking() {
        playerStatsMap = new HashMap<>();
        rankingSave = new File("ranking.list");
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


    public void displayPlayerRanking(Player player) {
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
        playerStatsMap.entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> -entry.getValue().getWinsToLossesRatio()))
                .forEach(entry -> {
                    Player player = entry.getKey();
                    PlayerStats playerStats = entry.getValue();
                    System.out.println(player.getName() + " - Wins: " + playerStats.getWins() + ", Losses: " + playerStats.getLosses() + ", W/L ratio: " + playerStats.getWinsToLossesRatio());
                });
    }

    public void saveRanking() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rankingSave));
            oos.writeObject(playerStatsMap);
            oos.close();
            System.out.println("Game ranking saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game ranking: " + e.getMessage());
        }
    }

    public void loadRanking() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rankingSave));
            Object readMap = ois.readObject();
            if (readMap instanceof HashMap) {
                playerStatsMap.putAll((HashMap) readMap);
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game ranking: " + e.getMessage());
        }
    }
}



