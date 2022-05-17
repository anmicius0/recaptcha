package com.example.recaptcha;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Game implements Initializable {

    private final Random rand = new Random();
    @FXML
    private AnchorPane root;
    @FXML
    private Label scoreLabel;
    private int score = -1;
    private int ans = -1;
    private String color = "", colorAns = "";
    @FXML
    private Button[][] buttons = new Button[1][1];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextLevel();
    }

    public void firstThing() {
        nextLevel();
        nextLevel();
    }

    private void genColor() {
        int r = rand.nextInt(256), g = rand.nextInt(256), b = rand.nextInt(256),
                range = (int) ((r + g + b > 127 * 3 ? 64 : -64) / Math.log(score + Math.E));
        color = "rgb(" + r + "," + g + "," + b + ");";
        colorAns = "rgb(" + (r + range) + "," + (g + range) + "," + (b + range) + ");";
    }

    public void nextLevel() {
        // Update score.
        scoreLabel.setText("Score：%3d".formatted(++score));

        // Clean up previous board.
        for (Button[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                root.getChildren().remove(button[j]);
            }
        }


        // Generate new board.
        if (buttons.length < 22) {
            buttons = new Button[buttons.length + 1][buttons.length + 1];
        }
        ans = rand.nextInt(buttons.length * buttons.length);
        genColor();

        int size = (int) (root.getHeight() * 0.8 / buttons.length - 5);
        for (int i = 0; i < buttons.length; i++)
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(size, size);
                buttons[i][j].setLayoutX((size + 5) * j + (root.getWidth() - root.getHeight()) * 0.5 + root.getHeight() * 0.1);
                buttons[i][j].setLayoutY((size + 5) * i + root.getHeight() * 0.1);
                buttons[i][j].setText(null);
                buttons[i][j].setId("button_" + (i * buttons.length + j));
                buttons[i][j].setOnMouseClicked(event -> {
                    Button b = (Button) event.getSource();
                    int id = Integer.parseInt(b.getId().substring(7));
                    if (id == ans)
                        nextLevel();
                    else {
                        dead();
                    }
                });

                // Set color
                if (i * buttons.length + j == ans) {
                    buttons[i][j].setStyle("-fx-background-color:%s;".formatted(colorAns));
                } else {
                    buttons[i][j].setStyle("-fx-background-color:%s;".formatted(color));
                }

                root.getChildren().add(buttons[i][j]);
            }
        System.gc();
    }

    public void dead() {
        try {
            SceneSwitcher.switchScene("end");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
