package com.kodilla.backend;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameRanking implements Serializable {
    private final Map<Player, PlayerStats> playerStatsMap;
    private final File rankingSave;

    public GameRanking() {
        playerStatsMap = new HashMap<>();
        rankingSave = new File("ranking.list");
    }

    public void addWin(Player player) {
        if (!player.getName().equals("Computer")) {
            PlayerStats playerStats = playerStatsMap.getOrDefault(player, new PlayerStats());
            playerStats.addWin();
            playerStatsMap.put(player, playerStats);

        }
        System.out.println(player + " +1");
    }

    public void addLoss(Player player) {
        if (!player.getName().equals("Computer")) {
            PlayerStats playerStats = playerStatsMap.getOrDefault(player, new PlayerStats());
            playerStats.addLoss();
            playerStatsMap.put(player, playerStats);

        }
        System.out.println(player + " -1");
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

    public String getFullRanking() {
        StringBuilder rankingText = new StringBuilder();
        rankingText.append("\nGameRanking:\n\n");

        AtomicInteger counter = new AtomicInteger(1);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        playerStatsMap.entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> -entry.getValue().getWinsToLossesRatio()))
                .limit(10)
                .forEach(entry -> {
                    Player player = entry.getKey();
                    PlayerStats playerStats = entry.getValue();
                    rankingText.append(counter.getAndIncrement()) // Counter increment and add value before entry
                            .append(". ")
                            .append(player.getName())
                            .append(" - Wins: ")
                            .append(playerStats.getWins())
                            .append(", Losses: ")
                            .append(playerStats.getLosses())
                            .append(", W/L ratio: ")
                            .append(decimalFormat.format(playerStats.getWinsToLossesRatio()))
                            .append("\n");
                });
        System.out.println(rankingText.toString());
        return rankingText.toString();
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

    @SuppressWarnings("unchecked")
    public void loadRanking() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rankingSave));
            Object readMap = ois.readObject();
            if (readMap instanceof HashMap<?, ?>) {
                playerStatsMap.putAll((HashMap<Player, PlayerStats>) readMap);
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game ranking: " + e.getMessage());
        }
    }

    public Map<Player, PlayerStats> getPlayerStatsMap() {
        return playerStatsMap;
    }

    public File getRankingSave() {
        return rankingSave;
    }
}



