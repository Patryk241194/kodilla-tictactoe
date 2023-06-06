package com.kodilla.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static com.kodilla.frontend.UIConstants.*;

public class InfoCenter {

    private final StackPane pane;
    private final Label message;
    private final Button startGameButton;
    private final Button showRulesButton;
    private final Button rankingButton;
    private final Button quitGameButton;
    private final Button playAgainButton;
    private final Button mainMenuButton;
    private final ImageView background;
    private final ImageView gameProducerLogo;

    public InfoCenter() {
        pane = new StackPane();
        pane.setMinSize(UIConstants.INFO_CENTER_WIDTH, UIConstants.INFO_CENTER_HEIGHT);

        message = new Label("Tic-Tac-Toe");
        message.setMinSize(UIConstants.INFO_CENTER_WIDTH, UIConstants.INFO_CENTER_HEIGHT);
        message.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-120);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(3);
        dropShadow.setSpread(3);
        message.setEffect(dropShadow);
        message.setTextFill(Color.WHITE);
        pane.getChildren().add(message);

        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(-60);
        pane.getChildren().add(startGameButton);

        showRulesButton = new Button("Show Rules");
        showRulesButton.setMinSize(135, 30);
        showRulesButton.setTranslateY(-20);
        pane.getChildren().add(showRulesButton);

        rankingButton = new Button("Show World Ranking");
        rankingButton.setMinSize(135, 30);
        rankingButton.setTranslateY(20);
        pane.getChildren().add(rankingButton);

        quitGameButton = new Button("Quit Game");
        quitGameButton.setMinSize(135, 30);
        quitGameButton.setTranslateY(60);
        pane.getChildren().add(quitGameButton);

        playAgainButton = new Button("Play Again");
        playAgainButton.setMinSize(80, 20);
        playAgainButton.setTranslateX(-50);
        playAgainButton.setTranslateY(-80);
        playAgainButton.setVisible(false);
        pane.getChildren().add(playAgainButton);

        mainMenuButton = new Button("Main Menu");
        mainMenuButton.setMinSize(80, 20);
        mainMenuButton.setTranslateX(50);
        mainMenuButton.setTranslateY(-80);
        mainMenuButton.setVisible(false);
        pane.getChildren().add(mainMenuButton);

        background = new ImageView("images/tictactoe - background.jpg");
        background.setFitWidth(UIConstants.INFO_CENTER_WIDTH);
        background.setFitHeight(UIConstants.INFO_CENTER_HEIGHT);
        pane.getChildren().add(0, background);

        gameProducerLogo = new ImageView("images/kodilla.png");
        gameProducerLogo.setFitWidth(100);
        gameProducerLogo.setFitHeight(100);
        gameProducerLogo.setTranslateX(-95);
        gameProducerLogo.setTranslateY(120);
        dropShadow.setSpread(1.5);
        gameProducerLogo.setEffect(dropShadow);
        pane.getChildren().add(gameProducerLogo);

    }

    public StackPane getStackPane() {
        return pane;
    }

    public void updateMessage(String message) {
        this.message.setText(message);
    }

    public void updateRankingUI(int fontSize) {
        this.message.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        message.setTranslateY(-30);
        mainMenuButton.setTranslateY(-115);
    }

    public void updateRulesUI(int fontSize) {
        this.message.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        message.setTranslateY(-30);
        mainMenuButton.setTranslateY(-115);
        mainMenuButton.setTranslateX(TILE_SIZE / 2 * (3 - INFO_CENTER_WIDTH / TILE_SIZE) + 50);
    }

    public void editMessageStyle() {
        this.message.setTranslateX(TILE_SIZE / 2 * (BOARD_SIZE - INFO_CENTER_WIDTH / TILE_SIZE));
        this.message.setFont(Font.font("Arial", FontWeight.BOLD, 32));
    }

    public void hideMessage() {
        message.setVisible(false);
    }

    public void showPlayAgainButton() {
        playAgainButton.setVisible(true);
        playAgainButton.setTranslateX(TILE_SIZE / 2 * (BOARD_SIZE - INFO_CENTER_WIDTH / TILE_SIZE) - 50);
    }

    public void hidePlayAgainButton() {
        playAgainButton.setVisible(false);
    }

    public void showMainMenuButton() {
        mainMenuButton.setVisible(true);
        mainMenuButton.setTranslateX(TILE_SIZE / 2 * (BOARD_SIZE - INFO_CENTER_WIDTH / TILE_SIZE) + 50);
    }

    public void hideMainMenuButton() {
        mainMenuButton.setVisible(false);
    }

    public void showMenuButtons() {
        startGameButton.setVisible(true);
        showRulesButton.setVisible(true);
        rankingButton.setVisible(true);
        quitGameButton.setVisible(true);
    }

    public void hideAllButtons() {
        startGameButton.setVisible(false);
        showRulesButton.setVisible(false);
        rankingButton.setVisible(false);
        quitGameButton.setVisible(false);
        playAgainButton.setVisible(false);
        mainMenuButton.setVisible(false);
    }

    public void showAllImages() {
        background.setVisible(true);
        gameProducerLogo.setVisible(true);
    }

    public void hideAllImages() {
        background.setVisible(false);
        gameProducerLogo.setVisible(false);
    }

    public void setPaneSizeToDefault() {
        pane.setMinSize(UIConstants.INFO_CENTER_WIDTH, UIConstants.INFO_CENTER_HEIGHT);
    }

    public void setStartGameButtonOnAction(EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }

    public void setShowRulesButtonOnAction(EventHandler<ActionEvent> onAction) {
        showRulesButton.setOnAction(onAction);
    }

    public void setRankingButtonOnAction(EventHandler<ActionEvent> onAction) {
        rankingButton.setOnAction(onAction);
    }

    public void setQuitGameButtonOnAction(EventHandler<ActionEvent> onAction) {
        quitGameButton.setOnAction(onAction);
    }

    public void setPlayAgainButtonOnAction(EventHandler<ActionEvent> onAction) {
        playAgainButton.setOnAction(onAction);
    }

    public void setMainMenuButtonOnAction(EventHandler<ActionEvent> onAction) {
        mainMenuButton.setOnAction(onAction);
    }
}
