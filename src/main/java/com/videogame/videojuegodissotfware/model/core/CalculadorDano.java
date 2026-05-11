package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
import com.videogame.videojuegodissotfware.model.items.Item;
import com.videogame.videojuegodissotfware.model.strategies.EstrategiaCombate;

public class CalculadorDano {
    private static CalculadorDano instance;

    private CalculadorDano() {} // construct privado para Singelton

    public static CalculadorDano getInstance() {
        if (instance == null) {
            instance = new CalculadorDano();
        }
        return instance;
    }
    // metodo para el ataque del monstruo, que no tiene item espada, hacia el personaje
    public int calcularAtaque(int fuerzaBase, EstadoEntidad estado, EstrategiaCombate estrategia) {
        double danoConEstado = estado.modificarDano(fuerzaBase);
        double danoFinal = danoConEstado * estrategia.modificadorCombate();

        return (int) Math.round(danoFinal);
    }

    // metodo para el ataque del personaje (con su item, mejorado o no), hacia el monstruo
    public int calcularAtaque(int fuerzaBase, EstadoEntidad estado, EstrategiaCombate estrategia, Item espada) {

        int danoAdicional = 0;

        if (espada != null) {
            danoAdicional = espada.getValorEfecto();
        }

        double danoConEstado = estado.modificarDano(fuerzaBase + danoAdicional);
        double danoFinal = danoConEstado * estrategia.modificadorCombate();

        return (int) Math.round(danoFinal);
    }

    // metodo para calcular la defensa del monstruo (sin item armadura)
    public int calcularDefensa(int defensaBase, EstrategiaCombate estrategia) {
        return (int) Math.round(defensaBase * estrategia.modificadorDefensa());
    }

    // metodo para calcular la defensa del personaje (con su item, mejorado o no)
    public int calcularDefensa(int defensaBase, EstrategiaCombate estrategia, Item armadura) {

        int resistenciaExtra = 0;

        if (armadura != null) {
            resistenciaExtra = armadura.getValorEfecto();
        }

        return (int) Math.round((defensaBase + resistenciaExtra) * estrategia.modificadorDefensa());
    }

    public int calcularVidaRecuperada(int vidaMax, EstrategiaCombate estrategia) {
        double baseRecuperacion = vidaMax * 0.20;
        double recuperacionFinal = baseRecuperacion * estrategia.modificadorDefensa();
        System.out.println("DEBUG: Vida recuperada calculada: " + recuperacionFinal);

        return (int) Math.round(recuperacionFinal);
    }
}
