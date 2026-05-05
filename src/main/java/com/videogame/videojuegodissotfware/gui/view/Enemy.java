package com.videogame.videojuegodissotfware.gui.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy {
    private double x, y;
    private Image sprite;
    private int type; // 8: Mago, 9: Ogro, 10: Esqueleto
    private final int ENEMY_SIZE = 80; // Aquí los haces más grandes (el doble que el tile)

    public Enemy(int type, double x, double y, Image sprite) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void render(GraphicsContext gc) {
        // Los dibujamos centrados respecto al tile de 32x32 o simplemente desde la esquina
        // Si miden 64 y el tile 32, restamos 16 para que queden centrados visualmente
        gc.drawImage(sprite, x - 16, y - 32, ENEMY_SIZE, ENEMY_SIZE);
    }

    // Getters para detectar colisión
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getType() {
        return type;
    }
}
