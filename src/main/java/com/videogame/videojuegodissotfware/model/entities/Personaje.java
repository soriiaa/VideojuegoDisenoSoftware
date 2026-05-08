package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.core.Mapa;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.items.Item;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Set;

public class Personaje extends Entidad {
    private int puntosVidaMax;
    private int nivel;
    private int oro;
    private ArrayList<Item> listaItems;
    private double speed = 1.0;
    private final double PLAYER_SIZE = 64.0;

    public Personaje(String nombre, int puntosVida, EstadoEntidad estado, int dano, int resistencia,
                     Image sprite, double x, double y, EstrategiaCombate estrategiaCombate,
                     int puntosVidaMax, int nivel, int oro) {
        super(nombre, puntosVida, estado, dano, resistencia, sprite, x, y, estrategiaCombate);
        this.puntosVidaMax = puntosVidaMax;
        this.nivel = nivel;
        this.oro = oro;
        this.listaItems = new ArrayList<>();
        try {
            this.setSprite(new Image(getClass().getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/Personaje.png")));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el sprite del jugador, se usará un cuadro azul.");
        }
    }

    public int update(Set<KeyCode> keys, Mapa map) {
        double nextX = getX();
        double nextY = getY();

        if (keys.contains(KeyCode.W)) {
            nextY -= speed;
        }
        if (keys.contains(KeyCode.S)) {
            nextY += speed;
        }
        if (keys.contains(KeyCode.A)) {
            nextX -= speed;
        }
        if (keys.contains(KeyCode.D)) {
            nextX += speed;
        }

        int tileCentro = map.getTileAt(nextX + PLAYER_SIZE/2, nextY + PLAYER_SIZE /2);

        // Si el tile NO es un obstáculo, movemos
        if (tileCentro != 2) {
            setX(nextX);
            setY(nextY);
        }

        int idEnemyCollision = -1;
        for (Monstruo monstruo : map.getEnemigos()) {
            // Comprobamos si el jugador toca al enemigo usando un rectángulo de colisión
            if (Math.abs(nextX - monstruo.getX()) < 32 && Math.abs(nextY - monstruo.getY()) < 32) {
                idEnemyCollision = monstruo.getType();
            }
        }
        return idEnemyCollision;
    }

    public void render(GraphicsContext gc) {
        if (getSprite() != null) {
            gc.drawImage(getSprite(), getX(), getY(), PLAYER_SIZE, PLAYER_SIZE);
        } else {
            // si falla imagen se pone un cuadro azul
            gc.setFill(Color.BLUE);
            gc.fillRect(getX(), getY(), PLAYER_SIZE, PLAYER_SIZE);
        }
    }

    public void usarPocion() {

    }

    public void comprarItem() {

    }

    public void iniciarCombate() {

    }



    public String getNombre() {
        return super.getNombre();
    }
    public int getPuntosVida() {
        return super.getPuntosVida();
    }
    public EstadoEntidad getEstado() {
        return super.getEstado();
    }

    public int getDano() {
        return super.getDano();
    }

    public int getResistencia() {
        return super.getResistencia();
    }

    public EstrategiaCombate getEstrategiaCombate() {
        return super.getEstrategiaCombate();
    }

    public int getPuntosVidaMax() {
        return puntosVidaMax;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public ArrayList<Item> getListaItems() {
        return listaItems;
    }

    public void setListaItems(ArrayList<Item> listaItems) {
        this.listaItems = listaItems;
    }
}