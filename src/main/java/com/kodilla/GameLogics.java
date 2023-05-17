package com.kodilla;

public class GameLogics {


    private GameMechanics gameMechanics;
    private GameBoard gameBoard;
    protected final String RULES_TEMPLATE = "\nRules for Tic-Tac-Toe (%dx%d): \n"
            + " Players take turns putting their marks in empty squares.\n"
            + " The first player to get %d of her marks in a row (up, down, across, or diagonally) is the winner.\n"
            + " When all %d squares are full, the game is over.\n"
            + " If no player has %d marks in a row, the game ends in a tie.\n"
            + "\nThe board below presents possible movements and instructions for performing the movement:";
    protected final String PLAYER1 = "\nPlayer1: ";
    protected final String PLAYER2 = "\nPlayer2: ";
    protected final String WIN_MESSAGE = "\n%s won the game!";
    protected final String DRAW_MESSAGE = "\nDraw!";
    protected final String STARTING_PLAYER_MESSAGE = "\n%s starts!";
    protected final String PLAYER_TURN_MESSAGE = "\n%s:";
    protected final String MOVE_MESSAGE = "\n%s's move: \n";

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

    public boolean verifyWinner(char symbol, int howManyInARowToWin) {
        char[][] board = gameBoard.getBoard();
        int boardSize = board.length;

        // Horizontal and vertical check
        for (int i = 0; i < boardSize; i++) {
            int counterHorizontal = 0;
            int counterVertical = 0;
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == symbol) {
                    counterHorizontal++;
                } else {
                    counterHorizontal = 0;
                }
                if (board[j][i] == symbol) {
                    counterVertical++;
                } else {
                    counterVertical = 0;
                }
                if (counterHorizontal == howManyInARowToWin || counterVertical == howManyInARowToWin) {
                    return true;
                }
            }
        }

        // Diagonal check (top-left to bottom-right && top-right to bottom-left)
        for (int i = 0; i <= boardSize - howManyInARowToWin; i++) {
            for (int j = 0; j <= boardSize - howManyInARowToWin; j++) {
                int counterDiagonal1 = 0;
                int counterDiagonal2 = 0;
                for (int k = 0; k < howManyInARowToWin; k++) {
                    if (board[i + k][j + k] == symbol) {
                        counterDiagonal1++;
                    } else {
                        counterDiagonal1 = 0;
                    }
                    if (board[i + k][j + howManyInARowToWin - k - 1] == symbol) {
                        counterDiagonal2++;
                    } else {
                        counterDiagonal2 = 0;
                    }
                    if (counterDiagonal1 == howManyInARowToWin || counterDiagonal2 == howManyInARowToWin) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
