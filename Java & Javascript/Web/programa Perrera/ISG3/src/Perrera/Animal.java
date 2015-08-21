package Perrera;


import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.lang.Comparable;
import java.util.Date;

public class Animal implements Comparable{
	private String nombre;
	private Date fechaNacimiento;
	private int peso;
	private String especie;
	private String numSerie;
	private Centro cent;
	private List vacunaciones;
	private List peticiones;
	
	public List getVacunas(){
		return vacunaciones;
	}
	
	public void setVacunas(List vac){
		vacunaciones=vac;
	}
	
	public void setCentro(Centro c){
		cent=c;
	}
	
	public Centro getCentro(){
		return cent;
	}
	
	public int compareTo(Object obj){
		Animal anim=(Animal) obj;
		int cmp=this.numSerie.compareTo(anim.getNumSerie());
		return cmp;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Centro getCent() {
		return cent;
	}

	public void setCent(Centro cent) {
		this.cent = cent;
	}

	public List getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(List peticiones) {
		this.peticiones = peticiones;
	}
	
	public void addPeticionUsuario(Usuario usu){
		ListIterator it=peticiones.listIterator();
		ListIterator itBusqueda=peticiones.listIterator();
		Peticion petIt=null;
		Ficha fic=null;
		boolean encontrado=false;
		boolean peticionRealizada=false;
		
		// Primero realizamos una busqueda para ver si el usuario ha realizado la peticion
		
		while(itBusqueda.hasNext() && !encontrado){
			petIt=(Peticion) itBusqueda.next();
			if(petIt.getLpet().getUsu().getDni().equals(usu.getDni())){
				peticionRealizada=true;
			}
		}
		
		//Se a�ade en el lugar oportuno, poniendo en orden los usuarios por puntos.
		while(it.hasNext() && !encontrado && !peticionRealizada){
			petIt=(Peticion)it.next();
			fic=petIt.getLpet().getUsu().getFichaUsuario();
			if(fic.getPuntos()<usu.getFichaUsuario().getPuntos()){
				it.add(usu);
				encontrado=true;
			}
		}
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
}

