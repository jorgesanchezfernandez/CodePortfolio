package Perrera;
/**
 * Clase que contiene los datos de la vacuna.
 */

public class Vacuna {
	private String vacunaID;
	private String Nombre;
	private String Descripcion;
	private String Tipo;			//Describe a que animal debe ser puesta.
	private int EdadVacunacion;
	
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public int getEdadVacunacion() {
		return EdadVacunacion;
	}
	public void setEdadVacunacion(int edadVacunacion) {
		EdadVacunacion = edadVacunacion;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getVacunaID() {
		return vacunaID;
	}

	public void setVacunaID(String vacunaID) {
		this.vacunaID = vacunaID;
	}
	
	
}

