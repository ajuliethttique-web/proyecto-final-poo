package modelo.entidades;


public class EnchufeInteligente extends Dispositivo {
private String horario;


public EnchufeInteligente(String id, String nombre, int potencia, String estado, String horario) {
super(id, nombre, potencia, estado);
this.horario = horario;
}


@Override
public String toCSV() {
return "Enchufe;" + id + ";" + nombre + ";" + potencia + ";" + estado + ";" + horario;
}
}