
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Date;

public class Animal implements Comparable{
	private String nombre;
	private Date fechaNacimiento;
	private int peso;
	private String especie;
	private String numSerie;
	private Centro cent;
	private List vacunaciones;		//Lineas de vacunas
	private SearchTree peticiones;		//Peticiones de los usuarios.


	public Animal(){
		ComparaPuntosUsuario cmp=new ComparaPuntosUsuario();
		peticiones=new SearchTree(cmp);
		vacunaciones=new LinkedList();
	}
	
	public boolean equals(Object obj){
		boolean res=false;
		String elemento=(String)obj;
		
		res=numSerie.equals(elemento);
			
		return res;
	}

	public int compareTo(Object obj){
		int res;
		if(obj==null)
			throw new NullPointerException("Objeto animal a comparar nulo");
		Animal elemento=(Animal)obj;

		if(elemento.getNumSerie()==null || this.numSerie==null)
			throw new NullPointerException("Objeto NumSerie a comparar nulo");
		
		res=numSerie.compareTo(elemento.getNumSerie());
		
		return res;
	}
	
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

	public SearchTree getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(SearchTree peticiones) {
		this.peticiones = peticiones;
	}
	
	public void addPeticionUsuario(Usuario usu){
		/*Añade un usuario a las peticiones del animal, estara ordenado por puntuaciones
		 * de usuario.
		 */
				
		//Añadimos en el arbol el usuario, en caso contrario lanzamos una excepcion
		if(!peticiones.add(usu))
			throw new IndexOutOfBoundsException("Usuario ya añadido a la lista");
	}
		
		
	public void removePeticionUsuario(Usuario usuario){
		if(!peticiones.remove(usuario))
			throw new IndexOutOfBoundsException("El usuario no ha realizado esta peticion de animal");
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
}

