package Perrera;

import java.util.List;

public class PetProcesor implements IPetProcesor {

	public void registraPeticion(Usuario usu, Animal ani){
		//Registra una nueva peticion.
		try{
			LineaPeticion lp=usu.getLPeticiones();
			lp.addPeticion(ani);
			ani.addPeticionUsuario(usu);
			
		}catch(Exception e){
			System.out.println("La peticion no pudo ser a�adida al usuario");
		}		
	}
	
	public void eliminarPeticion(Usuario usu,int numero){
		//Elimina el numero de peticion deseada de un usuario
		try{
			List peticiones=usu.getLPeticiones().getPeticiones();
				peticiones.remove(numero);
		}catch(Exception e){
			System.out.println("No ha podido eliminarse la peticion");
		}
	}
	
	public void eliminarPeticion(Usuario usu,Animal ani) {
		//Elmina una peticion realizada.
		LineaPeticion lp=usu.getLPeticiones();
		lp.removePeticion(ani);
	}
	
	public void cambiarEstado(Peticion pet, String nuevoEstado){
		if(nuevoEstado == Peticion.PENDIENTE_ADJUDICACION || nuevoEstado == Peticion.PENTICION_ACEPTADA || nuevoEstado == Peticion.PENTICION_CANCELADA || nuevoEstado == Peticion.RECOGIDO)
			pet.setEstado(nuevoEstado);
		else
			throw new IndexOutOfBoundsException("Valor no posible para una peticion");
	}
	
	public List listaPeticiones(Usuario usu){
		return usu.getLPeticiones().getPeticiones();
	}
}
