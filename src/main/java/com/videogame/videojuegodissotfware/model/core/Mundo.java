package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.core.combate.Combate;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactoryManager;
import com.videogame.videojuegodissotfware.model.items.Armadura;
import com.videogame.videojuegodissotfware.model.items.Espada;
import com.videogame.videojuegodissotfware.model.items.Item;
import com.videogame.videojuegodissotfware.model.items.Pocion;
import com.videogame.videojuegodissotfware.model.items.decorator.EncantamientoFortaleza;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Mundo {
    private ArrayList<Monstruo> listaMonstruos;
    private Personaje personaje;
    private ArrayList<Combate> listaCombates;
    private Mapa mapa;
    private long tiempoInicio;

    public Mundo(String nombrePersonaje, String tipoMapa) {
        this.listaCombates = new ArrayList<>();
        this.tiempoInicio = System.currentTimeMillis();
        inicializarMundo(nombrePersonaje, tipoMapa);
    }

    public void inicializarMundo(String nombrePersonaje, String tipoMapa) {
        this.personaje = generarPersonaje(nombrePersonaje);
        this.mapa = generarMapa(tipoMapa);
        this.listaMonstruos = generarMonstruos();

        this.mapa.setEnemigos(this.listaMonstruos);
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

    private Personaje generarPersonaje(String nombrePersonaje) {
        return new Personaje(nombrePersonaje, 150, 150, new EstadoBasico(), 60, 10,
                new Image(getClass().getResourceAsStream("/com/videogame/videojuegodissotfware/mapa/Personaje.png")),
                10, 10, new Equilibrada(),  1, 0);
    }

    public void eliminarMonstruo(Monstruo monstruo) {
        this.listaMonstruos.remove(monstruo);
        this.mapa.getEnemigos().remove(monstruo);

        System.out.println("DEBUG: " + monstruo.getNombre() + " eliminado del mundo y del mapa.");
    }

    private Mapa generarMapa(String tipoMapa) {
        return new Mapa(tipoMapa);
    }

    public void decorarEspada(int precio) {
        ArrayList<Item> inventario = personaje.getListaItems();

        for (int i = 0; i < inventario.size(); i++) {
            Item actual = inventario.get(i);
            if (actual instanceof Espada) {
                Item espadaEncantada = new EncantamientoFortaleza(actual);
                inventario.set(i, espadaEncantada);
                personaje.comprarItem(precio);
                i = inventario.size();
            }
        }
    }

    public void decorarArmadura(int precio) {
        ArrayList<Item> inventario = personaje.getListaItems();

        for (int i = 0; i < inventario.size(); i++) {
            Item actual = inventario.get(i);
            if (actual instanceof Armadura) {
                Item armaduraEncantada = new EncantamientoFortaleza(actual);
                inventario.set(i, armaduraEncantada);
                personaje.comprarItem(precio);
                i = inventario.size();
            }
        }
    }

    public void comprarPocion(int precio) {
        ArrayList<Item> inventario = personaje.getListaItems();
        inventario.add(new Pocion("Pocion"));
        personaje.comprarItem(precio);
    }

    public void gestionarCombate(Monstruo enemigo) {
        Combate combate = new Combate(enemigo, this.personaje);
        listaCombates.add(combate);
    }

    public void reiniciarMundo() {

    }

    public void pausar() {

    }

    public void comprobarResultado() {

    }

    public void finalizarPartida() {

    }

    public String getTiempoFormateado() {
        long tiempoActual = System.currentTimeMillis();
        long diferencia = tiempoActual - tiempoInicio;
        long segundosTotales = diferencia / 1000;
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public Combate getCombateActual() {
        return listaCombates.get(listaCombates.size() - 1);
    }

    public int getPrecioArma() {
        return new Espada("").getPrecio();
    }

    public int getPrecioArmadura() {
        return new Armadura("").getPrecio();
    }

    public int getPrecioPocion() {
        return new Pocion("").getPrecio();
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
