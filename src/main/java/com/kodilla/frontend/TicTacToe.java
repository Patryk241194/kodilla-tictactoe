package com.kodilla.frontend;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.kodilla.frontend.UIConstants.BOARD_SIZE;
import static com.kodilla.frontend.UIConstants.TILE_SIZE;


public class TicTacToe extends Application {
    private InfoCenter infoCenter;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE, Color.WHITE);
        initLayout(root);

        /*drawBoard(root);*/

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initLayout(Group root) {
        initInfoCenter(root);
        initTileBoard(root);
    }

    private void initTileBoard(Group root) {
    }

    private void initInfoCenter(Group root) {
        infoCenter = new InfoCenter();

        root.getChildren().add(infoCenter.getStackPane());

    }

    private void drawBoard(Group root) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                // Coordinates of the field on the board
                double x = row * TILE_SIZE;
                double y = column * TILE_SIZE;

                // Creating a rectangle representing a field on the board
                Rectangle tile = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
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
