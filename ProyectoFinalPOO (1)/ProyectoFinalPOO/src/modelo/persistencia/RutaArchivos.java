package modelo.persistencia;

import java.io.File;

public class RutaArchivos {

    public static final String CARPETA_DATA = "data";
    public static final String ARCHIVO_DISPOSITIVOS = CARPETA_DATA + "/dispositivos.txt";

    static {
        inicializar();
    }

    private static void inicializar() {
        File carpeta = new File(CARPETA_DATA);
        if (!carpeta.exists()) carpeta.mkdir();

        File archivo = new File(ARCHIVO_DISPOSITIVOS);
        try {
            if (!archivo.exists()) archivo.createNewFile();
        } catch (Exception e) {
            System.out.println("Error creando archivo de dispositivos: " + e.getMessage());
        }
    }
}
