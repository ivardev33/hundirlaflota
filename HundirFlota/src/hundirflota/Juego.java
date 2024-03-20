package hundirflota;

import java.awt.*;
import javax.swing.*;

public class Juego extends JPanel {

    Tablero tableroCPU;
    Tablero tableroJugador;
    
    /**
     * Constructor de la clase Juego
     */
    public Juego() {
        GridLayout layout = new GridLayout(2, 1);
        setLayout(layout);
        tableroCPU = new Tablero(false);
        add(tableroCPU);
        tableroJugador = new Tablero(true);
        add(tableroJugador);
    }

}
