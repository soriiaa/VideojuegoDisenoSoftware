package com.videogame.videojuegodissotfware;

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
    private TileMap tileMap;
    private Player player;
    private Set<KeyCode> inputKeys = new HashSet<>();

    public GameScene(Pane container) {
        // 1. Crear el canvas
        this.canvas = new Canvas();
        this.gc = canvas.getGraphicsContext2D();

        // 2. Vincular el tamaño de forma bidireccional y forzar el renderizado nítido
        canvas.widthProperty().bind(container.widthProperty().subtract(40));
        canvas.heightProperty().bind(container.heightProperty().subtract(110));

        canvas.setLayoutX(20);
        canvas.setLayoutY(20);

        // IMPORTANTE: Esto evita que el canvas "flote" y cause el desplazamiento infinito
        canvas.setManaged(false);

        this.tileMap = new TileMap();
        this.player = new Player(100, 100);

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> inputKeys.add(e.getCode()));
        canvas.setOnKeyReleased(e -> inputKeys.remove(e.getCode()));
        canvas.setOnMouseClicked(e -> canvas.requestFocus());
    }

    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        }.start();
    }

    private void update() {
        player.update(inputKeys, tileMap);
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