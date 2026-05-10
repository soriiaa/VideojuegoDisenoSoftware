package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameScene;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class OptionsController {
    @FXML
    private Button resumeBtn;
    @FXML
    private Button restartBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private StackPane contentPane;
    @FXML
    private Label gameTime;
    private GameScene game;
    GameFacade facade;
    private Timeline timeline;

    public void initialize() {
        resumeBtn.setOnMouseClicked(event -> resume());
        restartBtn.setOnMouseClicked(event -> restart());
        exitBtn.setOnMouseClicked(event -> exit());
        facade = GameFacade.getInstance();
        gameTime.setText(facade.getTiempoFormateado());

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                    gameTime.setText(facade.getTiempoFormateado());
                })
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setGameContext(GameScene game) {
        this.game = game;
    }

    public void resume() {
        timeline.stop();

        StackPane parent = (StackPane) contentPane.getParent();
        parent.getChildren().remove(contentPane); // se elimina al hijo (que es la pantalla de pausa) del stackpane general

        game.start(); // se reanuda el juego
        game.getCanvas().requestFocus();
    }
    public void restart() {
        timeline.stop();
        facade.reiniciarPartida();
    }
    public void exit() {
        timeline.stop();
        facade.finalizarPartida();
    }
}


