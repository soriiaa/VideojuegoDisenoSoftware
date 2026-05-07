package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Monstruo extends Entidad {
    private String categoria;
    private EstrategiaCombate estrategia;
    private int botin;
    private int type;
    private final int ENEMY_SIZE = 80;

    public Monstruo(String nombre, int puntosVida, String estado, int dano,
                    int resistencia, Image sprite, double x, double y, String categoria,
                    EstrategiaCombate estrategiaCombate, int botin, int type) {
        super(nombre, puntosVida, estado, dano, resistencia, sprite, x, y, estrategiaCombate);
        this.categoria = categoria;
        this.botin = botin;
        this.type = type;
    }

    public void render(GraphicsContext gc) {
        // Los dibujamos centrados respecto al tile de 32x32 o simplemente desde la esquina
        // Si miden 64 y el tile 32, restamos 16 para que queden centrados visualmente
        gc.drawImage(getSprite(), getX() - 16, getY() - 32, ENEMY_SIZE, ENEMY_SIZE);
    }

    public int getType() {
        return type;
    }
}
