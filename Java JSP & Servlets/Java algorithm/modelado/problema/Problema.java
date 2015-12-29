package modelado.problema;

import modelado.datos.Datos;
import modelado.solucion.Solucion;

public interface Problema {
	Datos getDatos();

	Boolean esSolucion(Solucion s);

}
