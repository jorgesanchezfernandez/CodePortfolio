package Perrera;
/**
 * Clase encargada de realizar un usuario
 */


import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Date;

public class Usuario {
	private String Nombre;
	private String Login;
	private String Password;
	private String Apellidos;
	private String Dni;
	private String Direccion;
	private String Telefono;
	private Ficha fichaUsuario;
	private LineaPeticion LPeticiones;
	
	public String getTelefono(){
		return Telefono;
	}
	
	public void setTelefono(String tlf) throws IndexOutOfBoundsException{
		if(tlf.length()==9)
			this.Telefono=tlf;
		else
			throw new IndexOutOfBoundsException("Los digitos del telefono de usuario son incorrectos");
	}
	
	public void setficha(Ficha fUsuario){
		this.fichaUsuario=fUsuario;
	}
	
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) throws IndexOutOfBoundsException{
		if(dni.length()==8)
			Dni = dni;
		else
			throw new IndexOutOfBoundsException("Digitos del dni del usuario incorrectos");
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Ficha getFichaUsuario() {
		return fichaUsuario;
	}

	public void setFichaUsuario(Ficha fichaUsuario) {
		this.fichaUsuario = fichaUsuario;
	}

	public LineaPeticion getLPeticiones() {
		return LPeticiones;
	}

	public void setLPeticiones(LineaPeticion peticiones) {
		LPeticiones = peticiones;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
}

