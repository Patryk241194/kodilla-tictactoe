/*
package com.kodilla.backend;

import com.kodilla.frontend.TileBoard;

public class GameMechanics {

    private final GameLogicsFX gameLogics;
    private final GameValidator validate;
    private final ConsoleInputReader scan;
    private final GameRanking ranking;
    private final MinMaxFX analyze;
    private Player startingPlayer;
    private Player secondPlayer;
    private Player player1;
    private Player player2;
    private final int numberOfPlayers;
    private String difficulty;
    private final int boardSize;

    public GameMechanics(int numberOfPlayers, int boardSize) {
        validate = new GameValidator();
        scan = new ConsoleInputReader();
        analyze = new MinMaxFX();
        ranking = new GameRanking();
        this.numberOfPlayers = validate.numberOfPlayers(numberOfPlayers);
        this.boardSize = validate.boardSize(boardSize);
//        this.howManyInARowToWin = getHowManyInARowToWin();
//        this.tiles = new GameBoard(boardSize, howManyInARowToWin);
        this.gameLogics = new GameLogicsFX(this);
        this.gameLogics.getRules();
    }
}*/
