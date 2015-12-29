package Perrera;

import java.util.Iterator;
import java.util.List;

public class ProblemaBusqueda extends EsquemaVORAZ {
	Object resultado=null;		//resultado de la busqueda
	Object objetoBuscar=null;		//iteracion
	Iterator it=null;		//iterador
	Object elemento=null;	//Objeto a buscar
	
	public ProblemaBusqueda(){};
	
	public ProblemaBusqueda(Object elemento,Iterator iterador){
		this.elemento=elemento;
		this.it=iterador;
	}
	public Object getSolucion(){
		voraz();
		return resultado;
	}
	
	protected void inicializa(){		
		this.resultado=null;		//==> en caso de no encontrado
	}
	protected boolean fin(){
		boolean res;
		res=(!it.hasNext() || resultado!=null);
		return res;
	}
	protected void seleccionaYElimina(){
		 objetoBuscar= it.next();		
	}
	protected boolean prometedor(){
		return (objetoBuscar.equals(elemento));
	}
	protected void anotaEnSolucion(){
		this.resultado = this.objetoBuscar;
	}
}