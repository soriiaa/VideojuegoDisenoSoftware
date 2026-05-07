package com.videogame.videojuegodissotfware.model.entities.state;

public class EstadoFurioso implements EstadoEntidad{

    @Override
    public double modificarDano(int danoBase) {
        return danoBase * 1.2;
    }

    @Override
    public String getNombre() {
        return "Furioso";
    }
}
