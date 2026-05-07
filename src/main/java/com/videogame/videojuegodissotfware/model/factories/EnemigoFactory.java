package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Dragon;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Mago;
import com.videogame.videojuegodissotfware.model.entities.monstruos.Orco;

//SimpleFactory
public class EnemigoFactory {

    // Método estático para no tener que instanciar la fábrica
    public static Monstruo crearEnemigo(String tipo) {

        if (tipo.equalsIgnoreCase("Mago")) {
            return new Mago();

        } else if (tipo.equalsIgnoreCase("Orco")) {
            return new Orco();

        }
        else if (tipo.equalsIgnoreCase("Esqueleto")) {
            return new Esqueleto();

        }
        else if (tipo.equalsIgnoreCase("Dragon")) {
            return new Dragon();

        }

        throw new IllegalArgumentException("El monstruo tipo " + tipo + " no existe en la fábrica.");
    }
}