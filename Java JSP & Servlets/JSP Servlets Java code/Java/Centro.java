
/**
 * Clase encargada de controlar los centros.
 */


import java.util.List;

public class Centro implements Comparable {
	
	private String centroID;
	private String Nombre;
	private String Descripcion;
	private String Telefono;
	private String Direccion;
	private String Poblacion;
	private String Provincia;
	private SearchTree Animales;
	private int CodigoPostal;
	
	public Centro(){
		Animales=new SearchTree();
	}
	
	public boolean equals(Object obj){
		boolean res=false;
		String elemento=(String)obj;
		
		res=centroID.equals(elemento);
			
		return res;
	}
	public int compareTo(Object obj){
		int res;		
		if(obj==null)
			throw new NullPointerException("Objeto centro a comparar nulo");
		Centro elemento=(Centro)obj;
		
		if(elemento.getCentroID()==null || this.centroID==null)
			throw new NullPointerException("Objeto centroID a comparar nulo");
		res=centroID.compareTo(elemento.getCentroID());
		
		return res;
	}
	
	public SearchTree getAnimales(){
		//Devuelve la lista de animales del centro
		return Animales;
	}
	
	public int getCodigoPostal() {
		return CodigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) throws IndexOutOfBoundsException{
		String cd=Integer.toString(codigoPostal);
		if(cd.length()==5)
			CodigoPostal = codigoPostal;
		else
			throw new IndexOutOfBoundsException("El codigo postal posee mas de 5 caracteres");
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getPoblacion() {
		return Poblacion;
	}
	public void setPoblacion(String poblacion) {
		Poblacion = poblacion;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) throws IndexOutOfBoundsException{
		if (telefono.length()==9)
			Telefono = telefono;
		else
			throw new IndexOutOfBoundsException("Numero de digitos de telefono incorrecto");
	}

	public String getCentroID() {
		return centroID;
	}

	public void setCentroID(String centroID) {
		this.centroID = centroID;
	}

	public void setAnimales(SearchTree animales) {
		Animales = animales;
	}
	
}
