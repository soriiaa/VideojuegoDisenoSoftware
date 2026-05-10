package com.videogame.videojuegodissotfware.model.entities.state;

public interface EstadoEntidad {
    double modificarDano(int danoBase); // el estado puede modificar el daño que hace (furioso, basico, aturdido)
    EstadoEntidad actualizarEstado(); // el estado puede actualizarse a si mismo (por ejemplo, el estado aturdido se actualiza a basico despues de un turno)
    String getNombre();
}