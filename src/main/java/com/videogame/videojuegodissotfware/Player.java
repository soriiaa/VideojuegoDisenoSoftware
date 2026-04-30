package com.videogame.videojuegodissotfware;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Set;

public class Player {
    private double x, y;
    private double speed = 4.0;
    private Image sprite;
    private final int PLAYER_SIZE = 32; // Ajusta según el tamaño que quieras

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        // CARGA AQUÍ TU SPRITE (asegúrate de que la ruta sea correcta)
        try {
            this.sprite = new Image(getClass().getResourceAsStream("mapa/Personaje.png"));
            // He puesto la torre como ejemplo, cámbialo por el png de tu personaje
        } catch (Exception e) {
            System.out.println("No se pudo cargar el sprite del jugador, se usará un cuadro azul.");
        }
    }

    public void update(Set<KeyCode> keys, TileMap map) {
        double nextX = x;
        double nextY = y;

        if (keys.contains(KeyCode.W)) nextY -= speed;
        if (keys.contains(KeyCode.S)) nextY += speed;
        if (keys.contains(KeyCode.A)) nextX -= speed;
        if (keys.contains(KeyCode.D)) nextX += speed;

        // COLISIÓN: Solo se mueve si el tile no es roca (ID 2), casa (4), etc.
        int tileDestino = map.getTileAt(nextX, nextY);
        if (tileDestino != 2 && tileDestino != 3 && tileDestino != 4) {
            x = nextX;
            y = nextY;
        }
    }

    public void render(GraphicsContext gc) {
        if (sprite != null) {
            // Dibujamos el sprite
            gc.drawImage(sprite, x, y, PLAYER_SIZE, PLAYER_SIZE);
        } else {
            // Si falla la imagen, un cuadro para no estar a ciegas
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, PLAYER_SIZE, PLAYER_SIZE);
        }
    }
}