package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.core.CalculadorDano;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Monstruo extends Entidad {
    private int botin;
    private int tipo; // 1 (mago), 2 (orco), 3 (esqueleto)
    private final int ENEMY_SIZE = 96;

    public Monstruo(String nombre, int puntosVida, int vidaMaxima, EstadoEntidad estado, int dano,
                    int resistencia, Image sprite, double x, double y,
                    EstrategiaCombate estrategiaCombate, int botin, int tipo) {
        super(nombre, puntosVida, vidaMaxima, estado, dano, resistencia, sprite, x, y, estrategiaCombate);
        this.botin = botin;
        this.tipo = tipo;
    }

    public int realizarTurno(Personaje personaje) {
        System.out.println("DEBUG: El " + getNombre() + " está pensando... (ESTRATEGIA: " + getEstrategiaCombate().getNombre() + ", ESTADO: " + getEstado().getNombre() + ")");

        Accion decision = decidirAccion();
        if (decision == Accion.ATACAR) {
            System.out.println(getNombre() + " decide atacar.");
            return atacar(personaje);
        } else {
            System.out.println(getNombre() + " decide protegerse.");
            return -proteger();
        }
    }

    public abstract Accion decidirAccion();

    @Override
    public int atacar(Entidad entidad) {
        CalculadorDano calc = CalculadorDano.getInstance();
        GameFacade facade = GameFacade.getInstance();
        int danoPotencial = calc.calcularAtaque(this.getDano(), this.getEstado(), this.getEstrategiaCombate());
        int defensaJugador = calc.calcularDefensa(entidad.getResistencia(), entidad.getEstrategiaCombate(), facade.getPersonaje().getArmadura());
        System.out.println("DEBUG: " + getNombre() + " ataca a " + entidad.getNombre() + " con daño potencial " + danoPotencial + " y defensa del jugador " + defensaJugador);

        int danoFinal = Math.max(0, danoPotencial - defensaJugador);
        entidad.setPuntosVida(entidad.getPuntosVida() - danoFinal);
        return danoFinal;
    }

    @Override
    public int proteger() {
        CalculadorDano calc = CalculadorDano.getInstance();
        int recuperado = calc.calcularVidaRecuperada(this.getVidaMaxima(), this.getEstrategiaCombate());

        int vidaAnterior = this.getPuntosVida();
        int vidaNueva = Math.min(this.getVidaMaxima(), vidaAnterior + recuperado);
        this.setPuntosVida(vidaNueva);
        System.out.println("DEBUG: " + getNombre() + " se protege y recupera " + (vidaNueva - vidaAnterior) + " puntos de vida (de " + vidaAnterior + " a " + vidaNueva + ")");

        return vidaNueva - vidaAnterior; // cantidad recuperada
    }

    public void render(GraphicsContext gc) {
        Image sprite = getSprite();
        if (sprite != null) {
            gc.drawImage(sprite, getX(), getY(), ENEMY_SIZE, ENEMY_SIZE);
        } else {
            gc.setFill(Color.MAGENTA); // Color chillón para debug
            gc.fillRect(getX(), getY(), 32, 32);
        }

    }

    public int getTipo() {
        return this.tipo;
    }

    public int getBotin() {
        return this.botin;
    }
}
