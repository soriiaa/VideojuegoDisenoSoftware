package com.videogame.videojuegodissotfware.model.core.combate;

import com.videogame.videojuegodissotfware.model.actions.Accion;
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

    public int ejecutarTurno(Accion accion) {
        if (estadoActual == FaseCombate.TURNO_JUGADOR) {
            estadoActual = FaseCombate.TURNO_ENEMIGO;
            switch (accion) {
                case ATACAR:
                    return personaje.atacar(enemigo);
                case PROTEGER:
                    personaje.proteger();
                    break;
                case USAR_POCION:
                    personaje.usarPocion();
                    break;
            }
        } else if (estadoActual == FaseCombate.TURNO_ENEMIGO) {
            enemigo.realizarTurno();
            estadoActual = FaseCombate.TURNO_JUGADOR;
            comprobarResultado();
        }
        return 0;
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
