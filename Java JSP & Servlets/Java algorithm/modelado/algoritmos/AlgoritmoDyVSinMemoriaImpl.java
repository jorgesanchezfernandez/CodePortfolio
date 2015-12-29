package modelado.algoritmos;

import java.util.List;

import modelado.problema.ProblemaDyV;
import modelado.solucion.Solucion;
import utiles.FactoriaDeColecciones;
import utiles.Metricas;
import utiles.MetricasImpl;

public final class AlgoritmoDyVSinMemoriaImpl implements AlgoritmoDyV {
	private ProblemaDyV problema;
	private Solucion solucion;
	private Metricas metricas;

	/**
	 * Constructor que da cuerpo a los metodos de la interfaz que resuelven el
	 * modelado.problema por esta técnica.
	 * 
	 * @param p
	 *            :modelado.problema que debe implementar.
	 */
	public AlgoritmoDyVSinMemoriaImpl(ProblemaDyV p) {
		problema = p;
		metricas = new MetricasImpl();
	}

	/**
	 * Metodo que hay que invocar para resolver el modelado.problema.
	 */
	public void ejecuta() {
		metricas.setTiempoDeEjecucionInicial();
		solucion = dYV(problema);
		metricas.setTiempoDeEjecucionFinal();
	}

	/**
	 * Algoritmo de Divide y Venceras sin memoria
	 * 
	 * @param p
	 *            : problemaDyV
	 * @return solucion
	 */
	private Solucion dYV(ProblemaDyV p) {
		Solucion s = null;
		metricas.addLLamadaRecursiva();
		if (p.esCasoBase()) {
			s = p.getSolucionCasoBase();
			metricas.addCasoBase();
		} else {
			List<ProblemaDyV> problemas = p.getSubproblemas();
			List<Solucion> soluciones = FactoriaDeColecciones.creaList();
			for (ProblemaDyV pr : problemas) {
				s = dYV(pr);
				soluciones.add(s);
			}
			s = p.combinaSoluciones(soluciones);
		}
		return s;
	}

	/**
	 * @return solucion obtenida por el metodo dYV.
	 */
	public Solucion getSolucion() {
		return solucion;
	}

	/**
	 * @return tiempo de ejecucion, numero de llamadas recursivas, numero de
	 *         invocaciones al caso base.
	 */
	public Metricas getMetricas() {
		return metricas;
	}

}
