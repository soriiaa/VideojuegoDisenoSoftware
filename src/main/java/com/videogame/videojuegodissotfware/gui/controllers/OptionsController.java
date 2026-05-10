package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameEventListener;
import com.videogame.videojuegodissotfware.gui.view.GameScene;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.ClientInfoStatus;

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
    GameFacade facade;
    GameEventListener listener;

    public void initialize() {
        resumeBtn.setOnMouseClicked(event -> resume());
        restartBtn.setOnMouseClicked(event -> restart());
        exitBtn.setOnMouseClicked(event -> exit());
        facade = GameFacade.getInstance();
    }

    public void setGameContext(GameScene game) {
        this.game = game;
    }

    public void resume() {
        StackPane parent = (StackPane) contentPane.getParent();
        parent.getChildren().remove(contentPane); // se elimina al hijo (que es la pantalla de pausa) del stackpane general

        game.start(); // se reanuda el juego
        game.getCanvas().requestFocus();
    }
    public void restart() {
        listener.onRestart();
        resume();
    }
    public void exit() {
        facade.finalizarPartida();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videogame/videojuegodissotfware/fxml/launch-view.fxml"));
            Parent root = loader.load();

            exitBtn.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void setListener(GameEventListener listener) {
        this.listener = listener;
    }
}


