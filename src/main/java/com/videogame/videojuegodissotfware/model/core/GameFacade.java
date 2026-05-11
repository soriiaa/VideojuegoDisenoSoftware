package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.core.combate.Combate;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactory;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactoryManager;
import com.videogame.videojuegodissotfware.model.factories.desierto.DesiertoFactory;
import com.videogame.videojuegodissotfware.model.factories.selva.PraderaFactory;

public class GameFacade {
    private static GameFacade instance;
    private Mundo mundo;
    private GameFacade() {}

    public static GameFacade getInstance() {
        if (instance == null) {
            instance = new GameFacade();
        }
        return instance;
    }

    public void inicializarNuevaPartida(String nombre, String tipoMapa) {
        inicializarFactory(tipoMapa);
        mundo = new Mundo(nombre, tipoMapa);
    }

    public void reiniciarPartida() {
        if (mundo != null) {
            String nombre = mundo.getPersonaje().getNombre();
            String tipoMapa = mundo.getTipoMapa();
            inicializarNuevaPartida(nombre, tipoMapa);
        }
    }

    public void finalizarPartida() {
        this.mundo = null;
        System.out.println("Partida finalizada");
    }

    public void iniciarCombate(Monstruo enemigo) {
        mundo.gestionarCombate(enemigo);
    }

    public int ejecutarTurnoJugador(Accion accion) {
        return mundo.getCombateActual().ejecutarTurnoJugador(accion);
    }

    public int ejecutarTurnoEnemigo() {
        return mundo.getCombateActual().ejecutarTurnoEnemigo();
    }

    public void mejorarArma(int precio) {
        mundo.decorarEspada(precio);
    }

    public void mejorarArmadura(int precio) {
        mundo.decorarArmadura(precio);
    }

    public void comprarPocion(int precio) {
        mundo.comprarPocion(precio);
    }


    public int[] getPreciosTienda() {
        int[] arrayPrecios = new int[3];

        arrayPrecios[0] = mundo.getPrecioArma();
        arrayPrecios[1] = mundo.getPrecioArmadura();
        arrayPrecios[2] = mundo.getPrecioPocion();

        return arrayPrecios;
    }

    public boolean comprobarDinero(int cantidad) {
        return mundo.getPersonaje().dineroDisponible(cantidad);
    }

    private void inicializarFactory(String tipoMapa) {
        EnemigoFactory factoryConcreta;
        if (tipoMapa.equalsIgnoreCase("Pradera")) {
            factoryConcreta = new PraderaFactory();
        } else {
            factoryConcreta = new DesiertoFactory();
        }
        // setteamos el tipo de factory para que en el mundo el manager sepa cual usar para crear los monstruos
        EnemigoFactoryManager.getInstance().setEnemigoFactory(factoryConcreta);
    }

    public String getTiempoFormateado() {
        return mundo.getTiempoFormateado();
    }

    public Combate getCombateActual() {
        return mundo.getCombateActual();
    }

    public Mundo getMundo() {
        return mundo;
    }

    public Personaje getPersonaje() {
        if (mundo != null) {
            return mundo.getPersonaje();
        } else {
            return null;
        }
    }
}