package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameEventListener;
import com.videogame.videojuegodissotfware.gui.view.GameScene;
import com.videogame.videojuegodissotfware.model.core.state.EstadoJuego;
import com.videogame.videojuegodissotfware.model.core.state.MenuState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GameController implements GameEventListener {
    @FXML
    public Label coins;
    @FXML
    public Label res;
    @FXML
    public Label attack;
    @FXML
    public Label state;
    @FXML
    private ImageView pauseBtn;
    @FXML
    private StackPane contentPane;

    // Aqui van los atributos y métodos del patron STATE
    // =================================================

    private EstadoJuego estadoJuego = new MenuState();

    // =================================================

    @FXML
    private StackPane centralContent; // El hueco en el center del BorderPane
    public void initialize() {
        GameScene game = new GameScene(centralContent, this);
        centralContent.getChildren().add(game.getCanvas());
        game.start();

        pauseBtn.setOnMouseClicked(event -> pause());
    }

    @Override
    public void onFightStarted(int enemyId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videogame/videojuegodissotfware/fxml/fight-view.fxml"));
            Parent combatView = loader.load();
            /*
            // Obtenemos el controlador de la pelea para pasarle el ID del enemigo
            FightController fightCtrl = loader.getController();
            fightCtrl.setEnemyData(enemyId);
             */

            centralContent.getChildren().clear();
            centralContent.getChildren().add(combatView);
            System.out.println("Creado el combate con enemigo de tipo: " + enemyId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pause() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videogame/videojuegodissotfware/fxml/options-view.fxml"));
            Parent pauseMenu = loader.load();

            contentPane.getChildren().add(pauseMenu);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
