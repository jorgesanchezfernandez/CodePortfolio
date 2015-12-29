package Perrera;

import java.util.Comparator;
/* Esta clase crea un orden de puntos para las peticiones de usuario
 */
public class ComparaPuntosUsuario implements Comparator {
/*
 * Ordena los usuarios por puntos segun sus fichas, esta sera de mayor a menor
 */
	public int compare(Object obj1, Object obj2) {

		int res;
		
		if(obj1 instanceof Usuario && obj2 instanceof Usuario){
			Usuario u1=(Usuario)obj1;
			Usuario u2=(Usuario)obj2;
			int puntosU1=u1.getFichaUsuario().getPuntos();
			int puntosU2=u2.getFichaUsuario().getPuntos();
			
			//comparamos los usuarios si son lo mismos
			res=u1.compareTo(u2);
			if(res!=0){
				if(puntosU1>puntosU2)
					res=-1;
				if(puntosU1<puntosU2)
					res=1;
			}
			
		}else
			throw new ClassCastException("Unos del objetos pasado como argumento no es un usuario");
		
		return res;
	}

}
