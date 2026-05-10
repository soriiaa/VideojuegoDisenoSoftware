package com.videogame.videojuegodissotfware.gui.controllers;

import com.videogame.videojuegodissotfware.gui.view.GameEventListener;
import com.videogame.videojuegodissotfware.model.actions.Accion;
import com.videogame.videojuegodissotfware.model.core.GameFacade;
import com.videogame.videojuegodissotfware.model.core.Mundo;
import com.videogame.videojuegodissotfware.model.core.combate.Combate;
import com.videogame.videojuegodissotfware.model.core.combate.FaseCombate;
import com.videogame.videojuegodissotfware.model.entities.Entidad;
import com.videogame.videojuegodissotfware.model.entities.Monstruo;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private Parent view;
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
        procesarTurnoJugador(ATACAR);
    }

    @FXML
    private void handleProtect() {
        procesarTurnoJugador(PROTEGER);
    }

    @FXML
    private void handlePotion() {
        procesarTurnoJugador(USAR_POCION);
    }

    private void procesarTurnoJugador(Accion accion) {
        setBotonesDeshabilitados(true);

        String estadoEnemigoAntes = facade.getCombateActual().getEnemigo().getEstado().getNombre();
        int resultado = facade.ejecutarTurnoJugador(accion);

        switch (accion) {
            case ATACAR -> addMsj("¡Atacas al enemigo causando " + resultado + " de daño!", "ATAQUE");
            case PROTEGER -> addMsj("Te defiendes y recuperas un 20% de tu vida máxima: " + resultado + " de vida.", "PROTEGER");
            case USAR_POCION -> addMsj("Usas una poción y recuperas el 100% de tu vida.", "CURACION");
        }

        actualizarDatosPantalla();
        listener.onPlayerStatsChanged();

        comprobarCambioEstado(facade.getCombateActual().getEnemigo(), estadoEnemigoAntes);

        if (facade.getCombateActual().getEstadoActual() != FaseCombate.FINALIZADO) {
            ejecutarTurnoEnemigoConDelay();
        } else {
            verificarFinCombate();
        }
    }

    private void ejecutarTurnoEnemigoConDelay() {
        PauseTransition pausa = new PauseTransition(Duration.seconds(1.5));
        pausa.setOnFinished(event -> {
            String estadoPersonajeAntes = facade.getPersonaje().getEstado().getNombre();
            int resultadoEnemigo = facade.ejecutarTurnoEnemigo();

            if (resultadoEnemigo >= 0) {
                addMsj("¡El " + facade.getCombateActual().getEnemigo().getNombre() + " te ataca y te quita " + resultadoEnemigo + " de HP!", "ATAQUE");
            } else {
                addMsj("El " + facade.getCombateActual().getEnemigo().getNombre() + " se protege y recupera " + Math.abs(resultadoEnemigo) + " de vida.", "INFO");
            }

            actualizarDatosPantalla();
            listener.onPlayerStatsChanged();

            comprobarCambioEstado(facade.getPersonaje(), estadoPersonajeAntes);

            // si el jugador no ha muerto, reactivamos botones
            if (facade.getCombateActual().getEstadoActual() != FaseCombate.FINALIZADO) {
                setBotonesDeshabilitados(false);
            } else {
                verificarFinCombate();
            }
        });
        pausa.play();
    }

    private void setBotonesDeshabilitados(boolean estado) {
        atackBtn.setDisable(estado);
        protectBtn.setDisable(estado);
        potionBtn.setDisable(estado);
    }


    private void verificarFinCombate() {
        Combate combate = facade.getCombateActual();
        if (combate.getEstadoActual() == FaseCombate.FINALIZADO) {
            setBotonesDeshabilitados(true);

            if (facade.getPersonaje().getPuntosVida() > 0) {
                addMsj("¡Victoria! Has derrotado al enemigo.", "INFO");
                addMsj("Oro obtenido: " + combate.getEnemigo().getBotin(), "INFO");
                terminarYVolverAlMapa();
            } else {
                addMsj("Has sido derrotado...", "INFO");

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {
                    listener.onGameOver();
                });
                pause.play();
            }
        }
    }

    private void comprobarCambioEstado(Entidad entidad, String estadoAnterior) {
        String estadoDespues = entidad.getEstado().getNombre();

        // se manda el mensaje si el estado ha cambiado realmente
        if (!estadoAnterior.equals(estadoDespues)) {
            if (estadoDespues.equals("Furioso")) {
                addMsj("¡" + entidad.getNombre() + " ha entrado en estado FURIOSO!", "ESTADO");
            } else if (estadoDespues.equals("Aturdido")) {
                addMsj("¡" + entidad.getNombre() + " ha quedado ATURDIDO!", "ESTADO");
            } else if (estadoDespues.equals("Basico") && !estadoAnterior.equals("Basico")) {
                addMsj("¡" + entidad.getNombre() + " se ha recuperado!", "ESTADO");
            }
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

        switch (tipo) {
            case "ATAQUE" -> nuevoMensaje.getStyleClass().add("mensaje-ataque");
            case "PROTEGER" -> nuevoMensaje.getStyleClass().add("mensaje-proteger");
            case "CURACION" -> nuevoMensaje.getStyleClass().add("mensaje-curacion");
            case "INFO" -> nuevoMensaje.getStyleClass().add("mensaje-info");
            case "ESTADO" -> nuevoMensaje.getStyleClass().add("mensaje-estado");
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
