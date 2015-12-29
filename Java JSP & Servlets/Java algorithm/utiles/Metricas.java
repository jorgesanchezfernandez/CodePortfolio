package utiles;

public interface Metricas {
    long getTiempoDeEjecucion();

    void setTiempoDeEjecucionInicial();

    void setTiempoDeEjecucionFinal();

    int getNumDeIteraciones();

    void addIteracion();

    int getNumDePasos();

    void addPaso();

    int getNumDeUsosDeLaMemoria();

    void addUsoDeLaMemoria();

    int getNumDeLLamadasRecursivas();

    void addLLamadaRecursiva();

    int getNumDeCasosBase();

    void addCasoBase();

    int getNumDeVecesQueFiltra();

    void addUnFiltro();
    
    long getTiempoDeEjecucionNS();
}
