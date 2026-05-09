package com.videogame.videojuegodissotfware.model.core.combate;

import com.videogame.videojuegodissotfware.model.entities.Entidad;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;

import java.util.Random;

public class Combate {
    private Monstruo enemigo;
    private Personaje personaje;
    private Entidad ganador;
    private FaseCombate estadoActual;

    public Combate (Monstruo enemigo, Personaje personaje) {
        this.enemigo = enemigo;
        this.personaje = personaje;
        this.estadoActual = determinarTurnoInicial();
    }

    public Entidad ejecutarTurno() {
        if (estadoActual == FaseCombate.TURNO_JUGADOR) {

        } else if (estadoActual == FaseCombate.TURNO_ENEMIGO) {
            enemigo.realizarTurno();
            estadoActual = FaseCombate.TURNO_JUGADOR;
            comprobarResultado();
            return ganador;
        }
        return null;
    }

    public void finalizarCombate() {

    }

    public void comprobarResultado() {
        if (enemigo.getPuntosVida() <= 0) {
            ganador = personaje;
        } else if (personaje.getPuntosVida() <= 0) {
            ganador = enemigo;
        }
    }

    private FaseCombate determinarTurnoInicial() {
        Random rand = new Random();
        return rand.nextBoolean() ? FaseCombate.TURNO_JUGADOR : FaseCombate.TURNO_ENEMIGO;
    }

    public void setEnemigo(Monstruo enemigo) {
        this.enemigo = enemigo;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    private void setGanador(Entidad entidadGanadora) {
        this.ganador = entidadGanadora;
    }
}
