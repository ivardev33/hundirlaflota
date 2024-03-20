package hundirflota;

import javax.swing.*;

/**
 * Clase Texto para el juego
 * @author ilernagames
 */
public class Texto extends JPanel {

    JLabel texto = new JLabel("HUNDIR LA FLOTA");
    
    /**
     * Constuctor de la clase Texto
     */
    public Texto() {
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 25, 0, new JPanel().getBackground()));
        add(texto);
        texto.setFont(this.getFont().deriveFont(17.0f));
    }

    public void setTexto(String texto) {
        this.texto.setText(texto);
    }

    public void setTamanoFuente(float numero) {
        this.texto.setFont(this.texto.getFont().deriveFont(numero));
    }

}
