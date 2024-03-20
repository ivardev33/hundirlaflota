package hundirflota;

import java.awt.Color;
import javax.swing.JButton;

//TODO �en este clase pueden ir algunos JDialog? Raz�nalo
public class Boton extends JButton {

    boolean activo = false;
    boolean iluminado = false;
    boolean tocado = false;
    boolean agua = false;
    boolean hundido = false;
    int id_barco = 0;

    public static Color color = Color.green;

    /**
     * Constructor de la clase bot�n
     *
     * @param texto cadena de texto a mostrar en el bot�n
     */
    public Boton(String texto) {
        this.setText(texto);
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setIluminado(boolean activo) {
        iluminado = activo;
    }

    public void setIdBarco(int barco) {
        id_barco = barco;
    }

    public void setTocado(boolean bool) {
        tocado = bool;
    }

    public void setAgua(boolean bool) {
        agua = bool;
    }

    public void setHundido(boolean bool) {
        hundido = bool;
    }

    public boolean getActivo() {
        return activo;
    }

    public boolean getIluminado() {
        return iluminado;
    }

    public int getIdBarco() {
        return id_barco;
    }

    public boolean getTocado() {
        return tocado;
    }

    public boolean getAgua() {
        return agua;
    }

    public boolean getHundido() {
        return agua;
    }

    public void setColorEleccionVerde() {
        this.setBackground(new Color(153, 255, 153));
    }

    public void setColorEleccionRojo() {
        this.setBackground(new Color(255, 153, 153));
    }

    public void setColorActivo() {
        this.setBackground(color);
    }

    public void setColorDefault() {
        this.setBackground(new JButton().getBackground());
        this.setBorder(new JButton().getBorder());
    }

    public void setColorSeleccion() {
        this.setBackground(new Color(255, 179, 102));
    }

    public void setColorTocado() {
        this.setBackground(Color.YELLOW);
    }

    public void setColorAgua() {
        this.setBackground(Color.cyan);
    }

    public void setColorHundido() {
        this.setBackground(Color.red);
    }

}
