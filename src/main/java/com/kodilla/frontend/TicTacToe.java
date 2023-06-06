package com.kodilla.frontend;

import com.kodilla.backend.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

import static com.kodilla.frontend.UIConstants.*;


public class TicTacToe extends Application {

    private Stage primaryStage;
    private Scene scene;
    private InfoCenter infoCenter;
    private TileBoard tileBoard;
    private ChoiceValidator validate = new ChoiceValidator();
    GameRanking gameRanking = new GameRanking();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Group root = new Group();
        scene = new Scene(root, INFO_CENTER_WIDTH, INFO_CENTER_HEIGHT, Color.WHITE);
        initLayout(root);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initLayout(Group root) {
        initInfoCenter(root);
        initTileBoard(root);
    }

    private void initTileBoard(Group root) {
        if (tileBoard == null) {
            tileBoard = new TileBoard(infoCenter, gameRanking);
            gameRanking.loadRanking();
        } else {
            tileBoard.reset();
        }
        tileBoard.getStackPane().setVisible(false);
        root.getChildren().add(tileBoard.getStackPane());
    }

    private void initInfoCenter(Group root) {
        infoCenter = new InfoCenter();
        infoCenter.setStartGameButtonOnAction(startNewGame(root));
        infoCenter.setShowRulesButtonOnAction(showRules(root));
        infoCenter.setRankingButtonOnAction(displayRanking(root));
        infoCenter.setQuitGameButtonOnAction(quitGame(root));
        infoCenter.setPlayAgainButtonOnAction(playAgain(root));
        infoCenter.setMainMenuButtonOnAction(mainMenu(root));

        root.getChildren().add(infoCenter.getStackPane());

    }

    private EventHandler<ActionEvent> startNewGame(Group root) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoCenter.hideAllButtons();
                infoCenter.hideAllImages();

                // Board Size selection
                Optional<Integer> sizeBoardResult = validate.boardSize();
                if (isPresent(sizeBoardResult)) return;
                BOARD_SIZE = sizeBoardResult.get();

                // Game mode selection
                Optional<String> modeResult = validate.gameMode();
                if (isPresent(modeResult)) return;
                String mode = modeResult.get();

                // Game Difficulty selection
                String difficulty = null;
                if (mode.equals(SINGLE_PLAYER)) {
                    Optional<String> difficultyResult = validate.difficulty();
                    if (isPresent(difficultyResult)) return;
                    difficulty = difficultyResult.get();
                }

                // Entering username of player1
                Optional<String> nameResult1 = validate.playerName(1);
                if (isPresent(nameResult1)) return;
                String name1 = nameResult1.get().trim();

                // Symbol selection of player1
                Optional<String> symbolResult = validate.playerSymbol();
                if (isPresent(symbolResult)) return;
                String symbol = symbolResult.get();

                if (!name1.isEmpty()) {
                    Player player1 = new Player(name1, symbol);
                    Player player2;
                    Player currentPlayer = tileBoard.getCurrentPlayer();

                    if (mode.equals(SINGLE_PLAYER)) {
                        player2 = new Player("Computer", (symbol == Symbol.O) ? Symbol.X : Symbol.O);
                        currentPlayer = tileBoard.drawWhoStarts(player1, player2);
                        tileBoard = new TileBoard(infoCenter, gameRanking);
                        tileBoard.setPlayer1(player1);
                        tileBoard.setPlayer2(player2);
                        tileBoard.setCurrentPlayer(currentPlayer);
                        tileBoard.setMode(mode);
                        tileBoard.setDifficulty(difficulty);
                    } else if (mode.equals(MULTI_PLAYER)) {
                        // Entering username of player2
                        Optional<String> nameResult2 = validate.playerName(2);
                        if (isPresent(nameResult2)) return;
                        String name2 = null;

                        if (nameResult2.isPresent()) {
                            name2 = nameResult2.get().trim();
                        }
                        player2 = new Player(name2, (symbol == Symbol.O) ? Symbol.X : Symbol.O);
                        currentPlayer = tileBoard.drawWhoStarts(player1, player2);
                        tileBoard = new TileBoard(infoCenter, gameRanking);
                        tileBoard.setPlayer1(player1);
                        tileBoard.setPlayer2(player2);
                        tileBoard.setCurrentPlayer(currentPlayer);
                        tileBoard.setMode(mode);
                    }

                    Stage primaryStage = (Stage) infoCenter.getStackPane().getScene().getWindow();
                    primaryStage.setMinWidth(BOARD_SIZE * TILE_SIZE + 15);
                    primaryStage.setMinHeight(BOARD_SIZE * TILE_SIZE + 140);
                    infoCenter.editMessageStyle();

                    infoCenter.updateMessage(currentPlayer.getName() + "'s Turn");
                    root.getChildren().add(tileBoard.getStackPane());

                }
            }

            private <T> boolean isPresent(Optional<T> choiceResult) {
                if (choiceResult.isPresent()) {
                    T choice = choiceResult.get();
                } else {
                    // Handle Cancel button action - show main menu
                    showMainMenu(root);
                    return true;
                }
                return false;
            }
        };
    }

    private EventHandler<ActionEvent> showRules(Group root) {
        return e -> {
            infoCenter.hideAllButtons();
            infoCenter.hideAllImages();
            // Board Size selection
            Optional<Integer> sizeBoardResult = validate.boardSize();
            if (sizeBoardResult.isPresent()) {
                BOARD_SIZE = sizeBoardResult.get();
                String rulesText = String.format(RULES_TEMPLATE, BOARD_SIZE, BOARD_SIZE,
                        tileBoard.getHowManyInARowToWin(), tileBoard.getHowManyInARowToWin(), tileBoard.getHowManyInARowToWin());
                infoCenter.updateMessage(rulesText);
                infoCenter.showMainMenuButton();
                infoCenter.updateRulesUI(13);
            } else {
                // Handle Cancel button action - show main menu
                showMainMenu(root);
            }
        };
    }

    private EventHandler<ActionEvent> displayRanking(Group root) {
        return e -> {
            infoCenter.hideAllButtons();
            infoCenter.hideAllImages();
            infoCenter.updateMessage(gameRanking.getFullRanking());
            infoCenter.updateRankingUI(13);
            infoCenter.showMainMenuButton();
        };
    }

    private EventHandler<ActionEvent> quitGame(Group root) {
        return event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        };
    }

    private EventHandler<ActionEvent> playAgain(Group root) {
        return e -> {
            resetGame(root);
            infoCenter.hideAllButtons();
            infoCenter.hideAllImages();
            tileBoard.getStackPane().setVisible(true);
        };
    }

    private EventHandler<ActionEvent> mainMenu(Group root) {
        return e -> {

            showMainMenu(root);
        };
    }

    private void showMainMenu(Group root) {
        // Clear any previous game state and show the main menu
        if (tileBoard != null) {
            root.getChildren().remove(tileBoard.getStackPane());
            tileBoard = null;
        }
        infoCenter.hideMessage();
        infoCenter.hidePlayAgainButton();
        infoCenter.hideMainMenuButton();
        initLayout(root);
    }

    private void resetGame(Group root) {
        if (tileBoard != null) {
            // Remove the tile board from the scene
            root.getChildren().remove(tileBoard.getStackPane());

            // Reset the board's fields
            tileBoard.reset();

            // Add the tile board back to the scene
            root.getChildren().add(tileBoard.getStackPane());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
