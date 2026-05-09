package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameEventListener;
import com.videogame.videojuegodissotfware.gui.view.GameScene;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import com.videogame.videojuegodissotfware.model.core.Mapa;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GameController implements GameEventListener {
    @FXML
    public Label name;
    @FXML
    public Label coins;
    @FXML
    public Label level;
    @FXML
    public Label hp;
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
    @FXML
    private StackPane centralContent;

    GameFacade facade;

    public void initialize() {
        this.facade = GameFacade.getInstance();

        Personaje player = facade.getPersonaje();
        String tipoMapa = facade.getTipoMapa();
        Mapa mapaReal = facade.getMundo().getMapa();
        GameScene game = new GameScene(centralContent, this, player, mapaReal);
        setPlayerData(player);

        centralContent.getChildren().add(game.getCanvas());
        game.start();

        pauseBtn.setOnMouseClicked(event -> pause());
    }

    public void setPlayerData(Personaje player) {
        name.setText(player.getNombre());
        hp.setText(player.getPuntosVida() + "/" + player.getPuntosVidaMax());
        level.setText(String.valueOf(player.getNivel()));
        coins.setText(String.valueOf(player.getOro()));
        res.setText(String.valueOf(player.getResistencia()));
        attack.setText(String.valueOf(player.getDano()));
        state.setText(player.getEstado().getNombre());

        // FALTA SETTEAR EL ARRAY DE ITEMS, AL INICIAR LA PARTIDA NO TIENE NINGUNO
    }

    @Override
    public void onFightStarted(int enemyId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videogame/videojuegodissotfware/fxml/fight-view.fxml"));
            Parent combatView = loader.load();
            centralContent.getChildren().clear();
            centralContent.getChildren().add(combatView);
            System.out.println("Creado el combate con enemigo de tipo: " + enemyId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pause() {
        facade.pausarPartida();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videogame/videojuegodissotfware/fxml/options-view.fxml"));
            Parent pauseMenu = loader.load();
            contentPane.getChildren().add(pauseMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
