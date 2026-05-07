package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;

import java.util.ArrayList;

public class Mundo {
    private ArrayList<Monstruo> listaMonstruos;
    private Personaje personaje;
    private long tiempoPartida;
    private int[][] mapaBits = new int[15][24];

    public Mundo(String nombrePersonaje, String generoPersonaje) {
        this.listaMonstruos = new ArrayList<>();
        // this.personaje = new Personaje(nombrePersonaje, generoPersonaje);
        this.tiempoPartida = 0;
    }

    public void reiniciarMundo() {

    }

    public void pausar() {

    }

    public void comprobarResultado() {

    }

    public void finalizarPartida() {

    }
}
