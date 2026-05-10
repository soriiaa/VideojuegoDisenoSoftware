package com.videogame.videojuegodissotfware.model.entities.state;

public class EstadoBasico implements EstadoEntidad{
    @Override
    public double modificarDano(int danoBase) {
        return danoBase;
    }
    @Override
    public EstadoEntidad actualizarEstado() {
        return this;
    }
    @Override
    public String getNombre() {
        return "Basico";
    }
}
