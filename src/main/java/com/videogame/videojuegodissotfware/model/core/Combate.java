package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.entities.Entidad;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;

public class Combate {
    private Monstruo monstruo;
    private Personaje personaje;
    private Entidad ganador;

    public void setMonstruo(Monstruo monstruo) {
        this.monstruo = monstruo;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public void finalizarCombate() {

    }

    public void comprobarResultado() {

    }

    private void setGanador(Entidad entidadGanadora) {
        this.ganador = entidadGanadora;
    }
}
