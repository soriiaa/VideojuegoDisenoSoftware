package com.videogame.videojuegodissotfware.model.strategies;

public class Defensiva implements EstrategiaCombate {
    @Override
    public double modificadorCombate() {
        return 0.8; // reduce el daño de ataque en un 30%
    }
    @Override
    public double modificadorDefensa() {
        return 1.6; // aumenta la defensa en un 60%
    }

    @Override
    public String getNombre() {
        return "Defensiva";
    }
}