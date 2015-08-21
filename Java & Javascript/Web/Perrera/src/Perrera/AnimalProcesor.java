package Perrera;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Date;

public class AnimalProcesor implements IAnimalProcesor {

	public List obtenerColaEspera(Animal animal) {
		return animal.getPeticiones();
	}

	public void obetenerCalendario(Animal animal) {
		List listaVacunas=VacunaStore.getInstance().getVacunas();
		Iterator itVacunas=listaVacunas.iterator();
		Iterator itSiguienteVacuna=null;
		List listaAux=new LinkedList();
		Vacuna vacunaActual=null;
		Vacuna vacunaComparada=null;
		Vacuna vacunaAniadir=null;
		int edadVacunacion;
		
		//Observar por si se pude mejorar
		while(itVacunas.hasNext()){
			vacunaActual=(Vacuna)itVacunas.next();
			if(vacunaActual.getTipo()==animal.getEspecie()){
				edadVacunacion=Integer.MAX_VALUE;
				itSiguienteVacuna=itVacunas;
				while(itSiguienteVacuna.hasNext()){
					vacunaComparada=(Vacuna)itSiguienteVacuna.next();
					if(vacunaComparada.getTipo()==animal.getEspecie() && vacunaComparada.getEdadVacunacion()<edadVacunacion){
						edadVacunacion=vacunaComparada.getEdadVacunacion();
						vacunaAniadir=vacunaComparada;
					}
				}
				if (edadVacunacion != Integer.MAX_VALUE){
					Date fechaVacunacion=new Date(animal.getFechaNacimiento().getYear() + edadVacunacion,animal.getFechaNacimiento().getMonth(),animal.getFechaNacimiento().getDate());
					LineaVacuna vac=new LineaVacuna();
					vac.setFechaVacunacion(fechaVacunacion);
					vac.setVac(vacunaAniadir);
					vac.setVacunado(false);
					listaAux.add(vac);
				}
			}
		
		}
		animal.setVacunas(listaAux);

	}

}
