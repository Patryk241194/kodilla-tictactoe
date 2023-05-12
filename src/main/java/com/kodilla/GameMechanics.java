package com.kodilla;

import java.util.Random;

public class GameMechanics {

    private GameBoard gameBoard;
    private GameLogics gameLogics;
    private GameValidator val;
    private Player startingPlayer;
    private Player secondPlayer;
    private Player player1;
    private Player player2;
    private Player npc;
    private int numberOfPlayers;
    private int howManyInARowToWin;
    private char gameVariant;
    private char difficulty;

    public GameMechanics(int numberOfPlayers, char gameVariant) {
        val = new GameValidator();
        this.numberOfPlayers = val.validateNumberOfPlayers(numberOfPlayers);
        this.gameVariant = val.validateGameVariant(gameVariant);
        this.gameBoard = new GameBoard(this.gameVariant);
        this.gameLogics = new GameLogics(this, this.gameBoard);
        this.gameLogics.getRules();
    }

    public void play() {
        this.createPlayer(numberOfPlayers);
        boolean gameInProgress = true;

        while (gameInProgress) {

            // Starting player's move
            this.makeAMove(startingPlayer, 1, 1);
            gameBoard.displayBoard();

            // We check if the starting player won the game, or if the board is full & we have a draw.
            if (gameLogics.verifyWinner(startingPlayer.getSymbol(), this.howManyInARowToWin)) {
                System.out.println("\n" + startingPlayer.getName() + " won the game!");
                gameInProgress = false;
            } else if (gameBoard.isBoardCompleted()) {
                System.out.println("\nDraw!");
                gameInProgress = false;
            }

            // Second player's move (variant with a second player or computer)
            if (numberOfPlayers == 2) {
                this.makeAMove(secondPlayer, 3, 2);
            } else if (numberOfPlayers == 1) {
                if (getGameVariant() == '1') {
                    this.makeAMove(secondPlayer, new Random().nextInt(4), new Random().nextInt(4));
                } else if (getGameVariant() == '2') {
                    this.makeAMove(secondPlayer, new Random().nextInt(10), new Random().nextInt(10));
                }
            }
            gameBoard.displayBoard();

            // We check if the second player / npc won the game, or if the board is full & we have a draw.
            if (gameLogics.verifyWinner(secondPlayer.getSymbol(), this.howManyInARowToWin)) {
                System.out.println("\n" + secondPlayer.getName() + " won the game!");
                gameInProgress = false;
            } else if (gameBoard.isBoardCompleted()) {
                System.out.println("\nDraw!");
                gameInProgress = false;
            }
        }
    }

    public void createPlayer(int numberOfPlayers) {
        int number = val.validateNumberOfPlayers(numberOfPlayers);

        if (number == 1) {
            player1 = new User(val.validateUserName("Patryk"), val.validateSymbol('x'));
            npc = new NPC(player1);
            startingPlayer = (new Random().nextInt(2) == 0) ? player1 : npc;
            secondPlayer = (startingPlayer == player1) ? npc : player1;
        } else if (number == 2) {
            player1 = new User(val.validateUserName("Patryk"), val.validateSymbol('x'));
            player2 = new User(val.validateUserName("Michał"), val.validateSymbol(player2.setOppositeSymbol(player1)));
            startingPlayer = (new Random().nextInt(2) == 0) ? player1 : player2;
            secondPlayer = (startingPlayer == player1) ? player2 : player1;
        }
        System.out.println(startingPlayer.getName() + " starts!");
    }

    public void makeAMove(Player player, int i, int j) {
  /*      int horizontalMovement = val.validateMovementRange(i);
        int verticalMovement = val.validateMovementRange(j);
        boolean isEmpty = val.validateMovementPossibility(gameBoard, horizontalMovement, verticalMovement);

        if (isEmpty) {*/
        /*gameBoard.addMoveToTheBoard(player, horizontalMovement, verticalMovement);
        * gameBoard.addMoveToTheBoard(player,i,j);*/
        gameBoard.getBoard()[i][j] = player.getSymbol();

    }

    public void displayBoard() {
        System.out.println();
        for (int i = 0; i < gameBoard.getBoard().length; i++) {
            for (int j = 0; j < gameBoard.getBoard()[i].length; j++) {
                System.out.print("| " + gameBoard.getBoard()[i][j] + " ");
            }
            System.out.println("|");
        }
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
        if (this.getGameVariant() == '1') {
            return 3;
        } else if (this.getGameVariant() == '2') {
            return 5;
        }
        return 0;
    }

    public void setHowManyInARowToWin(int howManyInARowToWin) {
        this.howManyInARowToWin = howManyInARowToWin;
    }

    public char getGameVariant() {
        return gameVariant;
    }

    public void setGameVariant(char gameVariant) {
        this.gameVariant = gameVariant;
    }

    public char getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(char difficulty) {
        this.difficulty = difficulty;
    }
}