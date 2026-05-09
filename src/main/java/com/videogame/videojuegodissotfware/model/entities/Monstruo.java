package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Monstruo extends Entidad {
    private int botin;
    private int tipo; // 1 (mago), 2 (orco), 3 (esqueleto)
    private final int ENEMY_SIZE = 96;



    public Monstruo(String nombre, int puntosVida, EstadoEntidad estado, int dano,
                    int resistencia, Image sprite, double x, double y,
                    EstrategiaCombate estrategiaCombate, int botin, int tipo) {
        super(nombre, puntosVida, estado, dano, resistencia, sprite, x, y, estrategiaCombate);
        this.botin = botin;
        this.tipo = tipo;
    }

    public void realizarTurno() {
        comprobarEstado();
        comprobarEstrategia();
        accion();
    }

    private void comprobarEstado() {

    }

    private void comprobarEstrategia() {

    }

    protected abstract void accion();

    public void render(GraphicsContext gc) {
        Image sprite = getSprite();
        if (sprite != null) {
            gc.drawImage(sprite, getX(), getY(), ENEMY_SIZE, ENEMY_SIZE);
        } else {
            gc.setFill(Color.MAGENTA); // Color chillón para debug
            gc.fillRect(getX(), getY(), 32, 32);
        }

    }

    public int getTipo() {
        return tipo;
    }
}
