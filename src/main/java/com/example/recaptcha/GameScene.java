package com.example.recaptcha;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class GameScene {

    private final Random rand = new Random();
    private final EventHandler<javafx.scene.input.MouseEvent> mouseClickedHandler;
    private final AnchorPane root;
    private Label scoreLabel;
    private int score = -1;
    private int ans = -1;
    private String color = "", colorAns = "";
    @FXML
    private Button[][] buttons;

    public GameScene(Stage stage) throws IOException {
        // Load game scene.
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(), stage.getHeight());

        // Initialize objects.
        root = (AnchorPane) scene.getRoot();
        mouseClickedHandler = event -> {
            Button b = (Button) event.getSource();
            int id = Integer.parseInt(b.getId().substring(7));
            if (id == ans) {
                nextLevel();
            } else {
                dead();
            }
        };
        scoreLabel = (Label) root.getChildren().get(0);
        buttons = new Button[1][1];

        nextLevel();
        stage.setScene(scene);
    }

    private void genColor() {
        int r = rand.nextInt(256), g = rand.nextInt(256), b = rand.nextInt(256),
                range = (int) ((r + g + b > 127 * 3 ? 64 : -64) / Math.log(score + Math.E));
        color = "rgb(" + r + "," + g + "," + b + ");";
        colorAns = "rgb(" + (r + range) + "," + (g + range) + "," + (b + range) + ");";
    }

    public void nextLevel() {
        // Update score.
        scoreLabel.setText("Score：%d".formatted(++score));

        // Clean up previous board.
        for (Button[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                root.getChildren().remove(button[j]);
            }
        }

        // Generate new board.
        if (score % 5 != 0 || buttons.length >= 20)
            buttons = new Button[buttons.length][buttons.length];
        else {
            buttons = new Button[buttons.length + 1][buttons.length + 1];
        }
        ans = rand.nextInt(buttons.length * buttons.length);
        genColor();

        int size = (int) (root.getHeight() * 0.7 / buttons.length);
        for (int i = 0; i < buttons.length; i++)
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(size, size);
                buttons[i][j].setLayoutX((size + 5) * j + 52.5);
                buttons[i][j].setLayoutY((size + 5) * i + 112.5);
                buttons[i][j].setText(null);
                buttons[i][j].setId("button_" + (i * buttons.length + j));
                buttons[i][j].setOnMouseClicked(mouseClickedHandler);

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
        // TODO: Implement game over condition.
    }
}
