package hundirflota;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import hundirflota.colorBarcos.*;

/**
 * Clase Título
 *
 * @author ilernagames
 */
public class Titulo extends JPanel {

    JPanel barra;
    JPanel panel_titulo = new JPanel();
    JLabel titulo;

    /**
     * Constructor de la clase Título
     */
    public Titulo() {
        barra = new JPanel();
        setLayout(new GridLayout(2, 1));
        panel_titulo.setBorder(new EmptyBorder(0, 0, 0, 0));
        add(barra);
        add(panel_titulo);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        // Datos del título
        titulo = new JLabel("ILERNAGAMES - HUNDIR LA FLOTA", SwingConstants.CENTER);
        titulo.setFont(new Font("Verdana", 1, 17));
        titulo.setForeground(Color.DARK_GRAY);
        titulo.setVisible(true);
        crearMenu();
        panel_titulo.add(titulo);
    }
    
    /**
     * Crea el menú para elegir los colores
     * AQUÍ CREAREMOS EL MENÚ DE LOS COLORES
     */
    private void crearMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu color_barcos = new JMenu("Color Barcos");
        // Color Marrón
        JMenuItem color_marron = new JMenuItem("Marrón");
        color_marron.addActionListener(new EColorMarron());
        // Color Morado
        JMenuItem color_morado = new JMenuItem("Morado");
        color_morado.addActionListener(new EColorMorado());
        // Color Naranja
        JMenuItem color_naranja = new JMenuItem("Naranja");
        color_naranja.addActionListener(new EColorNaranja());
        // Color Rosa
        JMenuItem color_rosa = new JMenuItem("Rosa");
        color_rosa.addActionListener(new EColorRosa());
        // Color Verde
        JMenuItem color_verde = new JMenuItem("Verde");
        color_verde.addActionListener(new EColorVerde());
        // COLOR NEGRO
        JMenuItem color_negro = new JMenuItem("Negro");
        color_negro.addActionListener(new EColorNegro());
        // COLOR AMARILLO
        JMenuItem color_amarillo = new JMenuItem("Amarillo");
        color_amarillo.addActionListener(new EColorAmarillo());
        // COLOR GRIS 
        JMenuItem color_gris = new JMenuItem("Gris");
        color_gris.addActionListener(new EColorGris());
        // Añadimos los diferentes colores
        color_barcos.add(color_marron);
        color_barcos.add(color_morado);
        color_barcos.add(color_naranja);
        color_barcos.add(color_rosa);
        color_barcos.add(color_verde);
        color_barcos.add(color_negro);
        color_barcos.add(color_amarillo);
        color_barcos.add(color_gris);
        menu.add(color_barcos);
        barra.add(menu);
    }

    class eColorVerde implements ActionListener {

        Color color = Color.green;

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int x = 0; x < 100; x++) {
                if (Partida.tableroJugador.botones[x].getActivo()
                        && !Partida.tableroJugador.botones[x].getTocado()
                        && !Partida.tableroJugador.botones[x].getHundido()) {
                    Partida.tableroJugador.botones[x].setBackground(color);
                }
            }
            Boton.color = color;
        }

    }
}