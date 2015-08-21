﻿package Perrera;
/**
 * Clase encargada de administrar los centro. Utiliza el PD Singleton.
 * Tiene 2 posibles funcionalidades, un listado y una busqueda.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;


public class CentroStore{
	
	private static CentroStore adCentro;
	private List centros;		//lista de centros
	
	public static synchronized CentroStore getInstance(){
		if(adCentro==null)
			adCentro= new CentroStore();
		return adCentro;
	}
	
	public CentroStore(){
		/*
		 *	Constructor de la clase, crea dos centros por defecto
		 */
		centros = new LinkedList();
		
		Centro c1=new Centro();
		c1.setCentroID("01");
		c1.setNombre("El Animal Feliz");
		c1.setDescripcion("Centro Dedicado a la adopcion de todo tipo de animales");
		c1.setCodigoPostal(41013);
		c1.setDireccion("C/Arboleda Nº21");
		c1.setPoblacion("Sevilla");
		c1.setProvincia("Sevilla");
		c1.setTelefono("555678213");
		
		Centro c2=new Centro();
		c2.setCentroID("02");
		c2.setNombre("El pequeño piolin");
		c2.setDescripcion("Centro Dedicado a la adopcion de canarios");
		c2.setCodigoPostal(41003);
		c2.setDireccion("C/Arboleda Nº28");
		c2.setPoblacion("Mairena");
		c2.setProvincia("Sevilla");
		c2.setTelefono("555329837");
		
		centros.add(c1);
		centros.add(c2);
	}
	
	public List getCentros(){
		/*
		 * Nos devuelve la lista con todos los centros
		 */
		return centros;
	}
	
	public Centro getCentro(String nomCentro)throws IndexOutOfBoundsException{
		/*
		 * Nos realiza una busqueda por nombre de centro	==> Nota, podemos utilizar metodo plantilla?
		 */
		Centro result = null;
		Iterator iter = centros.iterator(); 
		while(iter.hasNext() && result==null) {
			Centro cen = (Centro) iter.next();
			if (cen.getCentroID().compareTo(nomCentro) == 0)
				result = cen;
		}
		if(result==null)
			throw new IndexOutOfBoundsException("Centro no disponible");
		return result;				
	}
	
}

