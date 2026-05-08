package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.core.state.EstadoPartida;
import com.videogame.videojuegodissotfware.model.core.state.MenuState;
import com.videogame.videojuegodissotfware.model.core.state.PauseState;
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

    public void inicializarNuevaPartida(String nombre, String tipoMapa) {
        mundo = new Mundo(nombre, tipoMapa);
        setEstado(new PlayState());
    }

    public void iniciarCombate(int idEnemigo) {
        // Aqui hay que llamar a mundo.crearCombate(idEnemigo);

    }

    public void pausarPartida() {
        setEstado(new PauseState()); // Aqui hay que pausar el tiempo y eso
        mundo.pausar();
    }

    public String getTipoMapa() {
        return mundo.getTipoMapa();
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