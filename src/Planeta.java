public class Planeta extends CuerpoCeleste implements Gravitacional {

    public Planeta(String nombre , Integer fuerzaGravitacional , Integer masa , Integer posicionX , Integer posicionY) {
        super(nombre , fuerzaGravitacional , masa , posicionX , posicionY);
    }

    // Solo atrae si el otro cuerpo esta dentro del radio de influencia del planeta
    @Override
    public void atraer(CuerpoCeleste otro) {
        int distancia     = distanciaA(otro);
        int radioInfluencia = this.fuerzaGravitacional / 10;

        if (distancia <= radioInfluencia) {
            double fuerza = fuerzaHacia(otro);
            System.out.printf("  [ATRACCION] %s atrae a %s | distancia=%d | fuerza=%.4f%n",
                    this.nombre , otro.getNombre() , distancia , fuerza);
        } else {
            System.out.printf("  [IGNORADO]  %s no alcanza a %s | distancia=%d | radio=%d%n",
                    this.nombre , otro.getNombre() , distancia , radioInfluencia);
        }
    }

    @Override
    public void fuerza_atraccion(int distancia) {
        if (distancia == 0) {
            System.out.println("  Distancia cero: colision directa");
            return;
        }
        int fuerzaAtraccion = this.fuerzaGravitacional / distancia;
        System.out.println("  Fuerza de atraccion de " + this.nombre + ": " + fuerzaAtraccion);
    }
}