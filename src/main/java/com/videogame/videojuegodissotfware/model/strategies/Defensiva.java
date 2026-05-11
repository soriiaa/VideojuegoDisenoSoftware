package com.videogame.videojuegodissotfware.model.strategies;

public class Defensiva implements EstrategiaCombate {
    @Override
    public double modificadorCombate() {
        return 0.8; // reduce el daño de ataque en un 20%
    }
    @Override
    public double modificadorDefensa() {
        return 1.4; // aumenta la defensa en un 40%
    }

    @Override
    public String getNombre() {
        return "Defensiva";
    }
}