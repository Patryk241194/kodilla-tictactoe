package com.kodilla.frontend;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private final int boardSize = 3;
    private final int tileSize = 75;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, boardSize * tileSize, boardSize * tileSize, Color.WHITE);

        drawBoard(root);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBoard(Group root) {
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                // Coordinates of the field on the board
                double x = row * tileSize;
                double y = column * tileSize;

                // Creating a rectangle representing a field on the board
                Rectangle tile = new Rectangle(x, y, tileSize, tileSize);
                tile.setFill(Color.LIGHTGRAY);
                tile.setStroke(Color.BLACK);

                root.getChildren().add(tile);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}