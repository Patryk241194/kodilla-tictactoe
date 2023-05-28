package com.kodilla.backend;

public class GameLogics {


    private final GameMechanics gameMechanics;
    private final GameBoard gameBoard;
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

    public GameLogics(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
        this.gameBoard = gameMechanics.getGameBoard();
    }

    public void getRules() {
        int boardSize = gameMechanics.getBoardSize();
        int howManyInARow = gameMechanics.getHowManyInARowToWin();
        String rules = String.format(RULES_TEMPLATE, boardSize, boardSize, howManyInARow, boardSize * boardSize, howManyInARow);
        System.out.println(rules);
        gameBoard.displayPossibleMoves();
    }

    public static int verifyWinner(char[][] board, char playerSymbol, char opponentSymbol, int howManyInARowToWin) {

        // Check rows
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <= board[i].length - howManyInARowToWin; j++) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i][j + k] != playerSymbol) {
                        playerWin = false;
                    }
                    if (board[i][j + k] != opponentSymbol) {
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
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j] != playerSymbol) {
                        playerWin = false;
                    }
                    if (board[i + k][j] != opponentSymbol) {
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
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = 0; j <= board[i].length - howManyInARowToWin; j++) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j + k] != playerSymbol) {
                        playerWin = false;
                    }
                    if (board[i + k][j + k] != opponentSymbol) {
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
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = board[i].length - 1; j >= howManyInARowToWin - 1; j--) {
                boolean playerWin = true;
                boolean opponentWin = true;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j - k] != playerSymbol) {
                        playerWin = false;
                    }
                    if (board[i + k][j - k] != opponentSymbol) {
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

    public static boolean isBoardCompleted(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Symbol.EMPTY_FIELD) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasChanceToWin(char[][] board, char playerSymbol, int howManyInARowToWin) {
        // Check rows
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <= board[i].length - howManyInARowToWin; j++) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i][j + k] == playerSymbol || board[i][j + k] == Symbol.EMPTY_FIELD) {
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
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j] == playerSymbol || board[i + k][j] == Symbol.EMPTY_FIELD) {
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
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = 0; j <= board[i].length - howManyInARowToWin; j++) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j + k] == playerSymbol || board[i + k][j + k] == Symbol.EMPTY_FIELD) {
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
        for (int i = 0; i <= board.length - howManyInARowToWin; i++) {
            for (int j = board[i].length - 1; j >= howManyInARowToWin - 1; j--) {
                boolean hasChance = false;

                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j - k] == playerSymbol || board[i + k][j - k] == Symbol.EMPTY_FIELD) {
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
