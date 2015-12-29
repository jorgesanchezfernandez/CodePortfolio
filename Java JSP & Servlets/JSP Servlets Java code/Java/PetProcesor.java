package Perrera;

import java.util.List;
import java.util.Iterator;

public class PetProcesor implements IPetProcesor {

	public void registraPeticion(Usuario usu, LineaPeticion lp){
		//Registra una nueva peticion.
		try{
		
			usu.setLPeticiones(lp);
			Iterator it=lp.getPeticiones().iterator();
			Peticion pet=null;
		
			while(it.hasNext()){
				pet=(Peticion)it.next();
				if(pet.getEstado()==Peticion.EN_CARRITO){
					pet.setEstado(Peticion.PENDIENTE_ADJUDICACION);
					pet.getAnimal().addPeticionUsuario(usu);
				}
			}	
		}catch(Exception e){
			System.out.println("La peticion no pudo ser añadida al usuario" + " " + e.getMessage());
		}		
	}
	
	public void eliminarPeticion(Animal animal, LineaPeticion lp){
		//Elimina el numero de peticion deseada de un usuario
		try{
			lp.removePeticion(animal.getNumSerie());
			animal.removePeticionUsuario(lp.getUsu());
		}catch(Exception e){
			System.out.println("No ha podido eliminarse la peticion");
		}
	}	
}
