package vista;

import controlador.ControlPrincipal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private CardLayout cardLayout;
	private JPanel paneles;

	public VentanaPrincipal(ControlPrincipal c) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/Icono.png")));

		setTitle("SmartHomΣ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 500);

		// Panel que almacena todos los paneles
		paneles = new JPanel();
		cardLayout = new CardLayout();
		paneles.setLayout(cardLayout);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(paneles, BorderLayout.CENTER);

		setContentPane(paneles);

		// Agregar el panel principal
		paneles.add(crearPanelPrincipal(c), "Principal");

		setVisible(true);
	}

	private JPanel crearPanelPrincipal(ControlPrincipal c) {

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel lblBienvenida = new JLabel("Bienvenido!!");
		lblBienvenida.setBounds(254, 11, 99, 14);
		panel.add(lblBienvenida);

		JLabel lblIniciarComo = new JLabel("Iniciar Como...");
		lblIniciarComo.setBounds(664, 36, 99, 29);
		panel.add(lblIniciarComo);

		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.setBounds(674, 130, 89, 23);
		btnUsuario.addActionListener(e -> c.rol("Usuario"));
		panel.add(btnUsuario);

		JButton btnAdministrador = new JButton("Administrador");
		btnAdministrador.setBounds(659, 215, 119, 23);
		btnAdministrador.addActionListener(e -> c.rol("Administrador"));
		panel.add(btnAdministrador);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(674, 364, 89, 23);
		btnSalir.addActionListener(e -> System.exit(0));
		panel.add(btnSalir);

		JLabel imgLogo = new JLabel();
		imgLogo.setBounds(10, 36, 552, 396);
		imgLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/LogoMarca1.png")));
		panel.add(imgLogo);

		return panel;
	}

	// Agregar panel al CardLayout
	public void agregarPanel(JPanel p, String nombre) {
		paneles.add(p, nombre);
	}

	// Mostrar un panel específico
	public void mostrarPanel(String nombre) {
		cardLayout.show(paneles, nombre);
	}
}
