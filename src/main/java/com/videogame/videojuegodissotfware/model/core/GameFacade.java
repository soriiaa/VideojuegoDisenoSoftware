package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.core.combate.Combate;
import com.videogame.videojuegodissotfware.model.core.state.EstadoPartida;
import com.videogame.videojuegodissotfware.model.core.state.MenuState;
import com.videogame.videojuegodissotfware.model.core.state.PauseState;
import com.videogame.videojuegodissotfware.model.core.state.PlayState;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.Personaje;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactory;
import com.videogame.videojuegodissotfware.model.factories.EnemigoFactoryManager;
import com.videogame.videojuegodissotfware.model.factories.desierto.DesiertoFactory;
import com.videogame.videojuegodissotfware.model.factories.selva.PraderaFactory;
import javafx.scene.input.KeyCode;

public class GameFacade {
    private static GameFacade instance;
    private Mundo mundo;
    private EstadoPartida estadoPartida;

    private GameFacade() {
        this.estadoPartida = new MenuState();
    }

    public static GameFacade getInstance() {
        if (instance == null) {
            instance = new GameFacade();
        }
        return instance;
    }

    public void inicializarNuevaPartida(String nombre, String tipoMapa) {
        inicializarFactory(tipoMapa);
        mundo = new Mundo(nombre, tipoMapa);
        setEstado(new PlayState());
    }

    public void reiniciarPartida() {

    }

    public void finalizarPartida() {

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

    public void procesarInput(KeyCode tecla) {
        this.estadoPartida.manejarInput(tecla, this);
    }

    public void pausarPartida() {
        setEstado(new PauseState()); // Aqui hay que pausar el tiempo y eso
        mundo.pausar();
    }

    public void mejorarArma(int precio) {
        mundo.decorarItem(precio);
    }

    public void mejorarArmadura(int precio) {
        mundo.decorarItem(precio);
    }

    public void comprarPocion(int precio) {
        mundo.comprarPocion(precio);
    }

    /**
     *
     * @return 1 - Arma, 2 - Armadura, 3 - Poción
     */
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

    public Combate getCombateActual() {
        return mundo.getCombateActual();
    }

    public String getTipoMapa() {
        return mundo.getTipoMapa();
    }

    public void setEstado(EstadoPartida nuevoEstado) {
        this.estadoPartida = nuevoEstado;
    }

    public Personaje getPersonaje() {
        if (mundo != null) {
            return mundo.getPersonaje();
        } else {
            return null;
        }
    }

    public Mundo getMundo() {
        return mundo;
    }
}