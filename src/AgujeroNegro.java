public class AgujeroNegro extends CuerpoCeleste {

    // Radio dentro del cual el agujero negro absorbe cualquier cuerpo
    private final int radioAbsorcion;

    public AgujeroNegro(String nombre , Integer fuerzaGravitacional , Integer masa , Integer posicionX , Integer posicionY) {
        super(nombre , fuerzaGravitacional , masa , posicionX , posicionY);
        // El radio de absorcion escala con la masa del agujero negro
        this.radioAbsorcion = masa / 10;
    }

    // Lanza ColisionExcepcion si el cuerpo esta dentro del radio de absorcion
    public void colisionar(CuerpoCeleste otro) throws ColisionExcepcion {
        int distancia = distanciaA(otro);

        if (distancia <= radioAbsorcion) {
            throw new ColisionExcepcion(
                    String.format("ABSORCION: %s ha absorbido a %s (distancia=%d, radio=%d)",
                            this.nombre , otro.getNombre() , distancia , radioAbsorcion)
            );
        } else {
            System.out.printf("  [SEGURO] %s esta fuera del alcance de %s (distancia=%d, radio=%d)%n",
                    otro.getNombre() , this.nombre , distancia , radioAbsorcion);
        }
    }

    public int getRadioAbsorcion() { return radioAbsorcion; }
}