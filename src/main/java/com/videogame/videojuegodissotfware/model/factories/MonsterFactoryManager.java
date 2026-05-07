package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;


public class MonsterFactoryManager {
    private static MonsterFactoryManager instance;

    private EnemigoFactory abstractMonsterFactory;

    private MonsterFactoryManager() {
    }

    public static MonsterFactoryManager getInstance() {
        if (instance == null) {
            instance = new MonsterFactoryManager();
        }
        return instance;
    }

    public void setEnemigoFactory(EnemigoFactory factory) {
        this.abstractMonsterFactory = factory;
    }


    public Monstruo createOrco() {
        return abstractMonsterFactory.crearOrco();
    }

    public Monstruo createMago() {
        return abstractMonsterFactory.crearMago();
    }

    public Monstruo createEsqueleto() {
        return abstractMonsterFactory.crearEsqueleto();
    }

    public Monstruo createDragon() {
        return abstractMonsterFactory.crearDragon();
    }
}
