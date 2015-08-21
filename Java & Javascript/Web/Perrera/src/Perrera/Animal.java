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

	public List getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(List peticiones) {
		this.peticiones = peticiones;
	}
	
	public void addPeticionUsuario(Usuario usu){
		if(peticiones==null)
			peticiones=new LinkedList();
		
		ListIterator it=peticiones.listIterator();
		ListIterator itBusqueda=peticiones.listIterator();
		Usuario usuIt=null;
		int pos=0;
		Ficha fic=null;
		boolean encontrado=false;
		boolean fin=false;
		// Primero realizamos una busqueda para ver si el usuario ha realizado la peticion
		
		while(itBusqueda.hasNext() && !encontrado){
			usuIt=(Usuario) itBusqueda.next();
			if(usuIt.getDni().compareTo(usu.getDni())==0){
				encontrado=true;
			}
		}
		
		usuIt=null;
		int contador=0;
		pos=0;
		//Se a�ade en el lugar oportuno, poniendo en orden los usuarios por puntos.
		while(it.hasNext() && !fin && !encontrado){
			usuIt=(Usuario)it.next();
			fic=usuIt.getFichaUsuario();
			if(fic.getPuntos()<usu.getFichaUsuario().getPuntos()){
				pos=contador;
			}else{
				fin=true;
			}
			contador++;
		}
		
		if(!encontrado)
			peticiones.add(pos,usu);
		
	}
	
	public void removePeticionUsuario(Usuario usuario){
		peticiones.remove(usuario);
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
}

