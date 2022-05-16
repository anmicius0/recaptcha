package com.example.recaptcha;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class GameScene {

    private int _score = -1;
    private int ans = -1;
    private Random rand = new Random();
    private String color = "", colorAns = "";

    @FXML
    private Button buttons[][];
    private Label score;
    private AnchorPane root;
    private EventHandler<javafx.scene.input.MouseEvent> mouseClickedHandler;

    public void init(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        root = (AnchorPane) scene.getRoot();

        mouseClickedHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Button b = (Button) event.getSource();
                String id = b.getId();
                id = id.substring(7);
                int i_id = Integer.parseInt(id);
                if (i_id == ans) {
                    refresh();
                } else {
                    exit();
                }
            }
        };
        score = (Label) root.getChildren().get(0);

        buttons = new Button[1][1];
        refresh();
        stage.setScene(scene);
    }

    private void genColor() {
        int r = rand.nextInt(256), g = rand.nextInt(256), b = rand.nextInt(256),
                range = (int) ((r + g + b > 127 * 3 ? 64 : -64) / Math.log(_score + Math.E));
        color = "rgb(" + r + "," + g + "," + b + ");";
        colorAns = "rgb(" + (r + range) + "," + (g + range) + "," + (b + range) + ");";
    }

    public void refresh() {
        score.setText("分數：" + ++_score + "分");
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                root.getChildren().remove(buttons[i][j]);
            }
        }

        if (_score % 5 != 0 || buttons.length >= 20)
            buttons = new Button[buttons.length][buttons.length];
        else {
            buttons = new Button[buttons.length + 1][buttons.length + 1];
        }
        int size = 1000 / buttons.length - 5;
        ans = rand.nextInt(buttons.length * buttons.length);
        genColor();

        for (int i = 0; i < buttons.length; i++)
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(size, size);
                buttons[i][j].setLayoutX((size + 5) * j + 52.5);
                buttons[i][j].setLayoutY((size + 5) * i + 112.5);
                buttons[i][j].setText(null);
                buttons[i][j].setStyle("-fx-background-color:" + color + ";");
                if (i * buttons.length + j == ans)
                    buttons[i][j].setStyle("-fx-background-color:" + colorAns + ";");
                buttons[i][j].setId("button_" + (i * buttons.length + j));
                buttons[i][j].setOnMouseClicked(mouseClickedHandler);
                root.getChildren().add(buttons[i][j]);
            }
        System.gc();
    }

    public void exit() {
        // Implement gameover condition.
    }
}
