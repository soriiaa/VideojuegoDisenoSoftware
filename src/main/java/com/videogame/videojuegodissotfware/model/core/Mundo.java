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
    private ArrayList<Combate> listaCombates;
    private Mapa mapa;
    private long tiempoPartida;

    public Mundo(String nombrePersonaje, String tipoMapa) {

        this.listaMonstruos = new ArrayList<>();
        this.listaCombates = new ArrayList<>();
        this.tiempoPartida = 0;

        inicializarMundo(nombrePersonaje, tipoMapa);
    }

    public void inicializarMundo(String nombrePersonaje, String tipoMapa) {
        this.personaje = generarPersonaje(nombrePersonaje);
        this.mapa = generarMapa(tipoMapa);
        this.listaMonstruos = generarMonstruos();
    }

    private Personaje generarPersonaje(String nombrePersonaje) {
        return new Personaje(nombrePersonaje, 100, new EstadoBasico(), 15, 30,
                new Image(getClass().getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/Personaje.png")),
                10, 10, new Equilibrada(), 100, 1, 0);
    }

    private ArrayList<Monstruo> generarMonstruos() {
        return null;
    }

    private Mapa generarMapa(String tipoMapa) {
        return new Mapa(tipoMapa);
    }

    public void crearCombate() {
        // Combate combate = new Combate();
    }

    public void reiniciarMundo() {

    }

    public void pausar() {

    }

    public void comprobarResultado() {

    }

    public void finalizarPartida() {

    }

    public String getTipoMapa() {
        return mapa.getTipoMapa();
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}
