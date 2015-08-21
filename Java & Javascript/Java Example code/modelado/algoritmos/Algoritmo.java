package modelado.algoritmos;

import utiles.*;
import modelado.solucion.*;

public interface Algoritmo {
	public void ejecuta();
	public Metricas getMetricas();
	public Solucion getSolucion();

}
