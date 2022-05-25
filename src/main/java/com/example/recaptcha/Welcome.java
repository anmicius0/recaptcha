package com.example.recaptcha;

import javafx.fxml.FXML;

public class Welcome {
    @FXML
    protected void onWelcomeButtonClick() {
        // Change scene from welcome to game
        SceneSwitcher.switchScene("game");
    }

    @FXML
    protected void RuleButtonClink() {
        // Change scene from welcome to rule
        SceneSwitcher.switchScene("rule");
    }
}
