package Perrera;
/**
 * Clase que se encarga de controlar el estado de las peticiones del usuario
 */

import java.util.*;
import java.lang.Comparable;

public class Peticion implements Comparable{
	
	public static final String EN_CARRITO="En carrito";
	public static final String PENDIENTE_ADJUDICACION="Pendiente Adjudicacion";
	public static final String PENTICION_CANCELADA="Peticion Cancelada";
	public static final String PENTICION_ACEPTADA="Peticion Aceptada";
	public static final String RECOGIDO="Recogido";

	private String estado;
	private Date fecha;
	private Animal anim;
	private LineaPeticion lpet;
	
	public LineaPeticion getLpet() {
		return lpet;
	}

	public void setLpet(LineaPeticion lpet) {
		this.lpet = lpet;
	}

	public int compareTo(Object obj){
		Animal anims=(Animal) obj;
		int cmp=this.anim.compareTo(anims);
		return cmp;
	}
	
	public Animal getAnimal() {
		return anim;
	}
	public void setAnimal(Animal a) {
		this.anim = a;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}


