/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hundirflota;
import javax.swing.*;
/**
 *
 * @author Ivar
 */
public class VResultado {
    
     public static void mostrarVentanaResultado() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Resultado");
        JLabel label = new JLabel("Es el ganador");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialogo.add(label);
        dialogo.setSize(300, 150);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }
    
}
