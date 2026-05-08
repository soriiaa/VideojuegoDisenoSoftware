package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.core.state.EstadoPartida;
import com.videogame.videojuegodissotfware.model.core.state.MenuState;
import com.videogame.videojuegodissotfware.model.core.state.PlayState;

public class GameFacade {
    private static GameFacade instance;
    private Mundo mundo;
    private EstadoPartida estadoPartida;

    private GameFacade() {
        // El constructor es privado
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
    public void getPersonaje() {
        if (mundo != null) {
            mundo.getPersonaje();
        }
    }

    public Mundo getMundo() {
        return mundo;
    }
}