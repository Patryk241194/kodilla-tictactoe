package com.kodilla.frontend;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InfoCenter {

    private final StackPane pane;
    private final Label message;
    private final Button startGameButton;
    private final Button showRulesButton;
    private final Button rankingButton;
    private final Button quitGameButton;
    private final ImageView background;
    private final ImageView gameProducer;

    public InfoCenter() {
        pane = new StackPane();
        pane.setMinSize(UIConstants.INFO_CENTER_WIDTH, UIConstants.INFO_CENTER_HEIGHT);

        message = new Label("Tic-Tac-Toe");
        message.setMinSize(UIConstants.INFO_CENTER_WIDTH, UIConstants.INFO_CENTER_HEIGHT);
        message.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-110);
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

        background = new ImageView("images/tictactoe - background.jpg");
        background.setFitWidth(UIConstants.INFO_CENTER_WIDTH);
        background.setFitHeight(UIConstants.INFO_CENTER_HEIGHT);
        pane.getChildren().add(0, background);

        gameProducer = new ImageView("images/kodilla.png");
        gameProducer.setFitWidth(100);
        gameProducer.setFitHeight(100);
        gameProducer.setTranslateX(-95);
        gameProducer.setTranslateY(120);
        dropShadow.setSpread(1.5);
        gameProducer.setEffect(dropShadow);
        pane.getChildren().add(gameProducer);

    }

    public StackPane getStackPane() {
        return pane;
    }

    public void updateMessage(String message) {
        this.message.setText(message);
    }

    public void showStartButton() {
        startGameButton.setVisible(true);
    }

    public void hideStartButton() {
        startGameButton.setVisible(false);
    }

    public void setStartGameButtonOnAction(EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }
}
