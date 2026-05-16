public abstract class CuerpoCeleste {

    String nombre;
    int fuerzaGravitacional;
    int masa;
    int posicionX;
    int posicionY;

    public CuerpoCeleste(String nombre , int fuerzaGravitacional , int masa , int posicionX , int posicionY) {
        this.nombre = nombre;
        this.fuerzaGravitacional = fuerzaGravitacional;
        this.masa = masa;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    // Distancia euclidiana real entre este cuerpo y otro
    public int distanciaA(CuerpoCeleste otro) {
        int dx = this.posicionX - otro.posicionX;
        int dy = this.posicionY - otro.posicionY;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    // Fuerza gravitacional simplificada: (masaA * masaB) / distancia^2
    // Evita division por cero si estan en la misma posicion
    public double fuerzaHacia(CuerpoCeleste otro) {
        int distancia = distanciaA(otro);
        if (distancia == 0) return Double.MAX_VALUE;
        return (double) (this.masa * otro.masa) / (distancia * distancia);
    }

    public String getNombre()           { return nombre; }
    public int getMasa()                { return masa; }
    public int getFuerzaGravitacional() { return fuerzaGravitacional; }
    public int getPosicionX()           { return posicionX; }
    public int getPosicionY()           { return posicionY; }

    @Override
    public String toString() {
        return String.format("%-15s masa=%-6d pos=(%4d,%4d)  gravedad=%d",
                nombre, masa, posicionX, posicionY, fuerzaGravitacional);
    }
}