package com.kodilla;

public class GameLogics {


    private GameMechanics gameMechanics;
    private GameBoard gameBoard;
    private String RULES_3x3 = "\nRules for Tic-Tac-Toe: \n"
            + " Players take turns putting their marks in empty squares.\n"
            + " The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n"
            + " When all 9 squares are full, the game is over.\n"
            + " If no player has 3 marks in a row, the game ends in a tie.";

    private String RULES_10x10 = "\nRules for Tic-Tac-Toe (10x10): \n"
            + " Players take turns putting their marks in empty squares.\n"
            + " The first player to get 5 of her marks in a row (up, down, across, or diagonally) is the winner.\n"
            + " When all 100 squares are full, the game is over.\n"
            + " If no player has 5 marks in a row, the game ends in a tie.";

    public GameLogics(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
        this.gameBoard = gameMechanics.getGameBoard();
    }
    public void getRules() {
        if (gameMechanics.getGameVariant() == '1')
        System.out.println(RULES_3x3);
        else if (gameMechanics.getGameVariant() == '2') {
            System.out.println(RULES_10x10);
        }
        System.out.println("\nThe board below presents possible" +
                " movements and instructions for performing the movement:");
        this.gameBoard.displayPossibleMoves();
    }

// Do modyfikacji o dwa warianty gry!
    public boolean verifyWinner(char symbol, int howManyInARowToWin) {
        char[][] board = gameBoard.getBoard();

        // Verification of rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        // Verification of columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        // Verification of diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }

        if (board[2][0] == symbol && board[1][1] == symbol && board[0][2] == symbol) {
            return true;
        }

        return false;
    }


}
