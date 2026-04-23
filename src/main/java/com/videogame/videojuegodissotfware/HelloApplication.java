package com.videogame.videojuegodissotfware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Creamos la escena de juego directamente
        GameScene gameScene = new GameScene();

        stage.setTitle("Pixel Art Game - Desarrollo");
        stage.setScene(gameScene.getScene());
        stage.show();

        gameScene.startGameLoop();
    }

    public static void main(String[] args) {
        launch();
    }
}