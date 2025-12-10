package controlador;

import vista.*;

public class ControlPrincipal {

    private VentanaPrincipal vista;

    public void iniciar() {

        vista = new VentanaPrincipal(this);

        // Registrar panel administrador y panel usuario
        vista.agregarPanel(new PanelAdministrador(this), "Admin");
        vista.agregarPanel(new PanelUsuario(this), "Usuario");
    }

    // Cambiar panel según el rol
    public void rol(String tipoRol) {

        switch (tipoRol) {

            case "Administrador":
                vista.mostrarPanel("Admin");
                break;

            case "Usuario":
                vista.mostrarPanel("Usuario");
                break;
        }
    }

    // Volver al panel principal
    public void volverAlPrincipal() {
        vista.mostrarPanel("Principal");
    }
}
