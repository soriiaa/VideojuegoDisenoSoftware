package com.videogame.videojuegodissotfware.model.core.combate;

public enum FaseCombate {
    TURNO_JUGADOR,
    TURNO_ENEMIGO,
    PROCESANDO_ACCION, // Breve pausa mientras se ve la animación/daño
    FINALIZADO
}