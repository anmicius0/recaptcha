package com.example.recaptcha;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Random;

public class GameScene {

    private final Random rand = new Random();
    private final EventHandler<MouseEvent> mouseClickedHandler;
    private final AnchorPane root;
    private final Label scoreLabel;
    private int score = -1;
    private int ans = -1;
    private String color = "", colorAns = "";
    @FXML
    private Button buttons[][] = new Button[1][1];

    public GameScene(Stage stage) throws IOException {
        // Load game scene.
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(), stage.getHeight());

        // Initialize objects.
        root = (AnchorPane) scene.getRoot();
        mouseClickedHandler = event -> {
            Button b = (Button) event.getSource();
            int id = Integer.parseInt(b.getId().substring(7));
            if (id == ans)
                nextLevel();
            else
                dead();
        };
        // TODO: I think scoreLabel could be simplified.
        scoreLabel = (Label) root.getChildren().get(0);
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
        scoreLabel.setText("Score：%3d".formatted(++score));

        // Clean up previous board.
        for (Button[] button : buttons) {
            for (int j = 0; j < buttons.length; j++) {
                root.getChildren().remove(button[j]);
            }
        }

        // Generate new board.
        if (buttons.length >= 22)
            buttons = new Button[buttons.length][buttons.length];
        else {
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
