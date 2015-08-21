package Perrera;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.lang.Comparable;

public interface IPetProcesor {
	public void registraPeticion(Usuario usu, Animal ani);
	public void eliminarPeticion(Usuario usu,int numero);
	public void eliminarPeticion(Usuario usu,Animal ani);
	public void cambiarEstado(Peticion pet, String nuevoEstado);
	public List listaPeticiones(Usuario usu);
}