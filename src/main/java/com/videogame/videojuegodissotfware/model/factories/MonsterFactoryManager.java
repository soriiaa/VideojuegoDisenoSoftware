package com.videogame.videojuegodissotfware.model.factories;

import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import java.util.List;

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

    public Monstruo crearOgro() {
        return abstractMonsterFactory.crearOrco();
    }
    public Monstruo crearMago() {
        return abstractMonsterFactory.crearMago();
    }
    public Monstruo crearEsqueleto() {
        return abstractMonsterFactory.crearEsqueleto();
    }
}