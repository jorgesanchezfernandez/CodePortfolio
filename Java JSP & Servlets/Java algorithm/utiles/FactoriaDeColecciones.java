package utiles;

import java.util.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class FactoriaDeColecciones {
	
	// private static Set<> vacio = creaSet();
	
	public static <T> SortedSet<T> creaSortedSet(){
		return new TreeSet<T>();
	}
	
    public static <T> SortedSet<T> creaSortedSet(Comparator<? super T> c){
    	SortedSet<T> s=null;
    	if(c==null){
    		s= new TreeSet<T>();
    	} else {
    		s= new TreeSet<T>(c);
    	}
    	return s;
	}
    public static <T> List<T> creaList(){
		return new ArrayList<T>();
	}
    public static <T> Set<T> creaSet(){
    	return new TreeSet<T>();
		
	}
    
    public static <K,V> Map<K,V> creaMap(){
		return new HashMap<K,V>();
	}
}
