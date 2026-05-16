# 🌌 Galaxy Simulator

Un simulador dinámico de un ecosistema galáctico implementado en Java que aplica conceptos de herencia, interfaces, polimorfismo y manejo de excepciones. El programa simula la física gravitacional real entre cuerpos celestes y la absorción de materia por agujeros negros.

## 📋 Características

- **Física Gravitacional Real**: Cálculo de distancia euclidiana y fuerza gravitacional basada en la masa de los cuerpos
- **Radios de Influencia Dinámicos**: Planetas y estrellas tienen alcances diferentes según su fuerza gravitacional
- **Absorción de Agujeros Negros**: Los cuerpos celestes son absorbidos si se acercan demasiado (basado en la masa del agujero negro)
- **Simulación Interactiva**: Cambiar posiciones y masas altera completamente el comportamiento de la galaxia
- **Filtrado con Streams**: Búsqueda de planetas por criterios de masa usando Java Streams

## 🏗️ Estructura del Proyecto

```
├── CuerpoCeleste.java        # Clase abstracta base para todos los cuerpos
├── Gravitacional.java        # Interfaz para cuerpos que generan atracción
├── Planeta.java              # Planetas con atracción gravitacional limitada
├── Estrella.java             # Estrellas con mayor radio de influencia
├── AgujeroNegro.java         # Agujeros negros que absorben materia
├── ColisionExcepcion.java    # Excepción personalizada para colisiones
└── Main.java                 # Simulador principal
```

## 🔧 Conceptos Java Aplicados

| Concepto | Implementación |
|----------|---------------|
| **Herencia** | `CuerpoCeleste` es clase abstracta; `Planeta`, `Estrella` y `AgujeroNegro` extienden de ella |
| **Interfaces** | `Gravitacional` implementada por `Planeta` y `Estrella` |
| **Polimorfismo** | Método `atraer()` comportamiento diferente en cada clase |
| **Excepciones** | `ColisionExcepcion` lanzada cuando agujero negro absorbe un cuerpo |
| **Streams** | Filtrado y ordenamiento de cuerpos celestes por masa |
| **HashMap** | Galaxia representada como mapa `<String, CuerpoCeleste>` |

## 🚀 Cómo Usar

### Compilar
```bash
javac *.java
```

### Ejecutar
```bash
java Main
```

### Salida Esperada
El programa muestra 6 secciones:

1. **Galaxia Inicial**: Todos los cuerpos celestes con sus propiedades
2. **Filtrado por Masa**: Planetas con masa > 20 ordenados por peso
3. **Simulación de Atracción**: Cada cuerpo intenta atraer a los demás
4. **Simulación de Absorción**: El agujero negro absorbe cuerpos dentro de su radio
5. **Galaxia Final**: Estado tras las absorciones
6. **Ranking**: Cuerpos ordenados por fuerza gravitacional

## 🌍 Personalización

Puedes modificar fácilmente la simulación editando `Main.java`:

### Cambiar Posiciones
```java
var tierra = new Planeta("Tierra", 100, 8, 600, 400);  // Más cerca del agujero negro
```

### Cambiar Masas
```java
var jupiter = new Planeta("Jupiter", 1000, 150, 1500, 1210);  // Más masivo
```

### Agregar Cuerpos
```java
var venus = new Planeta("Venus", 150, 12, 450, 650);
galaxia.put(venus.getNombre(), venus);
```

## 📊 Fórmulas Utilizadas

### Distancia Euclidiana
```
distancia = √((x₁ - x₂)² + (y₁ - y₂)²)
```

### Fuerza Gravitacional
```
fuerza = (masa₁ × masa₂) / distancia²
```

### Radio de Influencia
- **Planeta**: `fuerzaGravitacional / 10`
- **Estrella**: `fuerzaGravitacional / 5`
- **Agujero Negro**: `masa / 10`

## 🎯 Casos de Uso Destacados

- **Mercurio** (pos: 350, 720) está dentro del radio de absorción del agujero negro
- **Sol** (pos: 300, 700) también es absorbido por su proximidad
- **Jupiter** y **Saturno** están lo suficientemente lejos como para escapar
- **Arturo** (la segunda estrella) está demasiado lejana para ser absorbida

## 📝 Requisitos

- Java 21 o superior
- Compilador `javac`

## 🔮 Posibles Mejoras Futuras

- [ ] Visualización gráfica con Swing/JavaFX
- [ ] Simulación paso a paso (tick por tick)
- [ ] Órbitas de cuerpos alrededor de estrellas
- [ ] Colisiones entre planetas
- [ ] Lectura de configuración desde archivo

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo licencia MIT.

---

**Autor**: Desarrollado como ejercicio de programación orientada a objetos en Java
**Última actualización**: 2026