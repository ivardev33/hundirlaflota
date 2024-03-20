package hundirflota;

import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame {
    // Constantes con el tamaño de la ventana
    public static final int ANCHO = 500;
    public static final int ALTO = 850;
    
    // Atributos en visibilidad de paquete
    Juego juego;
    Texto texto;
    Titulo titulo;
    
 

    
    /**
     * Constructor de la clase ventana
     */
    public Ventana() {
        setTitle("ILERNAGAMES - HUNDIR LA FLOTA");
        this.setResizable(false);
        setBounds(100, 100, Ventana.ANCHO, Ventana.ALTO);
        setLocationRelativeTo(null);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        juego = new Juego();
        add(juego, BorderLayout.CENTER);
        texto = new Texto();
        add(texto, BorderLayout.SOUTH);
        titulo = new Titulo();
        add(titulo, BorderLayout.NORTH);
        setVisible(true);
    }

}
