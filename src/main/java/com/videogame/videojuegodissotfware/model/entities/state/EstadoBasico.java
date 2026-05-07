package com.videogame.videojuegodissotfware.model.entities.state;

public class EstadoBasico implements EstadoEntidad{
    @Override
    public double modificarDano(int danoBase) {
        return danoBase;
    }

    @Override
    public String getNombre() {
        return "Basico";
    }
}
