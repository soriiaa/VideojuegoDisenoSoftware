package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.image.Image;

public abstract class Entidad {
    private String nombre;
    private int puntosVida;
    private EstadoEntidad estado;
    private int dano;
    private int resistencia;
    private Image sprite; // Aquí tenemos que guardar la ruta al png, no el png como tal.
    private double x, y;
    private EstrategiaCombate estrategiaCombate;

    public Entidad (String nombre, int puntosVida, EstadoEntidad estado, int dano, int resistencia,
                    Image sprite, double x, double y, EstrategiaCombate estrategiaCombate) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.estado = estado;
        this.dano = dano;
        this.resistencia = resistencia;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.estrategiaCombate = estrategiaCombate;
    }

    public Image getSprite() {
        return sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public void atacar() {

    }

    public void fortalecer() {

    }
}
