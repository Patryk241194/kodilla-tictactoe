package com.kodilla;

import java.util.Random;

public class GameMechanics {

    private GameBoard gameBoard;
    private GameLogics gameLogics;
    private final GameValidator validate;
    private final ConsoleInputReader scan;
    private final MinMax analyze;
    private final MinMax3x3 analyze3x3;
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
        analyze3x3 = new MinMax3x3();
        this.numberOfPlayers = validate.numberOfPlayers(numberOfPlayers);
        this.boardSize = validate.boardSize(boardSize);
        this.gameBoard = new GameBoard(boardSize);
        this.gameLogics = new GameLogics(this);
        this.gameLogics.getRules();
    }

    public void play() {
        createPlayer(numberOfPlayers);
        boolean isGameOver = false;

        while (!isGameOver) {
            // The variant where the player starts
            if (startingPlayer == player1) {

                System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + "%n", startingPlayer.toString());
                makeAMoveAndDisplayBoard(startingPlayer, scan.move(gameBoard, boardSize));
                isGameOver = verifyWinner(startingPlayer);
                if (isGameOver) {
                    break;
                }

                switch (numberOfPlayers) {
                    case 2 -> {
                        System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + "%n", secondPlayer.toString());
                        makeAMoveAndDisplayBoard(secondPlayer, scan.move(gameBoard, boardSize));
                    }
                    case 1 -> {
                        int[] bestMove = analyze.getBestMove(gameBoard.getBoard(), secondPlayer.getSymbol(), getHowManyInARowToWin());
                        /*int[] bestMove = analyze3x3.getBestMove(gameBoard.getBoard(), secondPlayer.getSymbol());*/
                        makeAMoveAndDisplayBoard(secondPlayer, bestMove);
                    }
                }
                isGameOver = verifyWinner(secondPlayer);

            } else if (startingPlayer == player2 || startingPlayer == npc) {
                // Variant in which the player or the npc starts, depending on whether we play vs computer or vs player
                switch (numberOfPlayers) {
                    case 2 -> {
                        System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + "%n", startingPlayer.toString());
                        makeAMoveAndDisplayBoard(startingPlayer, scan.move(gameBoard, boardSize));
                    }
                    case 1 -> {
                        int[] bestMove = analyze.getBestMove(gameBoard.getBoard(), startingPlayer.getSymbol(), getHowManyInARowToWin());
                        /*int[] bestMove = analyze3x3.getBestMove(gameBoard.getBoard(), startingPlayer.getSymbol());*/
                        makeAMoveAndDisplayBoard(startingPlayer, bestMove);
                    }
                }
                isGameOver = verifyWinner(startingPlayer);
                if (isGameOver) {
                    break;
                }

                System.out.printf(gameLogics.PLAYER_TURN_MESSAGE + "%n", secondPlayer.toString());
                makeAMoveAndDisplayBoard(secondPlayer, scan.move(gameBoard, boardSize));
                isGameOver = verifyWinner(secondPlayer);
            }
        }
    }

    public boolean verifyWinner(Player player) {
        boolean isGameOver = false;

        if (gameLogics.verifyWinner(player.getSymbol(), getHowManyInARowToWin())) {
            System.out.printf((gameLogics.WIN_MESSAGE) + "%n", player.getName());
            isGameOver = true;
        } else if (gameBoard.isBoardCompleted()) {
            System.out.println(gameLogics.DRAW_MESSAGE);
            isGameOver = true;
        }
        return isGameOver;
    }

    public void createPlayer(int numberOfPlayers) {
        int players = validate.numberOfPlayers(numberOfPlayers);

        if (players == 1) {
            player1 = new User(validate.username(scan.Name()), validate.symbol(scan.Symbol()));
            npc = new NPC(player1);
            startingPlayer = (new Random().nextInt(2) == 0) ? player1 : npc;
            secondPlayer = (startingPlayer == player1) ? npc : player1;
        } else if (players == 2) {
            System.out.printf(gameLogics.PLAYER1);
            player1 = new User(validate.username(scan.Name()), validate.symbol(scan.Symbol()));
            scan.nextLine();
            System.out.printf(gameLogics.PLAYER2);
            player2 = new User(validate.username(scan.Name()), validate.symbol(player1.setOppositeSymbol(player1)));
            startingPlayer = (new Random().nextInt(2) == 0) ? player1 : player2;
            secondPlayer = (startingPlayer == player1) ? player2 : player1;
        }
        System.out.printf((gameLogics.STARTING_PLAYER_MESSAGE) + "%n", startingPlayer.getName());
    }

    public void makeAMoveAndDisplayBoard(Player player, int[] move) {
        System.out.printf(gameLogics.MOVE_MESSAGE, player.toString());
        int row = validate.movementRange(move[0], boardSize);
        int column = validate.movementRange(move[1], boardSize);
        boolean isMovementPossible = validate.movementPossibility(gameBoard, row, column);

        if (isMovementPossible) {
            gameBoard.setFigure(player, row, column);
        }

        gameBoard.displayBoard();
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

    public int getBoardSize() {
        return boardSize;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}