package com.kodilla.frontend;

import com.kodilla.backend.Symbol;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

import static com.kodilla.frontend.UIConstants.*;

public class ChoiceValidator {

    protected Optional<Integer> boardSize() {
        ChoiceDialog<Integer> sizeBoardDialog = new ChoiceDialog<>(3, 3, 4, 5, 6, 7, 8, 9, 10);
        sizeBoardDialog.setTitle("New Game");
        sizeBoardDialog.setHeaderText("Select Board Size");
        sizeBoardDialog.setContentText("Size:");
        sizeBoardDialog.setResizable(false);
        Optional<Integer> sizeBoardResult = sizeBoardDialog.showAndWait();
        return sizeBoardResult;
    }

    protected Optional<String> difficulty() {
        ChoiceDialog<String> difficultyDialog = new ChoiceDialog<>(DIFFICULTY_EASY, DIFFICULTY_EASY, DIFFICULTY_MEDIUM, DIFFICULTY_HARD);
        difficultyDialog.setTitle("New Game");
        difficultyDialog.setHeaderText("Select Difficulty Level");
        difficultyDialog.setContentText("Difficulty:");
        difficultyDialog.setResizable(false);
        Optional<String> difficultyResult = difficultyDialog.showAndWait();
        return difficultyResult;
    }

    protected Optional<String> gameMode() {
        ChoiceDialog<String> modeDialog = new ChoiceDialog<>(SINGLE_PLAYER, SINGLE_PLAYER, MULTI_PLAYER);
        modeDialog.setTitle("New Game");
        modeDialog.setHeaderText("Select Game Mode");
        modeDialog.setContentText("Mode:");
        modeDialog.setResizable(false);
        Optional<String> modeResult = modeDialog.showAndWait();
        return modeResult;
    }

    protected Optional<String> playerName(int playerNumber) {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("New Game");
        nameDialog.setHeaderText("Enter Player " + playerNumber + " Name");
        nameDialog.setContentText("Name:");
        nameDialog.setResizable(false);

        Optional<String> result;
        String name = null;

        do {
            result = nameDialog.showAndWait();

            if (result.isPresent()) {
                name = result.get().trim();
                if (name.isEmpty()) {
                    // Display an error message if the name is empty
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Name cannot be empty!");
                    alert.showAndWait();
                }
            } else {
                break;
            }
        } while (name.isEmpty());

        return result;
    }

    protected Optional<String> playerSymbol() {
        ChoiceDialog<String> symbolDialog = new ChoiceDialog<>(Symbol.X, Symbol.O, Symbol.X);
        symbolDialog.setTitle("New Game");
        symbolDialog.setHeaderText("Select Player 1 Symbol");
        symbolDialog.setContentText("Symbol:");
        symbolDialog.setResizable(false);
        Optional<String> symbolResult = symbolDialog.showAndWait();
        return symbolResult;
    }
}
