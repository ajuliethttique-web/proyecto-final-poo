package modelo.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.add(linea);
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }

        return lineas;
    }

    public static void escribirArchivo(String ruta, List<String> contenido) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            for (String linea : contenido) {
                pw.println(linea);
            }
        } catch (Exception e) {
            System.out.println("Error escribiendo archivo: " + e.getMessage());
        }
    }
}
