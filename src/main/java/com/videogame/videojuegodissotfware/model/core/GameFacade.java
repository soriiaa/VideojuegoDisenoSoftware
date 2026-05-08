package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.core.state.EstadoPartida;
import com.videogame.videojuegodissotfware.model.core.state.MenuState;
import com.videogame.videojuegodissotfware.model.core.state.PlayState;
import com.videogame.videojuegodissotfware.model.entities.Personaje;

public class GameFacade {
    private static GameFacade instance;
    private Mundo mundo;
    private EstadoPartida estadoPartida;

    private GameFacade() {
        this.estadoPartida = new MenuState();
    }

    public static GameFacade getInstance() {
        if (instance == null) {
            instance = new GameFacade();
        }
        return instance;
    }

    // Método para que LaunchController configure al héroe
    public void inicializarNuevaPartida(String nombre, String genero) {
        this.mundo = new Mundo(nombre);
        setEstado(new PlayState());
    }

    public void setEstado(EstadoPartida nuevoEstado) {
        this.estadoPartida = nuevoEstado;
    }
    public Personaje getPersonaje() {
        if (mundo != null) {
            return mundo.getPersonaje();
        } else {
            return null;
        }
    }

    public Mundo getMundo() {
        return mundo;
    }
}