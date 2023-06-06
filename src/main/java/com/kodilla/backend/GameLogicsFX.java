package com.kodilla.backend;

import com.kodilla.frontend.TileBoard;

public class GameLogicsFX {

    protected final String MENU_MESSAGE = "\nMenu:\n1. New game - Start a new tic-tac-toe game\n"
            + "2. GameRanking - Display player ranking\n"
            + "3. Quit game - Exit the program";
    protected final String INVALID_MENU_SELECTION = "Invalid menu selection. Try again.";
    protected final String RULES_TEMPLATE = "\nRules for Tic-Tac-Toe (%dx%d): \n"
            + " Players take turns putting their marks in empty squares.\n"
            + " The first player to get %d of her marks in a row (up, down, across, or diagonally) is the winner.\n"
            + " When all %d squares are full, the game is over.\n"
            + " If no player has %d marks in a row, the game ends in a tie.\n"
            + "\nThe board below presents possible movements and instructions for performing the movement:";
    protected final String PLAYER1 = "\nPlayer1: ";
    protected final String PLAYER2 = "\nPlayer2: ";
    protected final String NPC_NAME = "Computer";
    protected final String STARTING_PLAYER_MESSAGE = "\n%s starts!";
    protected final String PLAYER_TURN_MESSAGE = "\n%s:";
    protected final String MOVE_MESSAGE = "\n%s's move: \n";
    protected final String WIN_MESSAGE = "\n%s won the game!";
    protected final String DRAW_MESSAGE = "\nDraw!";
    protected final String NEW_LINE = "%n";
    protected final String DIFFICULTY_EASY = "easy";
    protected final String DIFFICULTY_MEDIUM = "medium";
    protected final String DIFFICULTY_HARD = "hard";

/*    public GameLogicsFX(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
        this.gameBoard = gameMechanics.getTiles();
    }

    public void getRules() {
        int boardSize = gameMechanics.getBoardSize();
        int howManyInARow = gameMechanics.getHowManyInARowToWin();
        String rules = String.format(RULES_TEMPLATE, boardSize, boardSize, howManyInARow, boardSize * boardSize, howManyInARow);
        System.out.println(rules);
        gameBoard.displayPossibleMoves();
    }*/

    public static int verifyWinner(TileBoard.Tile[][] tiles, String playerSymbol, String opponentSymbol, int howManyInARowToWin) {

        // Check rows
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j <= tiles[i].length - howManyInARowToWin; j++) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (!tiles[i][j + k].getValue().equals(playerSymbol)) {
                        playerWin = false;
                    }
                    if (!tiles[i][j + k].getValue().equals(opponentSymbol)) {
                        opponentWin = false;
                    }
                }

                if (playerWin) {
                    return -1; // Player1 wins
                }
                if (opponentWin) {
                    return 1; // Player2/Computer wins
                }
            }
        }

        // Check columns
        for (int i = 0; i <= tiles.length - howManyInARowToWin; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (!tiles[i + k][j].getValue().equals(String.valueOf(playerSymbol))) {
                        playerWin = false;
                    }
                    if (!tiles[i + k][j].getValue().equals(String.valueOf(opponentSymbol))) {
                        opponentWin = false;
                    }
                }

                if (playerWin) {
                    return -1; // Player1 wins
                }
                if (opponentWin) {
                    return 1; // Player2/Computer wins
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int i = 0; i <= tiles.length - howManyInARowToWin; i++) {
            for (int j = 0; j <= tiles[i].length - howManyInARowToWin; j++) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (!tiles[i + k][j + k].getValue().equals(String.valueOf(playerSymbol))) {
                        playerWin = false;
                    }
                    if (!tiles[i + k][j + k].getValue().equals(String.valueOf(opponentSymbol))) {
                        opponentWin = false;
                    }
                }

                if (playerWin) {
                    return -1; // Player1 wins
                }
                if (opponentWin) {
                    return 1; // Player2/Computer wins
                }
            }
        }

        // Check diagonals (top-right to bottom-left)
        for (int i = 0; i <= tiles.length - howManyInARowToWin; i++) {
            for (int j = tiles[i].length - 1; j >= howManyInARowToWin - 1; j--) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (!tiles[i + k][j - k].getValue().equals(String.valueOf(playerSymbol))) {
                        playerWin = false;
                    }
                    if (!tiles[i + k][j - k].getValue().equals(String.valueOf(opponentSymbol))) {
                        opponentWin = false;
                    }
                }

                if (playerWin) {
                    return -1; // Player1 wins
                }
                if (opponentWin) {
                    return 1; // Player2/Computer wins
                }
            }
        }

        // Game is not over.
        return 0;
    }

    public static boolean isBoardCompleted(TileBoard.Tile[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j].getValue().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasChanceToWin(TileBoard.Tile[][] tiles, String playerSymbol, int howManyInARowToWin) {
        // Check rows
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j <= tiles[i].length - howManyInARowToWin; j++) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (tiles[i][j + k].getValue().equals(playerSymbol) || tiles[i][j + k].getValue().isEmpty()) {
                        hasChance = true;
                    } else {
                        hasChance = false;
                        break;
                    }
                }

                if (hasChance) {
                    return true;
                }
            }
        }

        // Check columns
        for (int i = 0; i <= tiles.length - howManyInARowToWin; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (tiles[i + k][j].getValue().equals(playerSymbol) || tiles[i + k][j].getValue().isEmpty()) {
                        hasChance = true;
                    } else {
                        hasChance = false;
                        break;
                    }
                }

                if (hasChance) {
                    return true;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int i = 0; i <= tiles.length - howManyInARowToWin; i++) {
            for (int j = 0; j <= tiles[i].length - howManyInARowToWin; j++) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (tiles[i + k][j + k].getValue().equals(playerSymbol) || tiles[i + k][j + k].getValue().isEmpty()) {
                        hasChance = true;
                    } else {
                        hasChance = false;
                        break;
                    }
                }

                if (hasChance) {
                    return true;
                }
            }
        }

        // Check diagonals (top-right to bottom-left)
        for (int i = 0; i <= tiles.length - howManyInARowToWin; i++) {
            for (int j = tiles[i].length - 1; j >= howManyInARowToWin - 1; j--) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (tiles[i + k][j - k].getValue().equals(playerSymbol) || tiles[i + k][j - k].getValue().isEmpty()) {
                        hasChance = true;
                    } else {
                        hasChance = false;
                        break;
                    }
                }

                if (hasChance) {
                    return true;
                }
            }
        }

        return false;
    }
}
