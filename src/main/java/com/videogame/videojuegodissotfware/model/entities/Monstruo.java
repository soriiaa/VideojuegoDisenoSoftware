package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;

public class Monstruo extends Entidad {
    private String categoria;
    private EstrategiaCombate estrategia;
    private int botin;

    public Monstruo(String nombre, int puntosVida, int dano, int resistencia, EstrategiaCombate estrategia) {
        super(nombre, puntosVida, dano, resistencia);
        this.estrategia = estrategia;
    }
}
