package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameEventListener;
import com.videogame.videojuegodissotfware.gui.view.GameScene;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import com.videogame.videojuegodissotfware.model.core.Mapa;
import com.videogame.videojuegodissotfware.model.core.state.FightState;
import com.videogame.videojuegodissotfware.model.core.state.PlayState;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import com.videogame.videojuegodissotfware.model.items.Espada;
import com.videogame.videojuegodissotfware.model.items.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

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
    private HBox inventoryBox;
    @FXML
    private HBox inventoryItems;
    @FXML
    private ImageView pauseBtn;
    @FXML
    private StackPane contentPane;
    @FXML
    private StackPane centralContent;
    private Parent combatView;

    @FXML
    private Label precioMejorarArma;
    @FXML
    private Label precioMejorarArmadura;
    @FXML
    private Label precioComprarPocion;

    @FXML
    private Button btnMejorarArma;
    @FXML
    private Button btnMejorarArmadura;
    @FXML
    private Button btnComprarPocion;


    GameFacade facade;
    GameScene game;

    public void initialize() {
        this.facade = GameFacade.getInstance();

        Personaje personaje = facade.getPersonaje();
        Mapa mapaReal = facade.getMundo().getMapa();
        game = new GameScene(centralContent, this, personaje, mapaReal);
        setPlayerData(personaje);
        refrescarInventario();
        refrescarTienda();

        centralContent.getChildren().add(game.getCanvas());
        game.start();

        pauseBtn.setOnMouseClicked(event -> pause());
        btnMejorarArma.setOnAction(e -> {
            facade.mejorarArma(Integer.parseInt(String.valueOf(this.precioMejorarArma.getText())));
            refrescarTienda();
            refrescarInventario();
        });
        btnMejorarArmadura.setOnAction(e -> {
            facade.mejorarArmadura(Integer.parseInt(String.valueOf(this.precioMejorarArmadura.getText())));
            refrescarTienda();
            refrescarInventario();
        });
        btnComprarPocion.setOnAction(e -> {
            facade.comprarPocion(Integer.parseInt(String.valueOf(this.precioComprarPocion.getText())));
            refrescarTienda();
            refrescarInventario();
        });
    }

    public void setPlayerData(Personaje personaje) {
        name.setText(personaje.getNombre());
        hp.setText(personaje.getPuntosVida() + "/" + personaje.getPuntosVidaMax());
        level.setText(String.valueOf(personaje.getNivel()));
        coins.setText(String.valueOf(personaje.getOro()));
        res.setText(String.valueOf(personaje.getResistencia()));
        attack.setText(String.valueOf(personaje.getDano()));
        state.setText(personaje.getEstado().getNombre());
        refrescarInventario();
    }

    // necesario para que con el listener se actualice la UI lateral cada vez que el personaje sufra cambios en sus stats (sobretodo en el combate)
    @Override
    public void onPlayerStatsChanged() {
        setPlayerData(facade.getPersonaje());
        refrescarTienda();
        refrescarInventario();
        System.out.println("UI Lateral actualizada desde el combate");
    }

    @Override
    public void onFightStarted(Monstruo enemigo) {
        facade.setEstado(new FightState());
        facade.iniciarCombate(enemigo);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videogame/videojuegodissotfware/fxml/fight-view.fxml"));
            combatView = loader.load();

            centralContent.getChildren().add(combatView);
            combatView.toFront();

            // al iniciar el combate, se necesita el controlador del combate para pasarle el listener (que es el GameController)
            // y que así pueda actualizar la UI lateral cada vez que el personaje sufra cambios en sus stats
            FightController fightController = loader.getController();
            fightController.setListener(this); // el GameController se pasa a sí mismo como listener

            game.getCanvas().setFocusTraversable(false); // quitamos el foco del mapa para que durante combate no se mueva el personaje
            combatView.requestFocus();

            System.out.println("Creado el combate con enemigo de tipo: " + enemigo.getTipo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refrescarTienda() {
        GameFacade facade = GameFacade.getInstance();
        int[] precios = facade.getPreciosTienda();

        this.precioMejorarArma.setText(String.valueOf(precios[0]));
        this.precioMejorarArmadura.setText(String.valueOf(precios[1]));
        this.precioComprarPocion.setText(String.valueOf(precios[2]));

        this.btnMejorarArma.setDisable(!facade.comprobarDinero(Integer.parseInt(String.valueOf(precioMejorarArma.getText()))));
        this.btnMejorarArmadura.setDisable(!facade.comprobarDinero(Integer.parseInt(String.valueOf(precioMejorarArmadura.getText()))));
        this.btnComprarPocion.setDisable(!facade.comprobarDinero(Integer.parseInt(String.valueOf(precioComprarPocion.getText()))));
    }

    public void refrescarInventario() {
        GameFacade facade = GameFacade.getInstance();

        ArrayList<Item> inventario = facade.getPersonaje().getListaItems();
        inventoryItems.getChildren().clear();

        for (Item item : inventario) {
            ImageView imageView = new ImageView();
            imageView.setImage(item.getSprite());
            imageView.setFitWidth(28);
            imageView.setFitHeight(28);
            inventoryItems.getChildren().add(imageView);
        }

        refrescarOro();
    }

    public void refrescarOro() {
        coins.setText(String.valueOf(facade.getPersonaje().getOro()));
    }

    @Override
    public void onFightEnded() {
        if (combatView != null) {
            centralContent.getChildren().remove(combatView);
            combatView = null;
        }
        game.resetAfterFight();
        facade.setEstado(new PlayState());

        // Devolvemos el foco al Canvas para que el personaje vuelva a moverse inmediatamente
        game.getCanvas().setFocusTraversable(true);
        game.getCanvas().requestFocus();

        game.start();

        refrescarTienda();
        refrescarInventario();
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
