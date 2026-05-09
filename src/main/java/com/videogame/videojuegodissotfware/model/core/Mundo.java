package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.core.combate.Combate;
import com.videogame.videojuegodissotfware.model.entities.Entidad;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactoryManager;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Mundo {
    private ArrayList<Monstruo> listaMonstruos;
    private Personaje personaje;
    private ArrayList<Combate> listaCombates;
    private Mapa mapa;
    private long tiempoPartida;

    public Mundo(String nombrePersonaje, String tipoMapa) {
        this.listaCombates = new ArrayList<>();
        this.tiempoPartida = 0;

        inicializarMundo(nombrePersonaje, tipoMapa);
    }

    public void inicializarMundo(String nombrePersonaje, String tipoMapa) {
        this.personaje = generarPersonaje(nombrePersonaje);
        this.mapa = generarMapa(tipoMapa);
        this.listaMonstruos = generarMonstruos();

        this.mapa.setEnemigos(this.listaMonstruos);
    }

    private Personaje generarPersonaje(String nombrePersonaje) {
        return new Personaje(nombrePersonaje, 100, new EstadoBasico(), 15, 30,
                new Image(getClass().getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/Personaje.png")),
                10, 10, new Equilibrada(), 100, 1, 0);
    }

    private ArrayList<Monstruo> generarMonstruos() {
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        EnemigoFactoryManager enemigoFactoryManager = EnemigoFactoryManager.getInstance();
        System.out.println("DEBUG: Iniciando generación de monstruos...");

        if (enemigoFactoryManager.getEnemigoFactory() == null) {
            System.out.println("DEBUG: ¡ERROR! La Factory es NULL en el Manager");
            return monstruos;
        }

        double[] pos1 = mapa.getPosicionValidaAleatoria();
        System.out.println("DEBUG: Generando en " + pos1[0] + "," + pos1[1]);
        double[] pos2 = mapa.getPosicionValidaAleatoria();
        System.out.println("DEBUG: Generando en " + pos2[0] + "," + pos2[1]);
        double[] pos3 = mapa.getPosicionValidaAleatoria();
        System.out.println("DEBUG: Generando en " + pos3[0] + "," + pos3[1]);

        monstruos.add(enemigoFactoryManager.crearOrco(pos1[0], pos1[1]));
        monstruos.add(enemigoFactoryManager.crearMago(pos2[0], pos2[1]));
        monstruos.add(enemigoFactoryManager.crearEsqueleto(pos3[0], pos3[1]));

        System.out.println("DEBUG: Lista final tiene " + monstruos.size() + " monstruos");
        return monstruos;
    }

    private Mapa generarMapa(String tipoMapa) {
        return new Mapa(tipoMapa);
    }

    public void gestionarCombate(Monstruo enemigo) {
        Combate combate = new Combate(enemigo, this.personaje);
        listaCombates.add(combate);
    }

    public Combate getCombateActual() {
        return listaCombates.get(listaCombates.size() - 1);
    }

    public void reiniciarMundo() {

    }

    public void pausar() {

    }

    public void comprobarResultado() {

    }

    public void finalizarPartida() {

    }

    public String getTipoMapa() {
        return mapa.getTipoMapa();
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}
