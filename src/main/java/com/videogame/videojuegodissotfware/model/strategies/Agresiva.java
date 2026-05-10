package com.videogame.videojuegodissotfware.model.strategies;

public class Agresiva implements EstrategiaCombate {
    @Override
    public double modificadorCombate() {
        return 1.2; // aumenta el daño de ataque en un 20%
    }

    @Override
    public double modificadorDefensa() {
        return 0.8; // reduce la defensa en un 20%
    }

    @Override
    public String getNombre() {
        return "Agresiva";
    }
}

