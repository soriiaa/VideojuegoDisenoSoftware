package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameEventListener;
import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import com.videogame.videojuegodissotfware.model.core.Mundo;
import com.videogame.videojuegodissotfware.model.core.combate.Combate;
import com.videogame.videojuegodissotfware.model.core.combate.FaseCombate;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import static com.videogame.videojuegodissotfware.model.actions.Accion.*;

public class FightController {
    @FXML
    private Label enemyName;
    @FXML
    private Label enemyHp;
    @FXML
    private Label enemyRes;
    @FXML
    private Label enemyDamage;
    @FXML
    private ImageView enemyImage;
    @FXML
    private Label enemyState;
    @FXML
    private Label enemyStrategy;
    @FXML
    private Label enemyReward;
    @FXML
    private Button atackBtn;
    @FXML
    private Button protectBtn;
    @FXML
    private Button potionBtn;
    @FXML
    private ScrollPane scrollLog;
    @FXML
    private VBox vboxMensajes;
    @FXML
    private VBox rootPane;

    GameFacade facade;
    private GameEventListener listener;

    public void initialize() {
        this.facade = GameFacade.getInstance();
        Combate combate = facade.getMundo().getCombateActual();
        rellenarEtiquetasEnemigo(combate.getEnemigo());


        String tipoMapa = facade.getMundo().getMapa().getTipoMapa();

        if (tipoMapa.equalsIgnoreCase("Desierto")) {
            rootPane.getStyleClass().add("backgroundFightDesierto");
        } else {
            rootPane.getStyleClass().add("backgroundFightPradera");
        }
    }

    public void rellenarEtiquetasEnemigo(Monstruo enemigo) {
        enemyName.setText(enemigo.getNombre());
        enemyHp.setText(String.valueOf(enemigo.getPuntosVida()));
        enemyRes.setText(String.valueOf(enemigo.getResistencia()));
        enemyDamage.setText(String.valueOf(enemigo.getDano()));
        enemyState.setText(enemigo.getEstado().getNombre());
        enemyStrategy.setText(enemigo.getEstrategiaCombate().getNombre());
        enemyReward.setText(String.valueOf(enemigo.getBotin()));
        enemyImage.setImage(enemigo.getSprite());
    }

    private void actualizarDatosPantalla() {
        Combate combate = facade.getCombateActual();

        enemyName.setText(combate.getEnemigo().getNombre());
        int hp = Math.max(0, combate.getEnemigo().getPuntosVida()); // para que no muestre negativo
        enemyHp.setText(String.valueOf(hp));
        enemyRes.setText(String.valueOf(combate.getEnemigo().getResistencia()));
        enemyDamage.setText(String.valueOf(combate.getEnemigo().getDano()));
        enemyState.setText(combate.getEnemigo().getEstado().getNombre());
        enemyStrategy.setText(combate.getEnemigo().getEstrategiaCombate().getNombre());
    }
    @FXML
    private void handleAttack() {
        Accion accion = ATACAR;
        int danoInflingido = facade.ejecutarTurno(accion);
        addMsj("¡Has atacado al " + facade.getCombateActual().getEnemigo().getNombre() + " causando " + danoInflingido + " de daño!", "ATAQUE");

        actualizarDatosPantalla();
        verificarFinCombate();
    }
    @FXML
    private void handleProtect() {
        Accion accion = PROTEGER;
        int vidaRecuperada = facade.ejecutarTurno(accion);
        addMsj("¡El guerrero se ha protegido curandose un 20% de su vida máxima (+ " + vidaRecuperada + " de HP)!", "PROTEGER");
        // actualizar los datps PERO del personaje, que esta en el controller de menu (donde estan sus datos)
        listener.onPlayerStatsChanged();
    }

    @FXML
    private void handlePotion() {
        Accion accion = USAR_POCION;

        addMsj("¡El guerrero se ha echado una poción (+ 20 de HP)!", "PROTEGER");
    }

    private void verificarFinCombate() {
        if (facade.getCombateActual().getEstadoActual() == FaseCombate.FINALIZADO) {
            addMsj("¡El combate ha terminado! Oro ganado: " + facade.getCombateActual().getEnemigo().getBotin(), "INFO");
            actualizarDatosPantalla();
            atackBtn.setDisable(true);
            protectBtn.setDisable(true);
            potionBtn.setDisable(true);

            // aqui se vuelve al mapa o se cierra el juego si ha perdido, dejando 3 o 5 seg
            terminarYVolverAlMapa();
        }
    }

    private void terminarYVolverAlMapa() {
        Combate combate = facade.getCombateActual();
        Mundo mundo = facade.getMundo();
        Monstruo enemigoDerrotado = combate.getEnemigo();

        int recompensa = enemigoDerrotado.getBotin();
        int oroActual = facade.getPersonaje().getOro();
        int oroActualizado = oroActual + recompensa;
        facade.getPersonaje().setOro(oroActualizado);

        listener.onPlayerStatsChanged();

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            mundo.eliminarMonstruo(enemigoDerrotado);
            listener.onFightEnded(); // se avisa que ya se ha terminado el combate para que el GameController vuelva a mostrar el mapa
        });
        pause.play();
    }

    public void addMsj(String texto, String tipo) {
        Label nuevoMensaje = new Label(texto);

        if (tipo.equals("ATAQUE")) {
            nuevoMensaje.getStyleClass().add("mensaje-ataque");
        }
        if (tipo.equals("PROTEGER")) {
            nuevoMensaje.getStyleClass().add("mensaje-proteger");
        }
        if (tipo.equals("INFO")) {
            nuevoMensaje.getStyleClass().add("mensaje-info");
        }

        vboxMensajes.getChildren().add(nuevoMensaje);

        // 4. Auto-scroll al final (Truco: esperar a que JavaFX renderice el nuevo nodo)
        vboxMensajes.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollLog.setVvalue(1.0);
        });
    }

    // para conectar el listener de GameController
    public void setListener(GameEventListener listener) {
        this.listener = listener;
    }
}
