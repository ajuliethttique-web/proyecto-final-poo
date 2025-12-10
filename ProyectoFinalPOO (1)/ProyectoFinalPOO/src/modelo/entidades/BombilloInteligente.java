package modelo.entidades;


public class BombilloInteligente extends Dispositivo {
private int brillo;
private String color;


public BombilloInteligente(String id, String nombre, int potencia, String estado, int brillo, String color) {
super(id, nombre, potencia, estado);
this.brillo = brillo;
this.color = color;
}


@Override
public String toCSV() {
return "Bombillo;" + id + ";" + nombre + ";" + potencia + ";" + estado + ";" + brillo + ";" + color;
}
}
