package com.videogame.videojuegodissotfware.model.strategies;

public class Agresiva implements EstrategiaCombate {
    @Override
    public double modificadorCombate() {
        return 1.5; // aumenta el daño de ataque en un 50%
    }

    @Override
    public double modificadorDefensa() {
        return 0.5; // reduce la defensa en un 50%
    }

    @Override
    public String getNombre() {
        return "Agresiva";
    }
}

