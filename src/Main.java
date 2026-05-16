import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // --- Creacion de cuerpos celestes ---
        // Posiciones en unidades astronomicas simuladas (x , y)
        // Masa influye en la fuerza gravitacional y en lo que el agujero negro puede absorber

        var tierra   = new Planeta("Tierra"  , 100  , 8   , 1200 , 750);
        var jupiter  = new Planeta("Jupiter" , 1000 , 92  , 1500 , 1210);
        var saturno  = new Planeta("Saturno" , 500  , 41  , 1200 , 1210);
        var urano    = new Planeta("Urano"   , 300  , 26  , 1400 , 1610);
        var neptuno  = new Planeta("Neptuno" , 200  , 16  , 1600 , 2010);
        var mercurio = new Planeta("Mercurio", 60   , 3   , 350  , 720);

        var sol    = new Estrella("Sol"    , 10000 , 1000 , 300  , 700);
        var arturo = new Estrella("Arturo" , 8500  , 715  , 2200 , 1800);

        // Agujero negro posicionado en el origen: absorbe cualquier cosa en lo que este a radio=masa/10=1000
        var agujeroNegro = new AgujeroNegro("Sagitario-A" , 100000 , 10000 , 0 , 0);

        // --- Galaxia representada como mapa nombre -> cuerpo ---
        var galaxia = new HashMap<String, CuerpoCeleste>();

        galaxia.put(tierra.getNombre()    , tierra);
        galaxia.put(jupiter.getNombre()   , jupiter);
        galaxia.put(saturno.getNombre()   , saturno);
        galaxia.put(urano.getNombre()     , urano);
        galaxia.put(neptuno.getNombre()   , neptuno);
        galaxia.put(mercurio.getNombre()  , mercurio);
        galaxia.put(sol.getNombre()       , sol);
        galaxia.put(arturo.getNombre()    , arturo);
        galaxia.put(agujeroNegro.getNombre() , agujeroNegro);

        // === SECCION 1: Estado inicial de la galaxia ===
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║           GALAXIA INICIAL                           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        galaxia.values().forEach(System.out::println);

        // === SECCION 2: Filtro por masa usando Streams ===
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║     PLANETAS CON MASA > 20                          ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        List<CuerpoCeleste> planetasPesados = galaxia.values().stream()
                .filter(c -> c instanceof Planeta)
                .filter(c -> c.getMasa() > 20)
                .sorted((a , b) -> Integer.compare(b.getMasa() , a.getMasa()))
                .toList();
        planetasPesados.forEach(System.out::println);

        // === SECCION 3: Simulacion de atraccion gravitacional ===
        // Cada cuerpo Gravitacional intenta atraer a todos los demas
        // El resultado depende de su radio de influencia vs la distancia real
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║     SIMULACION DE ATRACCION GRAVITACIONAL           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        List<CuerpoCeleste> todosLosCuerpos = galaxia.values().stream().toList();

        for (var cuerpo : todosLosCuerpos) {
            if (cuerpo instanceof Gravitacional g) {
                System.out.println("\n>> " + cuerpo.getNombre() + " intentando atraer:");
                for (var objetivo : todosLosCuerpos) {
                    // Un cuerpo no se atrae a si mismo
                    if (!objetivo.getNombre().equals(cuerpo.getNombre())) {
                        g.atraer(objetivo);
                    }
                }
            }
        }

        // === SECCION 4: Simulacion de absorcion del agujero negro ===
        // Verifica cada cuerpo: si esta dentro del radio de absorcion, desaparece de la galaxia
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║     SIMULACION DE ABSORCION DEL AGUJERO NEGRO      ║");
        System.out.printf ("║     Radio de absorcion: %-4d                        ║%n" , agujeroNegro.getRadioAbsorcion());
        System.out.println("╚══════════════════════════════════════════════════════╝");

        // Tomamos snapshot de los nombres para no modificar el mapa mientras iteramos
        List<String> nombresActuales = galaxia.keySet().stream()
                .filter(nombre -> !nombre.equals(agujeroNegro.getNombre()))
                .toList();

        for (var nombre : nombresActuales) {
            var cuerpo = galaxia.get(nombre);
            try {
                agujeroNegro.colisionar(cuerpo);
            } catch (ColisionExcepcion e) {
                System.out.println("  [!] " + e.getMessage());
                galaxia.remove(nombre);
            }
        }

        // === SECCION 5: Estado final de la galaxia tras absorcion ===
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║     GALAXIA TRAS LA ABSORCION                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        galaxia.values().forEach(System.out::println);

        // === SECCION 6: Ranking de cuerpos por fuerza gravitacional ===
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║     RANKING POR FUERZA GRAVITACIONAL                ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        galaxia.values().stream()
                .sorted((a , b) -> Integer.compare(b.getFuerzaGravitacional() , a.getFuerzaGravitacional()))
                .forEach(c -> System.out.printf("  %-15s gravedad=%d%n", c.getNombre() , c.getFuerzaGravitacional()));
    }
}