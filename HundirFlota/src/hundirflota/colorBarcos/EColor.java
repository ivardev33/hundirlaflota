package hundirflota.colorBarcos;

import java.awt.*;
import hundirflota.*;
import java.awt.event.*;

//TODO: Para crear un color hay que heredar esta clase
public abstract class EColor implements ActionListener {

    Color color;
    
    /**
     * Constructor de la clase ECOlor
     * @param color color a aplicar
     */
    public EColor(Color color){
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int x = 0; x < 100; x++) {
            Boton boton = Partida.tableroJugador.getBotones()[x];
            if (boton.getActivo()
                    && !boton.getTocado()
                    && !boton.getHundido()) {
                boton.setBackground(color);
            }
        }
    }

}
