package modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Dispositivo;

public class DispositivoDAO {

    // 🔹 Convierte objetos en texto y viceversa

    private String toTxt(Dispositivo d) {
        return d.getTipo() + ";" +
               d.getId() + ";" +
               d.getNombre() + ";" +
               d.getPotencia() + ";" +
               d.getEstado() + ";" +
               d.getHorario();
    }

    private Dispositivo fromTxt(String linea) {
        String[] p = linea.split(";");
        return new Dispositivo(
            p[0], // tipo
            p[1], // id
            p[2], // nombre
            p[3], // potencia
            p[4], // estado
            p[5]  // horario
        );
    }


    // ======================
    //        CRUD
    // ======================

    public List<Dispositivo> leerTodos() {
        List<String> lineas = Persistencia.leerArchivo(RutaArchivos.ARCHIVO_DISPOSITIVOS);
        List<Dispositivo> dispositivos = new ArrayList<>();

        for (String linea : lineas) {
            dispositivos.add(fromTxt(linea));
        }

        return dispositivos;
    }

    public void guardarTodos(List<Dispositivo> lista) {
        List<String> salida = new ArrayList<>();

        for (Dispositivo d : lista) {
            salida.add(toTxt(d));
        }

        Persistencia.escribirArchivo(RutaArchivos.ARCHIVO_DISPOSITIVOS, salida);
    }

    public void agregar(Dispositivo d) {
        List<Dispositivo> dispositivos = leerTodos();
        dispositivos.add(d);
        guardarTodos(dispositivos);
    }

    public void eliminar(String idBuscado) {
        List<Dispositivo> dispositivos = leerTodos();

        dispositivos.removeIf(d -> d.getId().equals(idBuscado));

        guardarTodos(dispositivos);
    }

    public void modificar(Dispositivo dActualizado) {
        List<Dispositivo> dispositivos = leerTodos();

        for (int i = 0; i < dispositivos.size(); i++) {
            if (dispositivos.get(i).getId().equals(dActualizado.getId())) {
                dispositivos.set(i, dActualizado);
                break;
            }
        }

        guardarTodos(dispositivos);
    }
}
