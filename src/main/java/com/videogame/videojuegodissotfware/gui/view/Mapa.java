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
        this.mapa = leerTxt("/com/videogame/videojuegodissotfware/mapa/mapaDesierto.txt");
        procesarEnemigos();
    }

    private void cargarPixeles() {
        try {
            // --- ASSETS DEL DESIERTO (en subcarpeta assetsDesierto) ---
            String pathDesierto = "/com/videogame/videojuegodissotfware/mapa/assetsDesierto/";

            // Suelo base y variantes (basados en image_ab78fd.jpg)
            tileset.put(0, new Image(getClass().getResourceAsStream(pathDesierto + "suelo.png")));
            tileset.put(1, new Image(getClass().getResourceAsStream(pathDesierto + "tile1.png")));
            tileset.put(2, new Image(getClass().getResourceAsStream(pathDesierto + "tile2.png")));
            tileset.put(5, new Image(getClass().getResourceAsStream(pathDesierto + "tile5.png")));
            tileset.put(16, new Image(getClass().getResourceAsStream(pathDesierto + "tile16.png")));
            tileset.put(28, new Image(getClass().getResourceAsStream(pathDesierto + "tile28.png")));
            tileset.put(30, new Image(getClass().getResourceAsStream(pathDesierto + "tile30.png")));

            // Decoración
            tileset.put(45, new Image(getClass().getResourceAsStream(pathDesierto + "tile45.png")));
            tileset.put(48, new Image(getClass().getResourceAsStream(pathDesierto + "tile48.png")));
            tileset.put(61, new Image(getClass().getResourceAsStream(pathDesierto + "tile61.png")));

            // Estructuras y Muros (IDs del 126 al 185)
            int[] castillo = {126, 127, 128, 129, 140,141, 142, 143, 154, 155, 156, 157, 168, 169, 170, 171, 182, 183, 184, 185};
            for (int id : castillo) {
                tileset.put(id, new Image(getClass().getResourceAsStream(pathDesierto + "tile" + id + ".png")));
            }

            // --- MONSTRUOS (en carpeta mapa) ---
            String pathMapa = "/com/videogame/videojuegodissotfware/mapa/";
            tileset.put(8, new Image(getClass().getResourceAsStream(pathMapa + "mago.png")));
            tileset.put(9, new Image(getClass().getResourceAsStream(pathMapa + "ogro.png")));
            tileset.put(10, new Image(getClass().getResourceAsStream(pathMapa + "esqueleto.png")));

        } catch (Exception e) {
            System.err.println("Error cargando imágenes: " + e.getMessage());
            e.printStackTrace();
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