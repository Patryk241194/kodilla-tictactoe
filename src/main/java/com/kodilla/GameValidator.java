package com.kodilla;

public class GameValidator {

    // User class validation

    public String username(String name) {
        String validatedName = null;

        if (name.isEmpty() || name.matches("\\s+")) {
            throw new IllegalArgumentException("Invalid input. Please enter a name with at least one digit.");
        } else {
            validatedName = name;
        }
        return validatedName;
    }

    public char symbol(char symbol) {
        char validatedSymbol;

        if (symbol != 'x' && symbol != 'o') {
            throw new IllegalArgumentException("Invalid user symbol. You must choose 'x' or 'o'.");
        } else {
            validatedSymbol = symbol;
        }
        return validatedSymbol;
    }

    // NPC class validation - ...

    // GameBoard class validation - ...

    // GameMechanics class validation

    public int numberOfPlayers(int numberOfPlayers) {
        int validatedNumberOfPlayers;

        if (numberOfPlayers != 1 && numberOfPlayers != 2) {
            throw new IllegalArgumentException("Invalid number. You must choose '1' or '2'.");
        } else {
            validatedNumberOfPlayers = numberOfPlayers;
        }
        return validatedNumberOfPlayers;
    }

    public int boardSize(int boardSize) {
        int validatedBoardSize;

        if (boardSize < 3 || boardSize > 10) {
            throw new IllegalArgumentException("Invalid board size. The board size must be between 3 and 10.");
        } else {
            validatedBoardSize = boardSize;
        }

        return validatedBoardSize;
    }

    public int movementRange(int move, int boardSize) {
        int validatedMove;

        if (move < 0 || move > boardSize - 1) {
            throw new IllegalArgumentException("Invalid move. You must choose between '00' and '" + (boardSize - 1) + (boardSize - 1) + "'.");
        } else {
            validatedMove = move;
        }
        return validatedMove;
    }

    public boolean movementPossibility(GameBoard gameBoard, int row, int col) {
        boolean isFieldEmpty = false;

        if (gameBoard.getFigure(row, col) != ' ') {
            throw new IllegalArgumentException("Invalid movement. The selected field is not empty.");
        } else {
            isFieldEmpty = true;
        }
        return isFieldEmpty;
    }
}
