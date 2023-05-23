package com.kodilla;

public class GameRunner {
    public static void main(String[] args) {
        ConsoleInputReader scan = new ConsoleInputReader();
        GameMechanics game = new GameMechanics(scan.numberOfPlayers(), scan.boardSize());
        game.play();
    }
}

