package Perrera;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.lang.Comparable;

public interface IPetProcesor {
	public void registraPeticion(Usuario usu, LineaPeticion lPet);
	public void eliminarPeticion(Animal animal,LineaPeticion lp);
}
