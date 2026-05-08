package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Mundo {
    private ArrayList<Monstruo> listaMonstruos;
    private Personaje personaje;
    private long tiempoPartida;
    private int[][] mapaBits = new int[15][24];

    public Mundo(String nombrePersonaje) {
        this.listaMonstruos = new ArrayList<>();
        this.personaje = new Personaje(
                nombrePersonaje,
                100,
                new EstadoBasico(),
                15,
                30,
                new Image(getClass().getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/Personaje.png")),
                10,
                10,
                new Equilibrada(),
                100,
                1,
                0);
        this.tiempoPartida = 0;
    }

    public Personaje getPersonaje() {
        return personaje;
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
