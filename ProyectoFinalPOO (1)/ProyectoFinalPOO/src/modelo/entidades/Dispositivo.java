package modelo.entidades;

public abstract class Dispositivo {
	protected String tipo;
	protected String id;
	protected String nombre;
	protected int potencia;
	protected String estado;
	protected String horario;

	public Dispositivo(String tipo, String id, String nombre, int potencia, String estado, String horario) {
		this.tipo = tipo;
		this.id = id;
		this.nombre = nombre;
		this.potencia = potencia;
		this.estado = estado;
		this.horario = horario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public abstract String toCSV();
}