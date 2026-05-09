package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Monstruo extends Entidad {
    private int botin;
    private int tipo; // 1 (mago), 2 (orco), 3 (esqueleto)
    private final int ENEMY_SIZE = 80;

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
        // Los dibujamos centrados respecto al tile de 32x32 o simplemente desde la esquina
        // Si miden 64 y el tile 32, restamos 16 para que queden centrados visualmente
        gc.drawImage(getSprite(), getX() - 16, getY() - 32, ENEMY_SIZE, ENEMY_SIZE);
    }

    public int getTipo() {
        return tipo;
    }
}
