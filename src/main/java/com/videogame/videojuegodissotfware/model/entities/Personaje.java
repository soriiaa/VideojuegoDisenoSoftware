package com.videogame.videojuegodissotfware.model.entities;

import com.videogame.videojuegodissotfware.model.core.CalculadorDano;
import com.videogame.videojuegodissotfware.model.core.Mapa;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.items.Armadura;
import com.videogame.videojuegodissotfware.model.items.Espada;
import com.videogame.videojuegodissotfware.model.items.Item;
import com.videogame.videojuegodissotfware.model.items.Pocion;
import com.videogame.videojuegodissotfware.model.items.decorator.ItemDecorator;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Set;

public class Personaje extends Entidad {
    private int nivel;
    private int oro;
    private ArrayList<Item> listaItems;
    private double speed = 1.0;
    private final double PLAYER_SIZE = 64.0;

    public Personaje(String nombre, int puntosVida, int vidaMaxima, EstadoEntidad estado, int dano, int resistencia,
                     Image sprite, double x, double y, EstrategiaCombate estrategiaCombate,
                     int nivel, int oro) {
        super(nombre, puntosVida, vidaMaxima, estado, dano, resistencia, sprite, x, y, estrategiaCombate);
        this.nivel = nivel;
        this.oro = oro;
        this.listaItems = new ArrayList<>();
        inicializarInventario();
        try {
            this.setSprite(new Image(getClass().getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/Personaje.png")));
        } catch (Exception e) {
            System.out.println("No se pudo cargar el sprite del jugador, se usará un cuadro azul.");
        }
    }

    public int atacar(Entidad enemigo) {
        CalculadorDano calc = CalculadorDano.getInstance();

        int danoPotencial = calc.calcularAtaque(this.getDano(), this.getEstado(), this.getEstrategiaCombate()); // segun estrategia y estado (y daño base)
        int defensaJugador = calc.calcularDefensa(enemigo.getResistencia(), enemigo.getEstrategiaCombate()); // segun estrategia y resistencia del enemigo

        System.out.println("DEBUG: " + getNombre() + " ataca a " + enemigo.getNombre() + " con daño potencial " + danoPotencial + " y defensa del enemigo " + defensaJugador);

        int danoFinal = Math.max(0, danoPotencial - defensaJugador);
        enemigo.setPuntosVida(enemigo.getPuntosVida() - danoFinal);

        return danoFinal;
    }

    public int proteger() {
        CalculadorDano calc = CalculadorDano.getInstance();
        int recuperado = calc.calcularVidaRecuperada(this.getPuntosVidaMax(), this.getEstrategiaCombate());

        int vidaAnterior = this.getPuntosVida();
        int vidaNueva = Math.min(this.getPuntosVidaMax(), vidaAnterior + recuperado);
        this.setPuntosVida(vidaNueva);
        System.out.println("DEBUG: " + getNombre() + " se protege y recupera " + (vidaNueva - vidaAnterior) + " puntos de vida (de " + vidaAnterior + " a " + vidaNueva + ")");

        return vidaNueva - vidaAnterior; // cantidad recuperada
    }

    /**
     * @param cantidad
     * @return Retorna true si hay dinero, false si no.
     */
    public boolean dineroDisponible(int cantidad) {
        if (this.oro - cantidad >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void gastarDinero(int cantidad) {
        this.oro -= cantidad;
    }

    public int usarPocion() {
        for (Item item : getListaItems()) {
            if (item instanceof Pocion) {
                int vidaAnterior = this.getPuntosVida();
                this.setPuntosVida(Math.min(this.getPuntosVida() + item.getValorEfecto(), getVidaMaxima()));
                int vidaRestaurada = this.getPuntosVida() - vidaAnterior;
                this.listaItems.remove(item);
                System.out.println("DEBUG: " + getNombre() + " usa una poción y recupera " + vidaRestaurada + " vida.");
                return vidaRestaurada;
            }
        }
        return 0;
    }

    public void comprarItem(int precio) {
        this.oro -= precio;
    }

    public boolean tieneArmaMejorada() {
        for (Item item : listaItems) {
            Item base = item.getObjetoBase();
            if (base instanceof Espada && item instanceof ItemDecorator) {
                return true;
            }
        }
        return false;
    }

    public boolean tieneArmaduraMejorada() {
        for (Item item : listaItems) {
            Item base = item.getObjetoBase();
            if (base instanceof Armadura && item instanceof ItemDecorator) {
                return true;
            }
        }
        return false;
    }

    public boolean tienePocion() {
        for (Item item : listaItems) {
            if (item instanceof Pocion) {
                return true;
            }
        }
        return false;
    }

    private void inicializarInventario() {
        Item espadaBasica = new Espada("Espada Básica");
        Item armaduraBasica = new Armadura("Armadura Básica");
        this.getListaItems().add(espadaBasica);
        this.getListaItems().add(armaduraBasica);
    }

    public Monstruo update(Set<KeyCode> keys, Mapa map) {
        double nextX = getX();
        double nextY = getY();

        if (keys.contains(KeyCode.W)) nextY -= speed;
        if (keys.contains(KeyCode.S)) nextY += speed;
        if (keys.contains(KeyCode.A)) nextX -= speed;
        if (keys.contains(KeyCode.D)) nextX += speed;

        //hitbox personaje
        double margenX = 20.0;
        double margenYTop = 32.0;
        double margenYBot = 5.0;

        //colisiones esquinas
        boolean chocaArribaIzquierda = map.esColision(nextX + margenX, nextY + margenYTop);
        boolean chocaArribaDerecha = map.esColision(nextX + PLAYER_SIZE - margenX, nextY + margenYTop);
        boolean chocaAbajoIzquierda = map.esColision(nextX + margenX, nextY + PLAYER_SIZE - margenYBot);
        boolean chocaAbajoDerecha = map.esColision(nextX + PLAYER_SIZE - margenX, nextY + PLAYER_SIZE - margenYBot);

        if (!chocaArribaIzquierda && !chocaArribaDerecha && !chocaAbajoIzquierda && !chocaAbajoDerecha) {
            setX(nextX);
            setY(nextY);
        }

        //colisiones monstruos
        for (Monstruo monstruo : map.getEnemigos()) {
            if (Math.abs(getX() - monstruo.getX()) < 32 && Math.abs(getY() - monstruo.getY()) < 32) {
                return monstruo;
            }
        }

        return null;
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
        return super.getVidaMaxima();
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