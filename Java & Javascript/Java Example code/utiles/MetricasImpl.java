package utiles;

public class MetricasImpl implements Metricas {
    private int numDeIteraciones = 0;
    private int numDeLLamadasRecursivas = 0;
    private int numDePasos = 0;
    private int numDeUsosDeLaMemoria = 0;
    private long tiempoDeEjecucionInicial;
    private long tiempoDeEjecucionFinal;

    private long tiempoDeEjecucionInicialNanoS;
    private long tiempoDeEjecucionFinalNanoS;

    private int numDeCasosBase = 0;
    private int numDeVecesQueFiltra = 0;

    public MetricasImpl() {

    }

    public int getNumDeIteraciones() {
	return numDeIteraciones;
    }

    public int getNumDeLLamadasRecursivas() {
	return numDeLLamadasRecursivas;
    }

    public int getNumDePasos() {
	return numDePasos;
    }

    public int getNumDeUsosDeLaMemoria() {
	return numDeUsosDeLaMemoria;
    }

    public long getTiempoDeEjecucion() {
	return tiempoDeEjecucionFinal - tiempoDeEjecucionInicial;
    }

    public int getNumDeCasosBase() {
	return numDeCasosBase;
    }

    public void addIteracion() {
	numDeIteraciones++;
    }

    public void addLLamadaRecursiva() {
	numDeLLamadasRecursivas++;
    }

    public void addPaso() {
	numDePasos++;
    }

    public void addUsoDeLaMemoria() {
	numDeUsosDeLaMemoria++;
    }

    public void setTiempoDeEjecucionInicial() {
	tiempoDeEjecucionInicial = System.currentTimeMillis();
	tiempoDeEjecucionInicialNanoS = System.nanoTime();
    }

    public void setTiempoDeEjecucionFinal() {
	tiempoDeEjecucionFinal = System.currentTimeMillis();
	tiempoDeEjecucionFinalNanoS = System.nanoTime();
    }

    public void addCasoBase() {
	numDeCasosBase++;
    }

    public int getNumDeVecesQueFiltra() {
	return numDeVecesQueFiltra;
    }

    public void addUnFiltro() {
	numDeVecesQueFiltra++;
    }

    public String toString() {
	String s = "Número de Iteraciones         = " + numDeIteraciones + "\n"
		+ "Número de Llamadas Recursivas = " + numDeLLamadasRecursivas
		+ "\n" + "Número de Pasos               = " + numDePasos + "\n"
		+ "Numero de Usos de la Memoria  = " + numDeUsosDeLaMemoria
		+ "\n" + "Tiempo de Ejecucion en mls    = "
		+ getTiempoDeEjecucion() + "\n"
		+ "Tiempo de ejecución en ns     = " + getTiempoDeEjecucionNS()
		+ "\n" + "Número de Casos Base          = " + numDeCasosBase
		+ "\n" + "Número de Veces que Filtra    = "
		+ numDeVecesQueFiltra;
	return s;
    }

    public long getTiempoDeEjecucionNS() {
	return tiempoDeEjecucionFinalNanoS - tiempoDeEjecucionInicialNanoS;
    }
}
