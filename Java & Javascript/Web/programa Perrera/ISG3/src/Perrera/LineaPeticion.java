package Perrera;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Date;

public class LineaPeticion {
	private Usuario usu;
	private List peticiones;
	private int numeroPeticiones;
	
	static final public int MAX_PETICIONES=3;
	
	public Peticion getPeticion(int num) throws IndexOutOfBoundsException{
		//Coge la peticion indicada por num.
		Peticion dev=null;
		if(0<=num && num<=MAX_PETICIONES)
			dev=(Peticion)peticiones.get(num);
		else
			throw new IndexOutOfBoundsException("Se ha superado el rango de peticiones, debe quedar entre 1 y " + MAX_PETICIONES);

		return dev;
	}
	
	public void addPeticion(Animal ani) throws IndexOutOfBoundsException{
		//a�ade una nueva peticion.
		boolean encontrado=false;
		if (peticiones==null)
			peticiones=new LinkedList();
		if(peticiones.size()<=MAX_PETICIONES){
			for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
				Peticion pet = (Peticion) iter.next();
				if (pet.getAnimal().getNumSerie().equals(ani.getNumSerie())) {
					System.out.println("Este animal ya ha sido a�adido a las peticiones.");
					encontrado = true;
				}
			}				
			if(!encontrado){
				Peticion pet=new Peticion();
				Date fecha= new Date();
				pet.setEstado(Peticion.PENDIENTE_ADJUDICACION);
				pet.setFecha(fecha);
				peticiones.add(pet);
			}
		}else
			throw new IndexOutOfBoundsException("No puede a�adirse mas peticiones, maximo alcanzado");
			
	}

	public void removePeticion(Animal ani){
		boolean encontrado=false;
		Iterator it=peticiones.iterator();
		
		while (!encontrado && it.hasNext()){
			Peticion pet=(Peticion) it.next();
			if (pet.getAnimal().getNumSerie().equals(ani.getNumSerie())){
				it.remove();
				encontrado=true;
			}
		}
	}
	
	public List getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(List peticiones) {
		this.peticiones = peticiones;
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public int getNumeroPeticiones() {
		return numeroPeticiones;
	}

	public void setNumeroPeticiones(int numeroPeticiones) {
		this.numeroPeticiones = numeroPeticiones;
	}	
	
}
