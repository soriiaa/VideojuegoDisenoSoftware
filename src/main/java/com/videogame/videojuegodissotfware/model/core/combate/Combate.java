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
        this.estadoActual = FaseCombate.TURNO_JUGADOR;
    }

    public void ejecutarTurno() {
        if (estadoActual == FaseCombate.TURNO_JUGADOR) {
            personaje.atacar();
        } else if (estadoActual == FaseCombate.TURNO_ENEMIGO) {
            enemigo.realizarTurno();
            estadoActual = FaseCombate.TURNO_JUGADOR;
            comprobarResultado();
        }
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

    public Monstruo getEnemigo() {
        return this.enemigo;
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
