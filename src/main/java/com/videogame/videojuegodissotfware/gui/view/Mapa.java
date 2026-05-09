package com.videogame.videojuegodissotfware.gui.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapa {
    private int[][] mapa;
    private Map<Integer, Image> tileset = new HashMap<>();
    private List<Enemy> enemigos = new ArrayList<>();
    private final int TILE_SIZE = 32;

    public Mapa() {
        cargarPixeles();
        // crgamos el mapa usando getResourceAsStream para que funcione dentro del JAR
        this.mapa = leerTxt("/com/videogame/videojuegodissotfware/mapa/mapaJungla.txt");
        procesarEnemigos();
    }

    private void cargarPixeles() {
        String pathNaturaleza = "/com/videogame/videojuegodissotfware/mapa/assetsJungla/";

        // Estos son todos los IDs que existen en tu imagen
        int[] nuevosTiles = {
                0,1,2,3,4,6,7,9,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,33,34,35
        };

        // Bucle que carga todos los tiles de golpe
        for (int id : nuevosTiles) {
            tileset.put(id, new Image(getClass().getResourceAsStream(pathNaturaleza + "tile" + id + ".png")));
        }
    }

    private int[][] leerTxt(String ruta) {
        List<int[]> lineas = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream(ruta);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividimos la línea por espacios
                String[] numeros = linea.trim().split("\\s+");
                int[] fila = new int[numeros.length];
                for (int i = 0; i < numeros.length; i++) {
                    fila[i] = Integer.parseInt(numeros[i]);
                }
                lineas.add(fila);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de mapa: " + e.getMessage());
            // mapa de emergencia por si falla la lectura
            return new int[][]{{0}};
        }
        return lineas.toArray(new int[0][]);
    }
    private void procesarEnemigos() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                int id = mapa[i][j];
                // Si el ID es 8, 9 o 10, creamos un objeto enemigo
                if (id >= 8 && id <= 10) {
                    enemigos.add(new Enemy(id, j * TILE_SIZE, i * TILE_SIZE, tileset.get(id)));
                    mapa[i][j] = 0;
                }
            }
        }
    }

    public void render(GraphicsContext gc) {
        gc.setImageSmoothing(false); // Pixel Art nítido

        // Obtenemos el tamaño actual del Canvas para saber hasta dónde dibujar
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        // Calculamos cuántas filas y columnas necesitamos para llenar el 100%
        int cols = (int) Math.ceil(canvasWidth / TILE_SIZE);
        int rows = (int) Math.ceil(canvasHeight / TILE_SIZE);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Obtenemos el ID del mapa, si nos pasamos del array, ponemos suelo (ID 0)
                int id = 0;
                if (i < mapa.length && j < mapa[i].length) {
                    id = mapa[i][j];
                }

                Image img = tileset.get(id);

                // Dibujar siempre suelo de fondo (ID 0) para evitar huecos negros
                gc.drawImage(tileset.get(0), j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                if (img != null && id != 0) {
                    gc.drawImage(img, j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
        for (Enemy e : enemigos) {
            e.render(gc);
        }
    }

    public int getTileAt(double x, double y) {
        int col = (int) (x / TILE_SIZE);
        int fila = (int) (y / TILE_SIZE);

        if (fila >= 0 && fila < mapa.length && col >= 0 && col < mapa[0].length) {
            return mapa[fila][col];
        }
        return -1;
    }

    public int getTileSize() { return TILE_SIZE; }
    public List<Enemy> getEnemigos() { return enemigos; }
    public int getAnchoMapa() { return mapa[0].length * TILE_SIZE; }
    public int getAltoMapa() { return mapa.length * TILE_SIZE; }
}