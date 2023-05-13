package com.kodilla;

import java.util.Random;

public class GameMechanics {

    private GameBoard gameBoard;
    private GameLogics gameLogics;
    private GameValidator validate;
    private ConsoleInputReader scan;
    private MinMax analyze;
    private Player startingPlayer;
    private Player secondPlayer;
    private Player player1;
    private Player player2;
    private Player npc;
    private int numberOfPlayers;
    private int howManyInARowToWin;
    private int boardSize;

    public GameMechanics(int numberOfPlayers, int boardSize) {
        validate = new GameValidator();
        scan = new ConsoleInputReader();
        analyze = new MinMax();
        this.numberOfPlayers = validate.numberOfPlayers(numberOfPlayers);
        this.boardSize = validate.boardSize(boardSize);
        this.gameBoard = new GameBoard(this.boardSize);
        this.gameLogics = new GameLogics(this);
        this.gameLogics.getRules();
    }

    public void play() {
        createPlayer(numberOfPlayers);
        boolean isGameOver = false;

        while (!isGameOver) {
            // The variant where the player starts
            if (startingPlayer == player1) {

                makeAMove(startingPlayer, 1, 1);
                isGameOver = verifyWinner(startingPlayer);

                switch (numberOfPlayers) {
                    case 2:
                        makeAMove(secondPlayer, 3, 2);
                        break;
                    case 1:
                        int[] bestMove = analyze.getBestMove(gameBoard.getBoard(), secondPlayer.getSymbol(), getHowManyInARowToWin());
                        makeAMove(secondPlayer, bestMove[0], bestMove[1]);
                        break;
                }
                isGameOver = verifyWinner(secondPlayer);

            } else if (startingPlayer == player2 || startingPlayer == npc) {
            // Variant in which the player or the npc starts, depending on whether we play vs computer or vs player
                switch (numberOfPlayers) {
                    case 2:
                        makeAMove(startingPlayer, 3, 2);
                        break;
                    case 1:
                        int[] bestMove = analyze.getBestMove(gameBoard.getBoard(), startingPlayer.getSymbol(), getHowManyInARowToWin());
                        makeAMove(startingPlayer, bestMove[0], bestMove[1]);
                        break;
                }
                isGameOver = verifyWinner(startingPlayer);

                makeAMove(secondPlayer, 1, 1);
                isGameOver = verifyWinner(secondPlayer);
            }
        }
    }

    public boolean verifyWinner(Player player) {
        boolean doWeHaveWinner = false;

        if (gameLogics.verifyWinner(player.getSymbol(), getHowManyInARowToWin())) {
            System.out.println("\n" + player.getName() + " won the game!");
            doWeHaveWinner = true;
        } else if (gameBoard.isBoardCompleted()) {
            System.out.println("\nDraw!");
            doWeHaveWinner = true;
        }
        return doWeHaveWinner;
    }

    public void createPlayer(int numberOfPlayers) {
        int players = validate.numberOfPlayers(numberOfPlayers);

        if (players == 1) {
            player1 = new User(validate.username(scan.Name()), validate.symbol(scan.Symbol()));
            npc = new NPC(player1);
            startingPlayer = (new Random().nextInt(2) == 0) ? player1 : npc;
            secondPlayer = (startingPlayer == player1) ? npc : player1;
        } else if (players == 2) {
            player1 = new User(validate.username(scan.Name()), validate.symbol(scan.Symbol()));
            player2 = new User(validate.username(scan.Name()), validate.symbol(player2.setOppositeSymbol(player1)));
            startingPlayer = (new Random().nextInt(2) == 0) ? player1 : player2;
            secondPlayer = (startingPlayer == player1) ? player2 : player1;
        }
        System.out.println(startingPlayer.getName() + " starts!");
    }

    public void makeAMove(Player player, int row, int col) {
        int horizontalMove = validate.movementRange(row,getBoardSize());
        int verticalMove = validate.movementRange(col,getBoardSize());
        boolean isEmpty = validate.movementPossibility(gameBoard, horizontalMove, verticalMove);

        if (isEmpty) {
            gameBoard.setFigure(player, row, col);
        }
        gameBoard.displayBoard();
    }

    public Player getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(Player startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getHowManyInARowToWin() {
        if (this.getBoardSize() == 3) {
            return 3;
        } else if (this.getBoardSize() > 3 && this.getBoardSize() <= 6) {
            return 4;
        } else if (this.getBoardSize() > 6) {
            return 5;
        }
        return 0;
    }

    public void setHowManyInARowToWin(int howManyInARowToWin) {
        this.howManyInARowToWin = howManyInARowToWin;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(char boardSize) {
        this.boardSize = boardSize;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}