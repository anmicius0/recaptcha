package com.example.recaptcha;

import javafx.scene.control.Button;

import java.util.Random;

public class Answer {
    private static final Random rand = new Random();
    public static String color = "", colorAns = "";

    public static int ans = -1;

    public static Button[][] newBoard(Button[][] buttons, int score) {
        // This method updates buttons

        // Update buttons
        if (buttons.length < 16) {
            buttons = new Button[buttons.length + 1][buttons.length + 1];
        }

        // Generate random answer
        ans = rand.nextInt(buttons.length * buttons.length);

        // Set properties
        int height = 540;
        int width = 960;
        int size = (int) (height * 0.8 / buttons.length - 5);
        getColor(score);

        for (int i = 0; i < buttons.length; i++)
            for (int j = 0; j < buttons.length; j++) {
                Button newButton = new Button();
                // Size and position
                newButton.setPrefSize(size, size);
                newButton.setLayoutX((size + 5) * j + (width - height) * 0.5 + height * 0.1);
                newButton.setLayoutY((size + 5) * i + height * 0.2);
                newButton.setText(null);
                newButton.setId("button_" + (i * buttons.length + j));

                // Color
                Answer.setButtonsColor(newButton, i * buttons.length + j == ans);

                buttons[i][j] = newButton;
            }

        return buttons;
    }

    public static void getColor(int score) {
        int r = rand.nextInt(256), g = rand.nextInt(256), b = rand.nextInt(256),
                range = (int) ((r + g + b > 127 * 3 ? 64 : -64) / Math.log(score + Math.E));
        color = "rgb(" + r + "," + g + "," + b + ");";
        colorAns = "rgb(" + (r + range) + "," + (g + range) + "," + (b + range) + ");";
    }

    public static void setButtonsColor(Button button, boolean ans) {
        // Set color
        if (ans) {
            button.setStyle("-fx-background-color:%s;".formatted(colorAns));
        } else {
            button.setStyle("-fx-background-color:%s;".formatted(color));
        }
    }
}
