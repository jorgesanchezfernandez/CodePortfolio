package Perrera;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Date;

public class AnimalStore{
	
	private static AnimalStore aniStore;
	private List animales;
	
	public static synchronized AnimalStore getInstance(){
		if(aniStore==null)
			aniStore = new AnimalStore();
		return aniStore;
	}
	
	public AnimalStore(){
		animales= new LinkedList();
		
		Animal a1=new Animal();
		a1.setNombre("Tobi");
		a1.setFechaNacimiento(new Date(2000,11,3));
		a1.setPeso(9);
		a1.setEspecie("canina");
		a1.setNumSerie("000A");
		a1.setVacunas(new LinkedList());
		a1.setPeticiones(new LinkedList());
		a1.setCentro(CentroStore.getInstance().getCentro("02"));
		CentroStore.getInstance().getCentro("02").getAnimales().add(a1);
		
		Animal a2=new Animal();
		a2.setNombre("Rantamplan");
		a2.setFechaNacimiento(new Date(2002,6,9));
		a2.setPeso(6);
		a2.setEspecie("canina");
		a2.setNumSerie("000B");
		a2.setVacunas(new LinkedList());
		a2.setPeticiones(new LinkedList());
		a2.setCentro(CentroStore.getInstance().getCentro("02"));
		CentroStore.getInstance().getCentro("02").getAnimales().add(a2);
		
		Animal a3=new Animal();
		a3.setNombre("Tom");
		a3.setFechaNacimiento(new Date(1995,1,1));
		a3.setPeso(3);
		a3.setEspecie("felina");
		a3.setNumSerie("000C");
		a3.setVacunas(new LinkedList());
		a3.setPeticiones(new LinkedList());
		a3.setCentro(CentroStore.getInstance().getCentro("01"));
		CentroStore.getInstance().getCentro("01").getAnimales().add(a3);

		//a�adimos en la lista de animales.
		animales.add(a1);
		animales.add(a2);
		animales.add(a3);
		
	
	}
	
	public List getAnimales(){
		/*
		 * Nos devuelve la lista con todos los centros
		 */
		return animales;
	}
	
	public Animal getAnimal(String numSerie){
		Animal res=null;
		List lista=AnimalStore.getInstance().getAnimales();
		Iterator it=lista.iterator();
		Animal animalIt=null;
		
		while(it.hasNext() && res==null){
			animalIt=(Animal) it.next();
			if(animalIt.getNumSerie().compareTo(numSerie)==0){
				res=animalIt;
			}
		}
		return res;
	}
}

