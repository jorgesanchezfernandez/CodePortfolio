
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Date;

public class LineaPeticion {
	private Usuario usu;
	private List peticiones;
	private int numeroPeticiones=0;
	
	static final public int MAX_PETICIONES=3;
	
	public Peticion getPeticion(int num) throws IndexOutOfBoundsException{
		//Coge la peticion indicada por num.
		Peticion dev=null;
		if(0<=num && num<MAX_PETICIONES)
			dev=(Peticion)peticiones.get(num);
		else
			throw new IndexOutOfBoundsException("Se ha superado el rango de peticiones, debe quedar entre 1 y " + MAX_PETICIONES);

		return dev;
	}
	
	public void addPeticion(Animal ani){
		//a�ade una nueva peticion.
		if (peticiones==null)
			peticiones=new LinkedList();
		if(peticiones.size()<=MAX_PETICIONES){
			boolean encontrado=false;
			Iterator iter = peticiones.iterator();
			while(iter.hasNext() && !encontrado) {
				Peticion pet = (Peticion) iter.next();
				if (pet.getAnimal().getNumSerie().compareTo(ani.getNumSerie())==0) {
					encontrado=true;
				}
			}	
			if(!encontrado){
				Peticion pet=new Peticion();
				Date fecha= new Date();
				pet.setAnimal(ani);
				pet.setEstado(Peticion.EN_CARRITO);
				pet.setFecha(fecha);
				numeroPeticiones++;
				peticiones.add(pet);
			}
		}
	}

	public void removePeticion(String numSerie){
		Iterator it=peticiones.iterator();
		Peticion peticion=null;
		boolean encontrado=false;
		while(it.hasNext() && !encontrado){
			peticion=(Peticion)it.next();
			if(peticion.getAnimal().getNumSerie().equals(numSerie))
				encontrado=true;
		}
		if(encontrado){
			peticiones.remove(peticion);
			numeroPeticiones--;
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
