package com.kodilla.frontend;

import com.kodilla.backend.GameLogicsFX;
import com.kodilla.backend.GameRanking;
import com.kodilla.backend.MinMaxFX;
import com.kodilla.backend.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Random;

import static com.kodilla.frontend.UIConstants.*;

public class TileBoard {

    private StackPane pane;
    private InfoCenter infoCenter;
    private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
    GameRanking gameRanking;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private String mode;
    private String difficulty;
    private boolean isEndOfGame = false;

    public TileBoard(InfoCenter infoCenter, GameRanking gameRanking) {
        this.infoCenter = infoCenter;
        this.gameRanking = gameRanking;
        pane = new StackPane();
        pane.setMinSize(TILE_SIZE, TILE_SIZE);
        pane.setTranslateY(100);

        addAllTiles();
    }

    private void addAllTiles() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((column * TILE_SIZE));
                tile.getStackPane().setTranslateY((row * TILE_SIZE));
                pane.getChildren().add(tile.getStackPane());
                tiles[row][column] = tile;
            }
        }
    }

    protected int getHowManyInARowToWin() {
        if (BOARD_SIZE == 3) {
            return 3;
        } else if (BOARD_SIZE > 3 && BOARD_SIZE <= 6) {
            return 4;
        } else if (BOARD_SIZE > 6) {
            return 5;
        }
        return 0;
    }

    public int getDifficulty(String difficulty) {
        if (difficulty.equalsIgnoreCase(DIFFICULTY_EASY)) {
            return 1;
        } else if (difficulty.equalsIgnoreCase(DIFFICULTY_MEDIUM)) {
            if (BOARD_SIZE == 3) {
                return 5;
            } else if (BOARD_SIZE >= 4 && BOARD_SIZE <= 10) {
                return 2;
            }
        } else if (difficulty.equalsIgnoreCase(DIFFICULTY_HARD)) {
            if (BOARD_SIZE == 3) {
                return 8;
            } else if (BOARD_SIZE >= 4 && BOARD_SIZE <= 6) {
                return 4;
            } else if (BOARD_SIZE >= 7 && BOARD_SIZE <= 10) {
                return 3;
            }
        }
        return 1;
    }

    public Player drawWhoStarts(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = (new Random().nextInt(2) == 0) ? player1 : player2;
        return currentPlayer;
    }

    public void changePlayerTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        infoCenter.updateMessage(currentPlayer.getName() + "'s turn");
    }

    public StackPane getStackPane() {
        return pane;
    }

    public boolean verifyResultOfTheDuel() {

        if (GameLogicsFX.verifyWinner(tiles, player1.getSymbol(), player2.getSymbol(), getHowManyInARowToWin()) == -1) {
            System.out.printf((WIN_MESSAGE) + NEW_LINE, player1.getName());
            infoCenter.updateMessage(player1.getName() + " won!");
            isEndOfGame = true;
        } else if (GameLogicsFX.verifyWinner(tiles, player1.getSymbol(), player2.getSymbol(), getHowManyInARowToWin()) == 1) {
            System.out.printf((WIN_MESSAGE) + NEW_LINE, player2.getName());
            infoCenter.updateMessage(player2.getName() + " won!");
            isEndOfGame = true;
        } else if (GameLogicsFX.isBoardCompleted(tiles)) {
            System.out.println(DRAW_MESSAGE);
            infoCenter.updateMessage(DRAW_MESSAGE);
            isEndOfGame = true;
        } else if (!GameLogicsFX.hasChanceToWin(tiles, player1.getSymbol(), getHowManyInARowToWin()) &&
                !GameLogicsFX.hasChanceToWin(tiles, player2.getSymbol(), getHowManyInARowToWin())) {
            System.out.println(DRAW_MESSAGE);
            infoCenter.updateMessage(DRAW_MESSAGE);
            isEndOfGame = true;
        }
        if (isEndOfGame) {
            Player winnerPlayer = getWinnerPlayer();
            if (winnerPlayer != null) {
                if (winnerPlayer.equals(player1)) {
                    gameRanking.addWin(player1);
                    gameRanking.addLoss(player2);
                } else if (winnerPlayer.equals(player2)) {
                    gameRanking.addWin(player2);
                    gameRanking.addLoss(player1);
                }
            }
            gameRanking.saveRanking();
            infoCenter.showPlayAgainButton();
            infoCenter.showMainMenuButton();
        }
        return isEndOfGame;
    }

    public void reset() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                tiles[row][column].setValue("");
            }
        }
        isEndOfGame = false;
        currentPlayer = drawWhoStarts(player1, player2);
        infoCenter.updateMessage(currentPlayer.getName() + "'s turn");
        infoCenter.hidePlayAgainButton();
        infoCenter.hideMainMenuButton();
    }

    private Player getWinnerPlayer() {
        if (GameLogicsFX.verifyWinner(tiles, player1.getSymbol(), player2.getSymbol(), getHowManyInARowToWin()) == -1) {
            return player1;
        } else if (GameLogicsFX.verifyWinner(tiles, player1.getSymbol(), player2.getSymbol(), getHowManyInARowToWin()) == 1) {
            return player2;
        } else {
            return null; // No winner
        }
    }

    public void makeComputerMove(String difficulty) {
        MinMaxFX minMax = new MinMaxFX();
        int[] bestMove = minMax.getBestMove(tiles, getHowManyInARowToWin(), getDifficulty(difficulty), player1, player2);
        int row = bestMove[0];
        int col = bestMove[1];

        if (row >= 0 && col >= 0) {
            tiles[row][col].setValue(currentPlayer.getSymbol());
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public class Tile {

        private StackPane pane;
        private Label label;

        public Tile() {
            pane = new StackPane();
            pane.setMinSize(TILE_SIZE, TILE_SIZE);

            Rectangle border = new Rectangle();
            border.setWidth(TILE_SIZE);
            border.setHeight(TILE_SIZE);
            border.setFill(Color.LIGHTGRAY);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(48));
            pane.getChildren().add(label);

/*            pane.setOnMouseClicked(event -> {
                if (currentPlayer.equals(player1)) {
                    if (label.getText().isEmpty() && !isEndOfGame) {
                        label.setText(currentPlayer.getSymbol());
                        changePlayerTurn();
                        verifyResultOfTheDuel();

                        if (mode.equals(SINGLE_PLAYER) && !isEndOfGame && currentPlayer.equals(player2)) {
                            makeComputerMove(difficulty);
                            changePlayerTurn();
                            verifyResultOfTheDuel();
                        }
                    }
                } else if (currentPlayer.equals(player2)) {
                    if (label.getText().isEmpty() && !isEndOfGame) {
                        label.setText(currentPlayer.getSymbol());
                        changePlayerTurn();
                        verifyResultOfTheDuel();
                    }
                }
            });*/
/*            pane.setOnMouseClicked(event -> {
                if (currentPlayer.equals(player1)) {
                    if (label.getText().isEmpty() && !isEndOfGame) {
                        label.setText(currentPlayer.getSymbol());
                        changePlayerTurn();
                        verifyResultOfTheDuel();

                        if (mode.equals(SINGLE_PLAYER) && !isEndOfGame && currentPlayer.equals(player2)) {
                            makeComputerMove(difficulty);
                            changePlayerTurn();
                            verifyResultOfTheDuel();
                        }
                    }
                } else if (currentPlayer.equals(player2) && mode.equals(MULTI_PLAYER)) {
                    if (label.getText().isEmpty() && !isEndOfGame) {
                        label.setText(currentPlayer.getSymbol());
                        changePlayerTurn();
                        verifyResultOfTheDuel();
                    }
                } else if (currentPlayer.equals(player2) && mode.equals(SINGLE_PLAYER)) {
                    if (label.getText().isEmpty() && !isEndOfGame) {
                        makeComputerMove(difficulty);
                        changePlayerTurn();
                        verifyResultOfTheDuel();
                    }
                }
            });*/
            pane.setOnMouseClicked(event -> {
                if (currentPlayer.equals(player1)) {
                    if (label.getText().isEmpty() && !isEndOfGame) {
                        label.setText(currentPlayer.getSymbol());
                        changePlayerTurn();
                        verifyResultOfTheDuel();

                        if (mode.equals(SINGLE_PLAYER) && !isEndOfGame && currentPlayer.equals(player2)) {
                            makeComputerMove(difficulty);
                            changePlayerTurn();
                            verifyResultOfTheDuel();
                        }
                    }
                } else if (currentPlayer.equals(player2)) {
                    if (label.getText().isEmpty() && !isEndOfGame) {
                        if (mode.equals(SINGLE_PLAYER)) {
                            makeComputerMove(difficulty);
                            changePlayerTurn();
                            verifyResultOfTheDuel();
                        } else if (mode.equals(MULTI_PLAYER)) {
                            label.setText(currentPlayer.getSymbol());
                            changePlayerTurn();
                            verifyResultOfTheDuel();
                        }
                    }
                }
            });
        }

        public StackPane getStackPane() {
            return pane;
        }

        public String getValue() {
            return label.getText();
        }

        public void setValue(String value) {
            label.setText(value);
        }
    }
}

