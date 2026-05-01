package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;

import java.util.ArrayList;

public abstract class Mundo {
    private ArrayList<Monstruo> listaMonstruos;
    private Personaje personaje;
    private long tiempoPartida;
    private int[][] mapaBits = new int[15][24];

    public void reiniciarMundo() {

    }

    public void pausar() {

    }

    public void comprobarResultado() {

    }

    public void finalizarPartida() {

    }
}
