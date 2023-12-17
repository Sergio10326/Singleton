import java.util.Random;
import java.util.Scanner;

// Implementación del patrón Singleton
class JuegoAdivinanza {
    private static JuegoAdivinanza instancia;

    private int rondas;
    private int intentos;
    private int recordPuntuacion;
    private String recordUsuario;

    private JuegoAdivinanza() {
        this.rondas = 10;
        this.intentos = 2;
        this.recordPuntuacion = Integer.MAX_VALUE;
        this.recordUsuario = "";
    }

    public static JuegoAdivinanza getInstancia() {
        if (instancia == null) {
            instancia = new JuegoAdivinanza();
        }
        return instancia;
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("¡Bienvenido al juego de adivinar el número!");

        for (int ronda = 1; ronda <= rondas; ronda++) {
            int numeroAleatorio = random.nextInt(10) + 1;
            System.out.println("\nRonda " + ronda + ": Adivina el número entre 1 y 10");

            for (int intento = 1; intento <= intentos; intento++) {
                System.out.print("Intento " + intento + ": ");
                int eleccionUsuario = scanner.nextInt();

                if (eleccionUsuario == numeroAleatorio) {
                    System.out.println("¡Correcto! Has adivinado el número.");
                    break;
                } else {
                    System.out.println("Incorrecto. ");

                    if (intento < intentos) {
                        System.out.println("Pista: El número es " + ((eleccionUsuario < numeroAleatorio) ? "mayor" : "menor"));
                    }
                }
            }
        }

        System.out.println("\nFin del juego.");
        scanner.close();
    }

    public void actualizarRecord(String usuario, int puntuacion) {
        if (puntuacion < recordPuntuacion) {
            recordUsuario = usuario;
            recordPuntuacion = puntuacion;
            System.out.println("¡Nuevo récord! Usuario: " + usuario + ", Puntuación: " + puntuacion);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Obtener la instancia del Singleton
        JuegoAdivinanza juego = JuegoAdivinanza.getInstancia();

        // Jugar al juego
        juego.jugar();

        // Simular que otro jugador juega y actualiza el récord
        JuegoAdivinanza otroJuego = JuegoAdivinanza.getInstancia();
        otroJuego.actualizarRecord("OtroUsuario", 15);
    }
}

