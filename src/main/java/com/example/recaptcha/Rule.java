package com.example.recaptcha;

import javafx.fxml.FXML;

public class Rule {

    @FXML
    protected void RuleButtonClink() {
        // Change scene from rule to welcome
        SceneSwitcher.switchScene("welcome");
    }
}
