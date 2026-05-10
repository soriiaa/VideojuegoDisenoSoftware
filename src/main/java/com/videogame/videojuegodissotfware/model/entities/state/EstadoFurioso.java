package com.videogame.videojuegodissotfware.model.entities.state;

public class EstadoFurioso implements EstadoEntidad{
    private int turnosRestantes = 2;

    @Override
    public double modificarDano(int danoBase) {
        return danoBase * 1.2;
    }

    @Override
    public EstadoEntidad actualizarEstado() {
        turnosRestantes--;
        if (turnosRestantes > 0) {
            return this; // sigue furioso
        } else {
            System.out.println("DEBUG: El estado furioso ha terminado, el personaje vuelve a estado basico.");
            return new EstadoBasico(); // vuelve a estado basico
        }
    }

    @Override
    public String getNombre() {
        return "Furioso";
    }
}
