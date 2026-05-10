package com.videogame.videojuegodissotfware.model.core;

import com.videogame.videojuegodissotfware.model.entities.state.EstadoEntidad;
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

    public int calcularAtaque(int fuerzaBase, EstadoEntidad estado, EstrategiaCombate estrategia) {
        double danoConEstado = estado.modificarDano(fuerzaBase);
        double danoFinal = danoConEstado * estrategia.modificadorCombate();

        return (int) Math.round(danoFinal);
    }

    public int calcularDefensa(int defensaBase, EstrategiaCombate estrategia) {
        return (int) Math.round(defensaBase * estrategia.modificadorDefensa());
    }

    public int calcularVidaRecuperada(int vidaMax, EstrategiaCombate estrategia) {
        double baseRecuperacion = vidaMax * 0.20;
        double recuperacionFinal = baseRecuperacion * estrategia.modificadorDefensa();
        System.out.println("DEBUG: Vida recuperada calculada: " + recuperacionFinal);

        return (int) Math.round(recuperacionFinal);
    }
}
