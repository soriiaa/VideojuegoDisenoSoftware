package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class OptionsController {
    @FXML
    private Button resumeBtn;
    @FXML
    private Button restartBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private StackPane contentPane;
    private GameScene game;
    private StackPane pauseMenuRoot; // Necesitaremos esto para cerrarlo

    public void initialize() {
        resumeBtn.setOnMouseClicked(event -> resume());
        restartBtn.setOnMouseClicked(event -> restart());
        exitBtn.setOnMouseClicked(event -> exit());

    }

    public void setGameContext(GameScene game, StackPane pauseMenuRoot) {
        this.game = game;
        this.pauseMenuRoot = pauseMenuRoot;
    }

    public void resume() {
        StackPane parent = (StackPane) contentPane.getParent();
        parent.getChildren().remove(contentPane); // se elimina al hijo (que es la pantalla de pausa) del stackpane general

        game.start(); // se reanuda el juego
        game.getCanvas().requestFocus();
    }
    public void restart() {
    }
    public void exit() {
    }


}


