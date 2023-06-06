package com.kodilla.frontend;

public class UIConstants {

    protected static int BOARD_SIZE = 3;
    protected static final int TILE_SIZE = 100;
    protected static final int INFO_CENTER_HEIGHT = 300;
    protected static final int INFO_CENTER_WIDTH = 300;
    protected static final String WIN_MESSAGE = "%s won the game!";
    protected static final String DRAW_MESSAGE = "Draw!";
    protected static final String NEW_LINE = "%n";
    protected static final String DIFFICULTY_EASY = "easy";
    protected static final String DIFFICULTY_MEDIUM = "medium";
    protected static final String DIFFICULTY_HARD = "hard";
    protected static final String SINGLE_PLAYER = "Single player";
    protected static final String MULTI_PLAYER = "Multi player";
    protected static final String RULES_TEMPLATE = "\nRules for Tic-Tac-Toe (%dx%d): \n"
            + " Players take turns putting their marks in empty\n squares.\n"
            + " The first player to get %d of her marks in a row\n (up, down, across, or diagonally) is the winner.\n"
            + " \nWhen all %d squares are full, the game is over.\n"
            + " If no player has %d marks in a row,\n the game ends in a tie.\n";

}
