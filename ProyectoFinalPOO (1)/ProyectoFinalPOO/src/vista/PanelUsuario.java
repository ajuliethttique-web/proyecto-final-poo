package vista;

import controlador.ControlPrincipal;
import javax.swing.*;

public class PanelUsuario extends JPanel {

    public PanelUsuario(ControlPrincipal c) {

        setLayout(null);

        JLabel titulo = new JLabel("Panel del Usuario");
        titulo.setBounds(150, 20, 200, 30);
        add(titulo);

        // Botón para regresar al menú principal
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 10, 80, 25);
        btnVolver.addActionListener(e -> c.volverAlPrincipal());
        add(btnVolver);

        // Más elementos del usuario
        JLabel lblUsuario = new JLabel("Opciones de usuario…");
        lblUsuario.setBounds(150, 80, 200, 20);
        add(lblUsuario);
    }
}
