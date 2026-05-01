package com.videogame.videojuegodissotfware.model.entities;

public abstract class Entidad {
    private String nombre;
    private int puntosVida;
    private String estado;
    private int dano;
    private int resistencia;
    private String textura; // Aquí tenemos que guardar la ruta al png, no el png como tal.
    private int[][] posicion = new int[480][768];

    public void moverse() {

    }

    public void atacar() {

    }

    public void fortalecer() {

    }
}
