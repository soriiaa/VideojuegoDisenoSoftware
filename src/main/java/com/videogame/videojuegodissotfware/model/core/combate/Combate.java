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
        // el turno de quien empieza se decide aleatoriamente
        //this.estadoActual = quienEmpieza();
        this.estadoActual = FaseCombate.TURNO_JUGADOR;
    }

    public FaseCombate quienEmpieza() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            System.out.println("Empieza el jugador");
            return FaseCombate.TURNO_JUGADOR;
        } else {
            System.out.println("Empieza el enemigo");
            return FaseCombate.TURNO_ENEMIGO;
        }
    }

    public int ejecutarTurno(Accion accion) {
        int danoRealizado = 0;
        int vidaAumentada = 0;
        if (estadoActual == FaseCombate.TURNO_JUGADOR) {
            switch (accion) {
                case ATACAR:
                    danoRealizado = personaje.atacar(enemigo);
                    break;
                case PROTEGER:
                    vidaAumentada = personaje.proteger();
                    break;
                case USAR_POCION:
                    personaje.usarPocion();
                    break;
            }
            comprobarResultado();

            // si no ha terminado, pasamos el turno al enemigo
            if (estadoActual != FaseCombate.FINALIZADO) {
                estadoActual = FaseCombate.TURNO_ENEMIGO;

                enemigo.realizarTurno();
                comprobarResultado();

                if (estadoActual != FaseCombate.FINALIZADO) {
                    estadoActual = FaseCombate.TURNO_JUGADOR;
                }
            }
        }
        return switch (accion) {
            case ATACAR -> danoRealizado;
            case PROTEGER, USAR_POCION -> vidaAumentada;
        };
    }

    public void finalizarCombate() {

    }

    public void comprobarResultado() {
        if (enemigo.getPuntosVida() <= 0) {
            ganador = personaje;
            estadoActual = FaseCombate.FINALIZADO;
        } else if (personaje.getPuntosVida() <= 0) {
            ganador = enemigo;
            estadoActual = FaseCombate.FINALIZADO;
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

    public FaseCombate getEstadoActual() {
        return estadoActual;
    }
}
