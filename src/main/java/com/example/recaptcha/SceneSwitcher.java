package com.example.recaptcha;
import javafx.stage.Stage;
import com.example.recaptcha.GameScene;
import java.io.IOException;

public class SceneSwitcher{
	
	private static GameScene gameScene;
	
    public static void ChangeScene(Stage stage) throws IOException {
    	gameScene = new GameScene();
    	gameScene.init(stage);
    }
}
