package com.videogame.videojuegodissotfware.model.entities.state;

public interface EstadoEntidad {
    double modificarDano(int danoBase); // el estado puede modificar el daño que hace (furioso, basico, aturdido)
    String getNombre();
}