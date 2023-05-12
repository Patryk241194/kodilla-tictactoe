package com.kodilla;

public class GameValidator {

    // User class validation

    public String validateUserName(String name) {
        String validatedName = null;

        if (name.isEmpty() || name.matches("\\s+")) {
            throw new IllegalArgumentException("Invalid input. Please enter a name with at least one digit.");
        } else {
            validatedName = name;
        }
        return validatedName;
    }

    public char validateSymbol(char symbol) {
        char validatedSymbol;

        if (symbol != 'x' && symbol != 'o') {
            throw new IllegalArgumentException("Invalid user symbol. You must choose 'x' or 'o'.");
        } else {
            validatedSymbol = symbol;
        }
        return validatedSymbol;
    }

    // NPC class validation - no need

    // GameBoard class validation - no need

    // GameMechanics class validation

    public int validateNumberOfPlayers(int numberOfPlayers) {
        int validatedNumberOfPlayers;

        if (numberOfPlayers != 1 && numberOfPlayers != 2) {
            throw new IllegalArgumentException("Invalid number. You must choose '1' or '2'.");
        } else {
            validatedNumberOfPlayers = numberOfPlayers;
        }
        return validatedNumberOfPlayers;
    }

    public char validateGameVariant(char gameVariant) {
        char validatedGameVariant;

        if (gameVariant != '1' && gameVariant != '2') {
            throw new IllegalArgumentException("Invalid game variant. You must choose '1' or '2'.");
        } else {
            validatedGameVariant = gameVariant;
        }
        return validatedGameVariant;
    }

    public int validateMovementRange(int move) {
        int validatedMove;

        if (move < 0 || move > 9) {
            throw new IllegalArgumentException("Invalid move. You must choose between '00' and '99'.");
        } else {
            validatedMove = move;
        }
        return validatedMove;
    }

    public boolean validateMovementPossibility(GameBoard gameBoard, int i, int j) {
        boolean isFieldEmpty = false;

        if (gameBoard.get(i, j) != ' ') {
            throw new IllegalArgumentException("Field is not empty.");
        } else {
            isFieldEmpty = true;
        }
        return isFieldEmpty;
    }
}
