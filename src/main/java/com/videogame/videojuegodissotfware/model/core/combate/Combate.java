package com.videogame.videojuegodissotfware.model.core.combate;

import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.entities.Entidad;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoAturdido;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoFurioso;

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

    public int ejecutarTurnoJugador(Accion accion) {
        int resultado = 0;
        if (estadoActual == FaseCombate.TURNO_JUGADOR) {
            switch (accion) {
                case ATACAR:
                    resultado = personaje.atacar(enemigo);
                    personaje.setEstado(personaje.getEstado().actualizarEstado());
                    comprobarCambioEstado(enemigo, resultado);
                    break;
                case PROTEGER:
                    resultado = personaje.proteger();
                    break;
                case USAR_POCION:
                    resultado = personaje.usarPocion();
                    break;
            }
            comprobarResultado();

            // si el combate sigue, cambiamos el estado pero NO ejecutamos al enemigo aún
            if (estadoActual != FaseCombate.FINALIZADO) {
                estadoActual = FaseCombate.TURNO_ENEMIGO;
            }
        }
        return resultado;
    }

    public int ejecutarTurnoEnemigo() {

        if (estadoActual == FaseCombate.TURNO_ENEMIGO) {
            int resultadoEnemigo = enemigo.realizarTurno(personaje);
            enemigo.setEstado(enemigo.getEstado().actualizarEstado());
            comprobarCambioEstado(personaje, resultadoEnemigo);
            comprobarResultado();

            if (estadoActual != FaseCombate.FINALIZADO) {
                estadoActual = FaseCombate.TURNO_JUGADOR;
            }
            return resultadoEnemigo;
        }
        return 0;
    }

    // se puede cambiar de estado una entidad cuando es atacada
    private void comprobarCambioEstado(Entidad victima, int danoRecibido) {
        // solo permitimos cambio si esta en basico para no mezclar estados
        if (victima.getEstado() instanceof EstadoBasico) {
            double azar = Math.random();

            // 30% de probabilidad de Furioso, solo si el daño recibido es mayor a 20
            if (danoRecibido > 20 && azar < 0.30) {
                victima.setEstado(new EstadoFurioso());
                System.out.println("DEBUG: " + victima.getNombre() + " ha entrado en estado FURIOSO");
            } else if (azar < 0.10) { // 10% de probabilidad de quedar Aturdido por el golpe
                victima.setEstado(new EstadoAturdido());
                System.out.println("DEBUG: " + victima.getNombre() + " ha quedado ATURDIDO");
            } else {
                System.out.println("DEBUG: " + victima.getNombre() + " no cambia de estado tras el ataque");
            }
        }
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
