package modelo.entidades;


public class Termostato extends Dispositivo {
private double temperatura;


public Termostato(String id, String nombre, int potencia, String estado, double temperatura) {
super(id, nombre, potencia, estado);
this.temperatura = temperatura;
}


@Override
public String toCSV() {
return "Termostato;" + id + ";" + nombre + ";" + potencia + ";" + estado + ";" + temperatura;
}
}