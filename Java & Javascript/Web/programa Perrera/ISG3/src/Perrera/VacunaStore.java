package Perrera;
/**
 * Clase que administra las bacunas. PD Singleton. Tiene 1 funcionalidad de busqueda
 * de vacuna a unos animales concretos.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class VacunaStore {

	private static VacunaStore adVacuna;
	private List vacunas;		//lista de vacunas
	
	public static synchronized VacunaStore getInstance(){
		if(adVacuna==null)
			adVacuna= new VacunaStore();
		return adVacuna;
	}
	
	private VacunaStore(){
		/*
		 *	Constructor de la clase, crea dos vacunas por defecto
		 */
		vacunas = new LinkedList();
		
		Vacuna v1=new Vacuna();
		v1.setNombre("Rabia");
		v1.setDescripcion("vacuna que elimina la rabia");
		v1.setEdadVacunacion(3);
		v1.setTipo("perro");
		
		Vacuna v2=new Vacuna();
		v2.setNombre("hepatitis");
		v2.setDescripcion("vacuna que elimina la hepatitis");
		v2.setEdadVacunacion(2);
		v2.setTipo("gatos");

		vacunas.add(v1);
		vacunas.add(v2);
	}

	public Vacuna getVacuna(String vacunaID){
		/*
		 * Nos realiza una busqueda por nombre de centro	==> Nota, podemos utilizar metodo plantilla?
		 */
		Vacuna result = null;
		Iterator iter = vacunas.iterator();
		while(iter.hasNext() && result==null) {
			Vacuna van = (Vacuna) iter.next();
			if (van.getVacunaID().compareTo(vacunaID) == 0) {
				result = van;
			}
		}
		return result;				
	}
	
	public List getVacunas(){
		return vacunas;
	}
	
}

