package com.videogame.videojuegodissotfware.gui.view;

import com.videogame.videojuegodissotfware.model.core.Mapa;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.input.KeyCode;

public class GameScene {
    private Canvas canvas;
    private GraphicsContext gc;
    private Mapa tileMap;
    private Personaje player;
    private Set<KeyCode> inputKeys = new HashSet<>();
    private GameEventListener listener;
    private AnimationTimer gameLoop; // para poder parar el renderizado de 60 veces por segundo
    private boolean fightStarted = false;

    public GameScene(Pane container, GameEventListener listener, Personaje player, Mapa mapaReal) {
        // 1. Crear el canvas
        this.canvas = new Canvas();
        this.gc = canvas.getGraphicsContext2D();
        this.listener = listener;
        this.player = player;

        // 2. Vincular el tamaño de forma bidireccional y forzar el renderizado nítido
        canvas.widthProperty().bind(container.widthProperty().subtract(40));
        canvas.heightProperty().bind(container.heightProperty().subtract(110));

        canvas.setLayoutX(20);
        canvas.setLayoutY(20);

        // IMPORTANTE: Esto evita que el canvas "flote" y cause el desplazamiento infinito
        canvas.setManaged(false);

        this.tileMap = mapaReal;

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> inputKeys.add(e.getCode()));
        canvas.setOnKeyReleased(e -> inputKeys.remove(e.getCode()));
        canvas.setOnMouseClicked(e -> canvas.requestFocus());
    }

    public void start() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    private void update() {
        if (!fightStarted) {
            // Recibimos el objeto (o null)
            Monstruo enemigoChocado = player.update(inputKeys, tileMap);

            if (enemigoChocado != null) {
                fightStarted = true;
                gameLoop.stop();
                // Ahora le pasas el monstruo real a la pelea
                listener.onFightStarted(enemigoChocado);
            }
        }
    }

    private void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tileMap.render(gc);
        player.render(gc);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}