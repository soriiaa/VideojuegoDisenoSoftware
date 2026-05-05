package com.videogame.videojuegodissotfware.gui.view;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Set;

public class Personaje {
    private double x, y;
    private double speed = 4.0;
    private Image sprite;
    private final int PLAYER_SIZE = 64;

    public Personaje(double x, double y) {
        this.x = x;
        this.y = y;
        try {
            this.sprite = new Image(getClass().getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/Personaje.png"));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el sprite del jugador, se usará un cuadro azul.");
        }
    }

    public int update(Set<KeyCode> keys, Mapa map) {
        double nextX = x;
        double nextY = y;

        if (keys.contains(KeyCode.W)) {
            nextY -= speed;
        }
        if (keys.contains(KeyCode.S)) {
            nextY += speed;
        }
        if (keys.contains(KeyCode.A)) {
            nextX -= speed;
        }
        if (keys.contains(KeyCode.D)) {
            nextX += speed;
        }

        int tileDestino = map.getTileAt(nextX, nextY);
        if (tileDestino != 2 && tileDestino != 3 && tileDestino != 4) {
            x = nextX;
            y = nextY;
        }

        int idEnemyCollision = -1;
        for (Enemy e : map.getEnemigos()) {
            // Comprobamos si el jugador toca al enemigo usando un rectángulo de colisión
            if (Math.abs(nextX - e.getX()) < 32 && Math.abs(nextY - e.getY()) < 32) {
                idEnemyCollision = e.getType();
            }
        }
        return idEnemyCollision;
    }

    public void render(GraphicsContext gc) {
        if (sprite != null) {
            gc.drawImage(sprite, x, y, PLAYER_SIZE, PLAYER_SIZE);
        } else {
            // si falla imagen se pone un cuadro azul
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, PLAYER_SIZE, PLAYER_SIZE);
        }
    }
}