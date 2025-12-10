package modelo.entidades;


public class PanelSolar extends Dispositivo {
private int capacidad;


public PanelSolar(String id, String nombre, int potencia, String estado, int capacidad) {
super(id, nombre, potencia, estado);
this.capacidad = capacidad;
}


@Override
public String toCSV() {
return "PanelSolar;" + id + ";" + nombre + ";" + potencia + ";" + estado + ";" + capacidad;
}
}