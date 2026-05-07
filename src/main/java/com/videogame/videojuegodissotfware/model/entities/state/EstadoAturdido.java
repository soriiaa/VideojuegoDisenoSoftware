package com.videogame.videojuegodissotfware.model.entities.state;

public class EstadoAturdido implements EstadoEntidad {
    @Override
    public double modificarDano(int danoBase) {
        return 0; // si esta aturdido puede atacar pero no hace daño a su rival
    }

    @Override
    public String getNombre() {
        return null;
    }
}
