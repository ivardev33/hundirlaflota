package hundirflota;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;


public class VBienvenida extends JDialog {

    public VBienvenida(JFrame parent) {
        super(parent, "¡Bienvenido al juego!", true);

        JLabel mensajeLabel = new JLabel("¡Bienvenido al juego,Vamos a hundir Barcos!!");
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton aceptarButton = new JButton("Estoy listo para la guerra");
        aceptarButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(mensajeLabel, BorderLayout.CENTER);
        panel.add(aceptarButton, BorderLayout.SOUTH);
        add(panel);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    public static void mostrarDialogo(JFrame parent) {
        VBienvenida dialog = new VBienvenida(parent);
        dialog.setVisible(true);
    }
}
