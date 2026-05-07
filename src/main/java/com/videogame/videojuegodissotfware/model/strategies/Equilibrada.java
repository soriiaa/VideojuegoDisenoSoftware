package com.videogame.videojuegodissotfware.model.strategies;

public class Equilibrada implements EstrategiaCombate {
    @Override
    public double modificadorAtaque() {
        return 1.0; // no modifica
    }

    @Override
    public double modificadorDefensa() {
        return 1.0; // no modifica
    }

    @Override
    public String getEstrategia() {
        return "Equilibrada";
    }
}
