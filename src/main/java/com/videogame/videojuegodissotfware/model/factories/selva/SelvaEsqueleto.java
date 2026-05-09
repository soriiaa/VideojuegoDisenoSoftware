package com.videogame.videojuegodissotfware.model.factories.selva;

import com.videogame.videojuegodissotfware.model.entities.monstruos.Esqueleto;
import com.videogame.videojuegodissotfware.model.entities.state.EstadoBasico;
import com.videogame.videojuegodissotfware.model.strategies.Equilibrada;
import javafx.scene.image.Image;

public class SelvaEsqueleto extends Esqueleto {
    public SelvaEsqueleto(double x, double y){
        super("Esqueleto de la Selva",
                100,
                new EstadoBasico(),
                30,
                20,
                new Image(SelvaEsqueleto.class.getResourceAsStream("/com/videogame/videojuegodissotfware/images/esqueleto.png")),
                x,
                y,
                new Equilibrada(),
                100,
                3
        );
    }
}
