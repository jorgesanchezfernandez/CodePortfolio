package modelado.problema;

import java.util.List;

import modelado.solucion.Solucion;

public interface ProblemaDyV extends Problema {
	boolean esCasoBase();

	Solucion getSolucionCasoBase();

	List<ProblemaDyV> getSubproblemas();

	Solucion combinaSoluciones(List<Solucion> ls);

}
