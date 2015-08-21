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
		u1.setNombre("pepe");
		u1.setLogin("pepe");
		u1.setPassword("12345");
		u1.setApellidos("Perez Ruiz");
		u1.setDni("55555555");
		u1.setDireccion("c/Juan de la cosa N�7");
		u1.setTelefono("696969696");
		
		Ficha f1=new Ficha();
		f1.setNumHijos(2);
		f1.setEdadMenor(7);
		f1.setM2Casa(200);
		f1.setM2Jardin(50);
		f1.setNAnimales(1);
		f1.setSalarioAnual(18000);
		
		u1.setFichaUsuario(f1);
		
		Usuario u2=new Usuario();
		u2.setNombre("juan");
		u2.setLogin("juan");
		u2.setPassword("12345");
		u2.setApellidos("Juan Ruiz");
		u2.setDni("55555554");
		u2.setDireccion("c/Juan martinez N�7");
		u2.setTelefono("555099034");
		
		Ficha f2=new Ficha();
		f2.setNumHijos(0);
		f2.setEdadMenor(15);
		f2.setM2Casa(100);
		f2.setM2Jardin(100);
		f2.setNAnimales(2);
		f2.setSalarioAnual(12000);
		
		u2.setFichaUsuario(f2);

		//creamos una peticion realizada guardada en el sistema 
		Animal ani=AnimalStore.getInstance().getAnimal("000A");
		IPetProcesor petPros=new PetProcesor();
		
		LineaPeticion lp1=new LineaPeticion();
		lp1.addPeticion(ani);
		
		petPros.registraPeticion(u1,lp1);
		/*
		LineaPeticion lp2=new LineaPeticion();
		lp2.addPeticion(ani);

		petPros.registraPeticion(u2,lp2);
		*/
		usuarios.add(u1);
		usuarios.add(u2);
	}
	
	public List getUsuarios(){
		/*
		 * Nos devuelve la lista con todos los centros
		 */
		return usuarios;
	}

	public void setUsuario(Usuario usu){
		if (usuarios==null)
			this.usuarios=new LinkedList();
		this.usuarios.add(usu);
	}
}

