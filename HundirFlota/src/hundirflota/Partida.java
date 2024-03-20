package hundirflota;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


//TODO en este clase deben ir algunos de los JDialog que necesitamos
public class Partida {

    // Variables que se usan para el juego
    private static Ventana partida = new Ventana();
    public static Tablero tableroJugador = partida.juego.tableroJugador;
    private static Tablero tableroCPU = partida.juego.tableroCPU;
    private static Texto texto = partida.texto;
    private static int[] barcoColocado = new int[5];
    private static int bucle = 1;
    
  

    /**
     * Main del proyecto
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            // Por si tenemos errores en el main
            PrintStream errorStream = new PrintStream("error.log");
            System.setErr(errorStream);
            // Configuramos que al cerrar la partida => se cierra el juego
            partida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Configuración de la ventana
            tableroJugador.setBorder(new EmptyBorder(0, 5, 0, 25));
            tableroCPU.rotar.setVisible(false);
            //BIENVENIDA AL JUEGO
            
            VBienvenida.mostrarDialogo(partida);
            
            
            
            // Jugador Coloca los 5 barcos
            colocarBarco(tableroJugador, 3, "Coloca el barco de 3 casillas.");
            colocarBarco(tableroJugador, 3, "Coloca otro barco de 3 casillas.");
            colocarBarco(tableroJugador, 4, "Coloca el barco de 4 casillas.");
            colocarBarco(tableroJugador, 4, "Coloca otro barco de 4 casillas");
            colocarBarco(tableroJugador, 5, "Coloca el barco de 5 casillas");
            // CPU coloca los 5 barcos
            colocarBarcoAleatorio(tableroCPU, 3, 1);
            colocarBarcoAleatorio(tableroCPU, 3, 2);
            colocarBarcoAleatorio(tableroCPU, 4, 3);
            colocarBarcoAleatorio(tableroCPU, 4, 4);
            colocarBarcoAleatorio(tableroCPU, 5, 5);
            // Tamaño del texto de
            texto.setTamanoFuente(12f);
            texto.setTexto("CPU colocó barcos y puedes comenzar a jugar.");
            // Bucle del juego
            while (bucle == 1) {
                turno();
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("No se pudo crear el archivo de log de errores.");
            fnfe.printStackTrace(System.err);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err); // Esto ahora va al archivo "error.log"
        }
    }

    /**
     * Colocar el barco
     *
     * @param tablero
     * @param n_barcos
     * @param ttexto
     * @throws InterruptedException
     */
    public static void colocarBarco(Tablero tablero, int n_barcos, String ttexto) throws InterruptedException {
        texto.setTexto(ttexto);
        tablero.anadirBarco(n_barcos);
        while (tablero.proceso == 1) {
            Thread.sleep(1);
        }
    }

    /**
     * Colocar el barco de forma aleatoria
     *
     * @param tablero
     * @param n_barcos
     * @param barco_contador
     */
    private static void colocarBarcoAleatorio(Tablero tablero, int n_barcos, int barco_contador) {
        int aleatorio = (int) (Math.random() * 2 + 1);
        if (aleatorio == 1) {
            colocarBarcoAleatorioHorizontal(tablero, n_barcos, barco_contador);
        } else {
            colocarBarcoAleatorioVertical(tablero, n_barcos, barco_contador);
        }
    }

    /**
     * Coloca Barco Horizontal de forma aleatoria
     *
     * @param tablero
     * @param n_barcos
     * @param barco_contador
     */
    private static void colocarBarcoAleatorioHorizontal(Tablero tablero, int n_barcos, int barco_contador) {
        int aleatorio;
        boolean valido;
        int bucleAux = 1;

        while (bucleAux == 1) {
            valido = true;
            aleatorio = (int) (Math.random() * 99 + 1);
            for (int i = 0; i < 5; i++) {
                if (barcoColocado[i] == aleatorio) {
                    valido = false;
                }
            }

            if (aleatorio % 10 > 10 - n_barcos) {
                valido = false;
            }
            if (tablero.botones[aleatorio].getActivo()) {
                valido = false;
            }
            if (tablero.anadirBarcoHorizontal(tablero.botones[aleatorio], n_barcos, barco_contador) == true && valido)//la coordenada 0 da problemas y multiplica los barcos
            {
                for (int x = 0; x < 100; x++) {
                    if (tablero.botones[x].getIluminado()) {
                        tablero.botones[x].setActivo(true);
                        //tablero.botones[x].setColorActivo();
                        tablero.botones[x].setColorDefault();
                        tablero.botones[x].setIdBarco(barco_contador);
                        //tablero.botones[x].setText(""+barco_contador);
                        tablero.botones[x].setIluminado(false);
                    }
                }
                barcoColocado[barco_contador - 1] = aleatorio;
                bucleAux = 0;
            }
        }
        tablero.barcosHorizontalBorrar(null);
    }

    /**
     * Coloca Barco Vertical de forma aleatoria
     *
     * @param tablero
     * @param n_barcos
     * @param barco_contador
     */
    public static void colocarBarcoAleatorioVertical(Tablero tablero, int n_barcos, int barco_contador) {
        int aleatorio;
        boolean valido = true;
        int bucleAux = 1;

        while (bucleAux == 1) {
            aleatorio = (int) (Math.random() * 99 + 1);
            for (int i = 0; i < 5; i++) {
                if (barcoColocado[i] == aleatorio) {
                    valido = false;
                }
            }
            if (aleatorio / 10 < n_barcos - 1) {
                valido = false;
            }
            if (tablero.botones[aleatorio].getActivo()) {
                valido = false;
            }
            if (tablero.anadirBarcoVertical(tablero.botones[aleatorio], n_barcos, barco_contador) == true && valido) {
                tablero.anadirBarcoVertical(tablero.botones[aleatorio], n_barcos, barco_contador);
                for (int x = 0; x < 100; x++) {
                    int contador_interno = 0;
                    if (contador_interno < n_barcos) {
                        if (tablero.botones[x].getIluminado()) {
                            tablero.botones[x].setActivo(true);
                            //tablero.botones[x].setColorActivo();
                            tablero.botones[x].setColorDefault();
                            tablero.botones[x].setIdBarco(barco_contador);
                            //tablero.botones[x].setText(""+barco_contador);
                            tablero.botones[x].setIluminado(false);
                            contador_interno++;
                        }
                    }
                }
                barcoColocado[barco_contador - 1] = aleatorio;
                bucleAux = 0;
                valido = false;
            } else {
                valido = true;
            }
        }
        tablero.barcosHorizontalBorrar(null);
    }

    /**
     * Establece el turno de juego
     *
     * @throws InterruptedException
     */
    public static void turno() throws InterruptedException {
        int contador1 = 0;
        int contador2 = 0;
        tableroCPU.elegirCasilla(-1);
        while (tableroCPU.proceso == 1) {
            Thread.sleep(1);
        }

        for (int x = 0; x < 100; x++) {
            if (tableroCPU.botones[x].getActivo()) {
                contador1++;
            }
            if (tableroCPU.botones[x].getTocado()) {
                contador2++;
            }
        }

        System.out.println("contador1:" + contador1);
        System.out.println("contador2:" + contador2);

        if (contador1 == contador2) {
            texto.setForeground(Color.GREEN);
            texto.setTexto("HAS GANADO!!!!");
            bucle = 0;
        }

        if (contador1 != contador2) {
            contador1 = 0;
            contador2 = 0;
            texto.setTexto("CPU elige la casilla para atacar.");
            Thread.sleep((int) (Math.random() * 3000 + 500));
            int aleatorio = (int) (Math.random() * 100);
            tableroJugador.elegirCasilla(aleatorio);
            for (int x = 0; x < 100; x++) {
                if (tableroJugador.botones[x].getActivo()) {
                    contador1++;
                }
                if (tableroJugador.botones[x].getHundido()) {
                    contador2++;
                }
            }
            if (contador1 == contador2) {
                texto.setForeground(Color.RED);
                bucle = 0;
                texto.setTexto("HAS PERDIDO!!!!");
            } else {
                texto.setTexto("CPU ha atacado. Es tu turno.");
            }
        }
    }

    public static Tablero getTableroJugador() {
        return tableroJugador;
    }

}
