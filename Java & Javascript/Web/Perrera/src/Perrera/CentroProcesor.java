package Perrera;

import java.util.List;

public class CentroProcesor implements ICentroProcesor {

	public List listaCentro(){
		return CentroStore.getInstance().getCentros();
	}
	
	public List listaAnimales(Centro centro){
		return centro.getAnimales();
	}
	
	public List listaAnimales(String IDCentro) {
		List dev=null;
		
		dev=CentroStore.getInstance().getCentro(IDCentro).getAnimales();
		return dev;
	}
	
}
