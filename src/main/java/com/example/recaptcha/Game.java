package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Game implements Initializable {

    public static int score = -1;
    @FXML
    private AnchorPane root;
    @FXML
    private Label scoreLabel;
    @FXML
    private Button[][] buttons = new Button[1][1];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextLevel();
    }


    public void nextLevel() {
        // Update score.
        scoreLabel.setText("Score：%3d".formatted(++score));

        // Clean up previous board.
        cleanup();

        // Generate new board.
        buttons = Answer.newBoard(buttons, score);

        // Mount buttons to root (and add listener).
        // I hope listener could move to Answer class.
        for (int i = 0; i < Math.pow(buttons.length, 2); i++) {
            buttons[i / buttons.length][i % buttons.length].setOnMouseClicked(event -> {
                Button b = (Button) event.getSource();
                int id = Integer.parseInt(b.getId().substring(7));
                if (id == Answer.ans) {
                    nextLevel();
                } else {
                    dead();
                }
            });
            root.getChildren().add(buttons[i / buttons.length][i % buttons.length]);
        }

        System.gc();
    }

    private void cleanup() {
        // Clean up previous board.
        for (int i = 0; i < Math.pow(buttons.length, 2); i++) {
            root.getChildren().remove(buttons[i / buttons.length][i % buttons.length]);
        }
    }

    public void dead() {
        SceneSwitcher.switchScene("end");
    }
}
