package com.videogame.videojuegodissotfware;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameScene {
    private Scene scene;
    private javafx.scene.canvas.Canvas canvas;
    private GraphicsContext gc;
    private TileMap tileMap;
    private Player player;
    private Set<KeyCode> inputKeys = new HashSet<>();

    public GameScene() {
        StackPane root = new StackPane();
        canvas = new Canvas(1280, 720); // Tamaño de tu ventana
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        scene = new Scene(root);

        // Inicializamos mapa y jugador
        tileMap = new TileMap();
        player = new Player(100, 100); // Posición inicial

        // Gestión de teclado
        scene.setOnKeyPressed(e -> inputKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> inputKeys.remove(e.getCode()));
    }

    public void startGameLoop() {
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

    public Scene getScene() { return scene; }
}