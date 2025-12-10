package vista;

import modelo.entidades.*;
import modelo.persistencia.DispositivoDAO;
import controlador.ControlPrincipal;
import javax.swing.*;
import java.awt.*;

public class PanelAdministrador extends JPanel {

    public PanelAdministrador(ControlPrincipal c) {
        setLayout(new BorderLayout());

        // ----- PARTE SUPERIOR: botón volver -----
        JPanel barraSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> c.volverAlPrincipal());
        barraSuperior.add(btnVolver);
        add(barraSuperior, BorderLayout.NORTH);

        // ----- PANEL CENTRAL CON GRID -----
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        JLabel titulo = new JLabel("Gestión de Dispositivos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelCentral.add(titulo, gbc);

        gbc.gridwidth = 1;

        // Lista de dispositivos
        gbc.gridx = 0;
        gbc.gridy = 1;
        String[] dispositivos = {
                "Enchufe Inteligente",
                "Bombillo Inteligente",
                "Termostato",
                "Panel Solar"
        };
        JList<String> listaDispositivos = new JList<>(dispositivos);
        JScrollPane scroll = new JScrollPane(listaDispositivos);
        scroll.setPreferredSize(new Dimension(150, 150));
        panelCentral.add(scroll, gbc);

        // Panel derecho con botones CRUD
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        panelCentral.add(panelBotones, gbc);

        add(panelCentral, BorderLayout.CENTER);
    }


// ==========================================
// Lista de dispositivos cargada desde DAO
// ==========================================
private JList<String> listaDispositivos;
private DefaultListModel<String> modeloLista;
private DispositivoDAO dao = new DispositivoDAO();

private void cargarDispositivos() {
    modeloLista.clear();
    for (Dispositivo d : dao.leerTodos()) {
        modeloLista.addElement(d.getId() + " - " + d.getNombre() + " (" + d.getTipo() + ")");
    }
}

// Llamar este método dentro del constructor del PanelAdministrador después de crear los botones
// Ejemplo dentro del constructor:
// modeloLista = new DefaultListModel<>();
// listaDispositivos = new JList<>(modeloLista);
// add(new JScrollPane(listaDispositivos), BorderLayout.EAST);
// cargarDispositivos();


// ==========================================
//  JDialog para Agregar Dispositivo
// ==========================================
class DialogAgregar extends JDialog {
    private JComboBox<String> cbTipo;
    private JTextField txtId, txtNombre, txtPotencia, txtEstado, txtHorario;
    private JButton btnGuardar, btnCancelar;
    private DispositivoDAO dao;
    private Runnable refrescar;

    public DialogAgregar(JFrame owner, DispositivoDAO dao, Runnable refrescar) {
        super(owner, "Agregar Dispositivo", true);
        this.dao = dao;
        this.refrescar = refrescar;

        setSize(350, 350);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(7, 2, 5, 5));

        cbTipo = new JComboBox<>(new String[]{"Enchufe Inteligente", "Bombillo Inteligente", "Termostato", "Panel Solar"});
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtPotencia = new JTextField();
        txtEstado = new JTextField();
        txtHorario = new JTextField();

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Tipo:")); add(cbTipo);
        add(new JLabel("ID:")); add(txtId);
        add(new JLabel("Nombre:")); add(txtNombre);
        add(new JLabel("Potencia:")); add(txtPotencia);
        add(new JLabel("Estado:")); add(txtEstado);
        add(new JLabel("Horario:")); add(txtHorario);
        add(btnGuardar); add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardar());
    }

    private void guardar() {
        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID y Nombre son obligatorios");
            return;
        }

        Dispositivo d = new Dispositivo(
            cbTipo.getSelectedItem().toString(),
            txtId.getText(),
            txtNombre.getText(),
            txtPotencia.getText(),
            txtEstado.getText(),
            txtHorario.getText()
        );

        dao.agregar(d);
        refrescar.run();
        dispose();
    }
}

// ==========================================
//  JDialog para Modificar Dispositivo
// ==========================================
class DialogModificar extends JDialog {
    private JComboBox<String> cbTipo;
    private JTextField txtId, txtNombre, txtPotencia, txtEstado, txtHorario;
    private JButton btnGuardar, btnCancelar;
    private DispositivoDAO dao;
    private Runnable refrescar;
    private Dispositivo dispositivo;

    public DialogModificar(JFrame owner, DispositivoDAO dao, Runnable refrescar, Dispositivo dispositivo) {
        super(owner, "Modificar Dispositivo", true);
        this.dao = dao;
        this.refrescar = refrescar;
        this.dispositivo = dispositivo;

        setSize(350, 350);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(7, 2, 5, 5));

        cbTipo = new JComboBox<>(new String[]{"Enchufe Inteligente", "Bombillo Inteligente", "Termostato", "Panel Solar"});
        cbTipo.setSelectedItem(dispositivo.getTipo());

        txtId = new JTextField(dispositivo.getId());
        txtId.setEnabled(false);
        txtNombre = new JTextField(dispositivo.getNombre());
        txtPotencia = new JTextField(dispositivo.getPotencia());
        txtEstado = new JTextField(dispositivo.getEstado());
        txtHorario = new JTextField(dispositivo.getHorario());

        btnGuardar = new JButton("Guardar cambios");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Tipo:")); add(cbTipo);
        add(new JLabel("ID:")); add(txtId);
        add(new JLabel("Nombre:")); add(txtNombre);
        add(new JLabel("Potencia:")); add(txtPotencia);
        add(new JLabel("Estado:")); add(txtEstado);
        add(new JLabel("Horario:")); add(txtHorario);
        add(btnGuardar); add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardar());
    }

    private void guardar() {
        dispositivo.setNombre(txtNombre.getText());
        dispositivo.setPotencia(txtPotencia.getText());
        dispositivo.setEstado(txtEstado.getText());
        dispositivo.setHorario(txtHorario.getText());

        dao.modificar(dispositivo);
        refrescar.run();
        dispose();
    }
}
}