package Perrera;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Date;

public class UsuarioStore{
	
	private static UsuarioStore usuStore;
	private List usuarios;
	
	public static synchronized UsuarioStore getInstance(){
		if(usuStore==null)
			usuStore = new UsuarioStore();
		return usuStore;
	}
	
	public UsuarioStore(){
		usuarios= new LinkedList();
			
		Usuario u1=new Usuario();
		u1.setNombre("Pepe");
		u1.setLogin("Pepe");
		u1.setPassword("12345");
		u1.setApellidos("Perez Ruiz");
		u1.setDni("55555555L");
		u1.setDireccion("c/Juan de la cosa Nº7");
		u1.setTelefono("696969696");
		
		Ficha f1=new Ficha();
		f1.setNumHijos(2);
		f1.setEdadMenor(7);
		f1.setM2Casa(200);
		f1.setM2Jardin(50);
		f1.setNAnimales(1);
		f1.setSalarioAnual(18000);
		
		u1.setFichaUsuario(f1);
		
		usuarios.add(u1);
	}
	
	public List getUsuarios(){
		/*
		 * Nos devuelve la lista con todos los centros
		 */
		return usuarios;
	}

	
}

