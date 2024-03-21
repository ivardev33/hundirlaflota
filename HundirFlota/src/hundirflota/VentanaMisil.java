 
package hundirflota;
import javax.swing.*;

public class VentanaMisil {
    
    public static void mostrarVentanaImpacto() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("¡Impacto!");
        JLabel label = new JLabel("¡Un misil ha impactado en uno de tus barcos!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialogo.add(label);
        dialogo.setSize(300, 150);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }
}