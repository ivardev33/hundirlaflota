package hundirflota;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 * Clase Tablero para la partida de Hundir la Flota
 *
 * @author ilernagames
 */
public class Tablero extends JPanel {
    // Atributos con visibilidad de paquete
    Boton[] botones = new Boton[105];
    JLabel[] letra = new JLabel[11];
    JLabel[] numero = new JLabel[10];
    JButton rotar;
    // Configuración del tablero
    int rotacion = 0;
    int proceso = 0; //--0 = COLOCACION DE BARCO NO INICIADA // 1 = COLOCANDO BARCOS // 2 = PROCESO TERMINADO
    boolean ePosibleColocar = true; //indicar si es posible o no colocar el barco
    int contadorBarco = 1;
    int barcosHundidos = 0;
    // Eventos de los botones
    EanadirBarco e1 = new EanadirBarco();
    EcambiarRotacion e2 = new EcambiarRotacion();
    EelegirCasilla e3 = new EelegirCasilla();

    /**
     * Constructor de la clase Tablero
     *
     * @param jugador
     */
    public Tablero(boolean jugador) {
        GridLayout layout = new GridLayout(12, 11);
        setLayout(layout);
        setBorder(new EmptyBorder(15, 5, 0, 25));
        crearTablero();
    }

    /**
     * Crea el tablero de juego
     */
    private void crearTablero() {
        for (int x = 0; x < 11; x++) {
            switch (x) {
                case 0 ->
                    letra[x] = new JLabel("", SwingConstants.CENTER);
                case 1 ->
                    letra[x] = new JLabel("A", SwingConstants.CENTER);
                case 2 ->
                    letra[x] = new JLabel("B", SwingConstants.CENTER);
                case 3 ->
                    letra[x] = new JLabel("C", SwingConstants.CENTER);
                case 4 ->
                    letra[x] = new JLabel("D", SwingConstants.CENTER);
                case 5 ->
                    letra[x] = new JLabel("E", SwingConstants.CENTER);
                case 6 ->
                    letra[x] = new JLabel("F", SwingConstants.CENTER);
                case 7 ->
                    letra[x] = new JLabel("G", SwingConstants.CENTER);
                case 8 ->
                    letra[x] = new JLabel("H", SwingConstants.CENTER);
                case 9 ->
                    letra[x] = new JLabel("I", SwingConstants.CENTER);
                case 10 ->
                    letra[x] = new JLabel("J", SwingConstants.CENTER);
            }
            add(letra[x]);
        }
        int contador = 0;
        for (int x = 0; x < 100; x++) {
            if (x % 10 == 0 && x != 99) {
                numero[contador] = new JLabel("" + (contador + 1), SwingConstants.CENTER);
                add(numero[contador]);
                contador++;
            }
            botones[x] = new Boton("");
            botones[x].setSize(25, 25);
            add(botones[x]);
        }
        JPanel panelrotar = new JPanel();
        this.add(panelrotar);
        panelrotar.setLayout(new FlowLayout());
        rotar = new JButton("Rotar");
        rotar.addActionListener(new EcambiarRotacion());
        rotar.setBackground(Color.cyan);
        panelrotar.add(rotar);
    }

    /**
     * Añade un barco
     *
     * @param n_barcos
     */
    public void anadirBarco(int n_barcos) {
        proceso = 1;
        for (int x = 0; x < 100; x++) {
            e1.setN_barcos(n_barcos);
            botones[x].addMouseListener(e1);
            botones[x].addKeyListener(e2);
        }
    }

    /**
     * Termina de añadior el barco
     */
    public void terminarAnadirBarco() {
        for (int x = 0; x < 100; x++) {
            EanadirBarco e = new EanadirBarco();
            botones[x].removeMouseListener(e1);
            botones[x].removeKeyListener(e2);
        }
        proceso = 2;
    }

    /**
     * Añade un Barco en la Orientaciñon Horizontal
     *
     * @param objeto
     * @param n_barcos
     * @param barco_contador
     * @return
     */
    public boolean anadirBarcoHorizontal(Boton objeto, int n_barcos, int barco_contador) {
        int bandera = 0;
        for (int x = 0; x < 100; x++) {
            int linea = x / 10;
            linea = linea * 10;
            int numeroAux = x - linea;
            int contador = 0;
            if (objeto == botones[x] && !botones[x].getActivo()) {
                if (numeroAux <= 10 - n_barcos) {
                    for (int y = 0; y <= 10 - n_barcos; y++) {
                        if (objeto == botones[x] && numeroAux == y) {
                            for (int w = 0; w < n_barcos; w++) {
                                if (contador == 0) {
                                    if (!botones[x + contador].getActivo()) {
                                        botones[x + contador].setColorEleccionVerde();
                                        botones[x + contador].setIluminado(true);
                                        botones[x + contador].setBorder(
                                                BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));
                                    } else {
                                        bandera++;
                                    }
                                } else if (contador == n_barcos - 1) {
                                    if (!botones[x + contador].getActivo()) {
                                        botones[x + contador].setColorEleccionVerde();
                                        botones[x + contador].setIluminado(true);
                                        botones[x + contador].setBorder(
                                                BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));
                                    } else {
                                        bandera++;
                                    }
                                } else {
                                    if (!botones[x + contador].getActivo()) {
                                        botones[x + contador].setColorEleccionVerde();
                                        botones[x + contador].setIluminado(true);
                                        botones[x + contador].setBorder(
                                                BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));
                                    } else {
                                        bandera++;
                                    }
                                }
                                contador++;
                            }
                        }
                    }
                }
            }
        }
        ePosibleColocar = bandera == 0;
        if (ePosibleColocar) {
            //contador_barco++;
            return true;
        } else {
            for (int x = 0; x < 100; x++) {
                if (botones[x].getIluminado()) {
                    botones[x].setIluminado(false);
                }
            }
            return false;
        }
    }

    /**
     * Añade un barco en orientación vertical
     *
     * @param objeto
     * @param n_barcos
     * @param barco_contador
     * @return
     */
    public boolean anadirBarcoVertical(Boton objeto, int n_barcos, int barco_contador) {
        int bandera = 0;
        for (int x = 0; x < 100; x++) {
            int linea = x / 10;
            int contador = 0;
            if (linea >= n_barcos - 1) {
                for (int y = n_barcos - 1; y < 10; y++) {
                    if (objeto == botones[x] && linea == y) {
                        for (int w = 0; w < n_barcos; w++) {
                            if (contador == 0) {
                                if (!botones[x - contador].getActivo()) {
                                    botones[x - contador].setColorEleccionVerde();
                                    botones[x - contador].setIluminado(true);
                                    botones[x - contador].setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));
                                } else {
                                    bandera++;
                                }
                            } else if (contador / 10 == n_barcos - 1) {
                                if (!botones[x - contador].getActivo()) {
                                    botones[x - contador].setColorEleccionVerde();
                                    botones[x - contador].setIluminado(true);
                                    botones[x - contador].setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));
                                } else {
                                    bandera++;
                                }
                            } else {
                                if (!botones[x - contador].getActivo()) {
                                    botones[x - contador].setColorEleccionVerde();
                                    botones[x - contador].setIluminado(true);
                                    botones[x - contador].setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.black));
                                } else {
                                    bandera++;
                                }
                            }
                            contador += 10;
                        }
                    }
                }
            }
        }
        ePosibleColocar = bandera == 0;
        if (ePosibleColocar) {
            return true;
        } else {
            for (int x = 0; x < 100; x++) {
                if (botones[x].getIluminado()) {
                    botones[x].setIluminado(false);
                }
            }
            return false;
        }
    }

    /**
     * Borrar Barcos en posición Horizontal
     *
     * @param objeto
     */
    public void barcosHorizontalBorrar(Boton objeto) {
        for (int x = 0; x < 100; x++) {
            if (!botones[x].getActivo()) {
                botones[x].setBackground(new JButton().getBackground());
                botones[x].setBorder(new JButton().getBorder());
            }
        }
    }

    /**
     * Elegir una casilla
     *
     * @param casilla
     */
    public void elegirCasilla(int casilla) {
        proceso = 1;
        if (casilla > -1) {
            //comprobar si hay barco en esa casilla
            if (botones[casilla].getActivo()) {
                botones[casilla].setColorTocado();
                botones[casilla].setTocado(true);
            } //comprobar si no hay barco en esa casilla
            else if (!botones[casilla].getActivo()) {
                botones[casilla].setColorAgua();
            }
            terminarElegirCasilla();
        } else {
            for (int x = 0; x < 100; x++) {
                botones[x].addMouseListener(e3);
            }
        }
    }

    /**
     * Termina de elegir casilla
     */
    public void terminarElegirCasilla() {
        int hundir[] = new int[5];
        hundir[0] = 0;
        hundir[1] = 0;
        hundir[2] = 0;
        hundir[3] = 0;
        hundir[4] = 0;
        for (int x = 0; x < 100; x++) {
            botones[x].removeMouseListener(e3);
            for (int i = 0; i < 5; i++) {
                if (botones[x].getIdBarco() == i + 1) {
                    if (!botones[x].getTocado()) {
                        hundir[i]++;
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (hundir[i] == 0) {
                for (int x = 0; x < 100; x++) {
                    if (botones[x].getIdBarco() == i + 1) {
                        botones[x].setColorHundido();
                        botones[x].setHundido(true);
                        barcosHundidos++;
                        botones[x].setText("" + botones[x].getIdBarco());
                    }
                }
            }
        }
        proceso = 2;
    }

    public Boton[] getBotones() {
        return botones;
    }

    /**
     * Inner class para el evento
     */
    class EanadirBarco implements MouseListener {

        int n_barcos = 0;

        //---METODOS PROPIOS----
        public void barcosHorizontal(Boton objeto) {

            int bandera = 0;

            for (int x = 0; x < 100; x++) {

                int linea = x / 10;
                linea = linea * 10;

                int numero = x - linea;

                int contador = 0;

                if (objeto == botones[x] && !botones[x].getActivo()) {

                    if (numero <= 10 - n_barcos) {
                        for (int y = 0; y <= 10 - n_barcos; y++) {
                            if (objeto == botones[x] && numero == y) {
                                for (int w = 0; w < n_barcos; w++) {
                                    if (contador == 0) {
                                        if (!botones[x + contador].getActivo()) {
                                            botones[x + contador].setColorEleccionVerde();
                                            botones[x + contador].setIluminado(true);
                                            botones[x + contador].setBorder(
                                                    BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));
                                        } else {
                                            bandera++;
                                        }

                                    } else if (contador == n_barcos - 1) {
                                        if (!botones[x + contador].getActivo()) {
                                            botones[x + contador].setColorEleccionVerde();
                                            botones[x + contador].setIluminado(true);
                                            botones[x + contador].setBorder(
                                                    BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));
                                        } else {
                                            bandera++;
                                        }
                                    } else {
                                        if (!botones[x + contador].getActivo()) {
                                            botones[x + contador].setColorEleccionVerde();
                                            botones[x + contador].setIluminado(true);
                                            botones[x + contador].setBorder(
                                                    BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));
                                        } else {
                                            bandera++;
                                        }
                                    }

                                    if (bandera == 0) {
                                        ePosibleColocar = true;
                                    } else {
                                        ePosibleColocar = false;
                                    }

                                    contador++;
                                }

                            }

                        }
                    } else if (numero > 10 - n_barcos) {

                        ePosibleColocar = false;

                        for (int y = 11 - n_barcos; y < 10; y++) {

                            if (objeto == botones[x] && numero == y) {

                                for (int w = 0; w < 10 - y; w++) {

                                    if (contador == 0) {
                                        if (!botones[x + contador].getActivo()) {
                                            botones[x + contador].setColorEleccionRojo();
                                            botones[x + contador].setBorder(
                                                    BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));
                                        }

                                    } else if (contador == n_barcos - 2) {
                                        if (!botones[x + contador].getActivo()) {
                                            botones[x + contador].setColorEleccionRojo();
                                            botones[x + contador].setBorder(
                                                    BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));
                                        }

                                    } else {
                                        if (!botones[x + contador].getActivo()) {
                                            botones[x + contador].setColorEleccionRojo();
                                            botones[x + contador].setBorder(
                                                    BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));
                                        }

                                    }

                                    contador++;
                                }

                            }

                        }
                    }

                }

            }

        }

        public void barcosVertical(Boton objeto) {
            int bandera = 0;
            for (int x = 0; x < 100; x++) {
                int linea = x / 10;
                int contador = 0;
                if (linea >= n_barcos - 1) {
                    for (int y = n_barcos - 1; y < 10; y++) {
                        if (objeto == botones[x] && linea == y) {
                            for (int w = 0; w < n_barcos; w++) {
                                if (contador == 0) {
                                    if (!botones[x - contador].getActivo()) {
                                        botones[x - contador].setColorEleccionVerde();
                                        botones[x - contador].setIluminado(true);
                                        botones[x - contador].setBorder(
                                                BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));
                                    } else {
                                        bandera++;
                                    }
                                } else if (contador / 10 == n_barcos - 1) {
                                    if (!botones[x - contador].getActivo()) {
                                        botones[x - contador].setColorEleccionVerde();
                                        botones[x - contador].setIluminado(true);
                                        botones[x - contador].setBorder(
                                                BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));
                                    } else {
                                        bandera++;
                                    }
                                } else {
                                    if (!botones[x - contador].getActivo()) {
                                        botones[x - contador].setColorEleccionVerde();
                                        botones[x - contador].setIluminado(true);
                                        botones[x - contador].setBorder(
                                                BorderFactory.createMatteBorder(0, 2, 0, 2, Color.black));
                                    } else {
                                        bandera++;
                                    }
                                }
                                if (bandera == 0) {
                                    ePosibleColocar = true;
                                } else {
                                    ePosibleColocar = false;
                                }
                                contador += 10;
                            }
                        }
                    }
                } else if (linea < n_barcos) {
                    ePosibleColocar = false;
                    for (int y = n_barcos; y >= 0; y--) {
                        if (objeto == botones[x] && linea == y) {
                            for (int w = y; w >= 0; w--) {
                                if (contador == 0) {
                                    if (!botones[x - contador].getActivo()) {
                                        botones[x - contador].setColorEleccionRojo();
                                        botones[x - contador].setBorder(
                                                BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));
                                    }
                                } else if (linea == 1) {
                                    if (!botones[x - contador].getActivo()) {
                                        botones[x - contador].setColorEleccionRojo();
                                        botones[x - contador].setBorder(
                                                BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));
                                    }
                                } else {
                                    if (!botones[x - contador].getActivo()) {
                                        botones[x - contador].setColorEleccionRojo();
                                        botones[x - contador].setBorder(
                                                BorderFactory.createMatteBorder(0, 2, 0, 2, Color.black));
                                    }
                                }

                                contador += 10;
                            }
                        }
                    }
                }
            }
        }

        public void barcosHorizontalBorrar(Boton objeto) {
            for (int x = 0; x < 100; x++) {
                if (!botones[x].getActivo()) {
                    botones[x].setBackground(new JButton().getBackground());
                    botones[x].setBorder(new JButton().getBorder());
                }
            }
        }

        public void barcosVerticalBorrar(Boton objeto) {
            for (int x = 0; x < 100; x++) {
                if (!botones[x].getActivo()) {
                    botones[x].setBackground(new JButton().getBackground());
                    botones[x].setBorder(new JButton().getBorder());
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
            if (ePosibleColocar) {
                for (int x = 0; x < 100; x++) {
                    if (botones[x].getIluminado()) {
                        botones[x].setActivo(true);
                        botones[x].setColorActivo();
                        botones[x].setIdBarco(contadorBarco);
                    }
                }
                contadorBarco++;
                terminarAnadirBarco();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Boton objeto = (Boton) e.getSource();
            if (rotacion == 0) {
                barcosHorizontal(objeto);
            } else if (rotacion == 1) {
                barcosVertical(objeto);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Boton objeto = (Boton) e.getSource();
            if (rotacion == 0) {
                barcosHorizontalBorrar(objeto);
            } else if (rotacion == 1) {
                barcosVerticalBorrar(objeto);
            }
            for (int x = 0; x < 100; x++) {
                botones[x].iluminado = false;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        public void setN_barcos(int barcos) {
            n_barcos = barcos;
        }

    }

    /**
     * Inner class para el evento
     */
    class EcambiarRotacion extends EanadirBarco implements KeyListener, ActionListener {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            for (int x = 0; x < 100; x++) {
                this.barcosHorizontalBorrar((Boton) e.getSource());
            }

            if (rotacion == 0) {
                rotacion = 1;
                barcosVertical((Boton) e.getSource());
            } else if (rotacion == 1) {
                rotacion = 0;
                barcosHorizontal((Boton) e.getSource());
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (rotacion == 0) {
                rotacion = 1;
            } else if (rotacion == 1) {
                rotacion = 0;
            }
        }
    }

    /**
     * Inner class para el evento
     */
    class EelegirCasilla implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent arg0) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Boton boton = (Boton) e.getSource();
            if (!(boton.getAgua() || boton.getTocado())) {
                boton.setColorSeleccion();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Boton boton = (Boton) e.getSource();
            if (!(boton.getAgua() || boton.getTocado())) {
                boton.setColorDefault();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Boton boton = (Boton) e.getSource();
            if (boton.getActivo()) //comprobar si hay barco en esa casilla
            {
                boton.setColorTocado();
                boton.setTocado(true);
            } else if (!boton.getActivo()) //comprobar si no hay barco en esa casilla
            {
                boton.setColorAgua();
                boton.setAgua(true);
            }
            terminarElegirCasilla();
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {

        }
    }
}
