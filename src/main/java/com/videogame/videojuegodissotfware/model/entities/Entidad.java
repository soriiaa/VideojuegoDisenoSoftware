package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.image.Image;

public abstract class Entidad {

    private EstadoEntidad estado;
    private EstrategiaCombate estrategiaCombate;
    private String nombre;
    private int puntosVida;
    private int vidaMaxima;
    private int dano;
    private int resistencia;
    private Image sprite;
    private double x, y;

    public Entidad (String nombre, int puntosVida, int vidaMaxima, EstadoEntidad estado, int dano, int resistencia,
                    Image sprite, double x, double y, EstrategiaCombate estrategiaCombate) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.vidaMaxima = vidaMaxima;
        this.estado = estado;
        this.dano = dano;
        this.resistencia = resistencia;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.estrategiaCombate = estrategiaCombate;
    }

    public Image getSprite() {
        return sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public EstadoEntidad getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntidad estado) {
        this.estado = estado;
    }

    public int getDano() {
        return dano;
    }

    public int getResistencia() {
        return resistencia;
    }

    public EstrategiaCombate getEstrategiaCombate() {
        return estrategiaCombate;
    }

    /**
     *
     * @param entidad
     * @return El daño que se le ha inflingido al enemigo.
     */
    public abstract int atacar(Entidad entidad);
    public abstract int proteger();
}
