# 🐉 Mazmorras

> RPG de combate por turnos desarrollado en **Java 21 + JavaFX**, como proyecto de la asignatura de Diseño de Software.

**Autores:** Ignacio Oya · Mario Seoane · Alejandro Soria

---

## 📋 Descripción

*Mazmorras* es un videojuego RPG 2D de combate por turnos. El jugador explora mapas generados con **tilemaps** (matrices numéricas que representan tiles gráficos) y se enfrenta a distintos enemigos para obtener recursos, mejorar su personaje y sobrevivir.

El proyecto aplica algunos **patrones de diseño** vistos en clase, integrando la lógica del modelo con la interfaz gráfica JavaFX a través de controladores FXML + CSS.

---

## 🖼️ Capturas de pantalla

### Pantalla de inicio
![Pantalla de inicio](/img/screen1_inicio.png)

### Exploración del mapa
![Mapa de juego](/img/screen2_mapa.png)

### Menú de pausa
![Menú de pausa](/img/screen3_pausa.png)

### Pantalla de combate
![Combate por turnos](/img/screen4_combate.png)

### Game Over
![Game Over](/img/screen5_gameover.png)

---

## 🛠️ Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| JavaFX | 21-ea+24 |
| ControlsFX | 11.1.2 |
| JUnit Jupiter | 5.9.2 |
| Build tool | Maven |

---

## 🏗️ Patrones de diseño implementados

### 1. Strategy — `EstrategiaCombate`
Interfaz implementada por `Agresiva`, `Defensiva` y `Equilibrada`. Cada estrategia aplica modificadores distintos al daño y la defensa (±20%). La clase `Monstruo` delega el cálculo del daño en su estrategia asignada.

### 2. Decorator — `ItemDecorator`
La clase abstracta `Item` es la base. `Espada`, `Armadura` y `Pocion` son items concretos. `ItemDecorator` envuelve un item para extenderlo; `EncantamientoFortaleza` añade daño extra a la espada sin modificar la clase original.

### 3. State — `EstadoEntidad`
Interfaz con tres estados: `EstadoBasico`, `EstadoAturdido` (no hace daño) y `EstadoFurioso` (daño aumentado). El estado es dinámico y puede cambiar entre turnos. La clase `Entidad` mantiene el estado actual como atributo.

### 4. Abstract Factory — `EnemigoFactory`
Interfaz que define la creación de familias de monstruos. `PraderaFactory` y `DesiertoFactory` generan sus propias versiones de `Orco`, `Mago` y `Esqueleto`. `EnemigoFactoryManager` actúa como contexto y selecciona la fábrica según el mapa elegido.

### 5. Singleton — `CalculadorDano`
Constructor privado + variable estática `instance`. El método `getInstance()` garantiza una única instancia. Centraliza los cálculos de `calcularAtaque()`, `calcularDefensa()` y `calcularVidaRecuperada()`, aplicando estados y estrategias.

### 6. Template Method — `Monstruo`
La clase abstracta define el esqueleto de `realizarTurno()`, que delega la decisión al método abstracto `decidirAccion()`. Cada subclase lo sobreescribe con su propia lógica:
- **Orco:** ataca siempre que tenga >20% de vida.
- **Mago:** ataca con >50% de vida, se protege si no.
- **Esqueleto:** ataca con >40% de vida, se protege si no.

### 7. Facade — `GameFacade`
Oculta la complejidad de las clases `Mundo`, `Mapa` y `Combate`. Expone métodos de alto nivel como `inicializarNuevaPartida()`, `iniciarCombate()` o `comprarPocion()`. Los controladores (`GameController`, `FightController`, `LaunchController`, `OptionsController`) interactúan únicamente con esta fachada.

---

## 📁 Estructura del proyecto

```
src/main/java/com/videogame/videojuegodissotfware/
│
├── HelloApplication.java               # Punto de entrada
│
├── gui/
│   ├── controllers/
│   │   ├── LaunchController.java       # Pantalla de inicio
│   │   ├── GameController.java         # Exploración del mapa
│   │   ├── FightController.java        # Pantalla de combate
│   │   └── OptionsController.java      # Menú de pausa / tienda
│   └── view/
│       ├── GameScene.java              # Renderizado del tilemap
│       └── GameEventListener.java
│
└── model/
    ├── core/
    │   ├── GameFacade.java             # [Facade]
    │   ├── CalculadorDano.java         # [Singleton]
    │   ├── Mundo.java
    │   ├── Mapa.java
    │   └── combate/
    │       ├── Combate.java
    │       └── FaseCombate.java
    ├── entities/
    │   ├── Entidad.java
    │   ├── Personaje.java
    │   ├── Monstruo.java               # [Template Method]
    │   ├── monstruos/
    │   │   ├── Orco.java
    │   │   ├── Mago.java
    │   │   └── Esqueleto.java
    │   └── state/                      # [State]
    │       ├── EstadoEntidad.java
    │       ├── EstadoBasico.java
    │       ├── EstadoAturdido.java
    │       └── EstadoFurioso.java
    ├── factories/                      # [Abstract Factory]
    │   ├── EnemigoFactory.java
    │   ├── EnemigoFactoryManager.java
    │   ├── pradera/
    │   │   ├── PraderaFactory.java
    │   │   ├── PraderaOrco.java
    │   │   ├── PraderaMago.java
    │   │   └── PraderaEsqueleto.java
    │   └── desierto/
    │       ├── DesiertoFactory.java
    │       ├── DesiertoOrco.java
    │       ├── DesiertoMago.java
    │       └── DesiertoEsqueleto.java
    ├── items/                          # [Decorator]
    │   ├── Item.java
    │   ├── Espada.java
    │   ├── Armadura.java
    │   ├── Pocion.java
    │   └── decorator/
    │       ├── ItemDecorator.java
    │       └── EncantamientoFortaleza.java
    ├── strategies/                     # [Strategy]
    │   ├── EstrategiaCombate.java
    │   ├── Agresiva.java
    │   ├── Defensiva.java
    │   └── Equilibrada.java
    └── actions/
        └── Accion.java
```

---

## 🎮 Manual de uso

### 1. Inicio
Introduce el nombre de tu guerrero/a, selecciona el mapa (**Pradera** o **Desierto**) y pulsa **Empezar**. Ambos campos son obligatorios.

### 2. Exploración
- Muévete con **W A S D**.
- Choca con un enemigo para iniciar combate.
- Usa el menú inferior para comprar mejoras con el oro obtenido:
  - 🗡️ **Mejorar Arma** — aumenta el daño permanentemente.
  - 🛡️ **Mejorar Armadura** — aumenta la resistencia.
  - 🧪 **Comprar Poción** — añade una poción al inventario.
- Pulsa el botón **⏸** para pausar: continuar, reiniciar o salir.

### 3. Combate
Cada turno puedes elegir entre tres acciones:
- **Atacar** — inflige daño según tus estadísticas y arma equipada.
- **Protegerse** — recupera HP.
- **Poción** — consume una poción del inventario para recuperar mucha vida.

El combate termina cuando uno de los dos llega a 0 HP.

### 4. Fin de partida
- **Game Over:** la aplicación muestra la pantalla de derrota y cierra tras unos segundos.
- **Has Ganado:** puedes reiniciar o salir desde el menú de pausa.

---

## ▶️ Ejecución

```bash
# Clonar el repositorio
git clone https://github.com/soriiaa/VideojuegoDisenoSoftware.git
cd VideojuegoDisenoSoftware

# Ejecutar con Maven
./mvnw clean javafx:run
```

> Requiere **Java 21** y **Maven** instalados. En Windows usa `mvnw.cmd` en lugar de `./mvnw`.

---

## 🙋‍♂️ Autores

Este proyecto ha sido desarrollado por:

- **Alejandro Soria** — [soriiaa](https://github.com/soriiaa)
- **Mario Seoane** — [mariooseoanee](https://github.com/mariooseoanee)
- **Ignacio Oya** — [nachoya19](https://github.com/nachoya19)

---

## 📄 Licencia

Proyecto académico — Ingeniería del Software, curso 2025/2026.
