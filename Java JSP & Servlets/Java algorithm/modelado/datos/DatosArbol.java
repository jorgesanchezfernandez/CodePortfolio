package modelado.datos;

import utiles.Coordenada;

public interface DatosArbol extends Datos {
	
	public Double getAltura();
	
	public Integer getAngulo();
	
	public Coordenada getCoordenadasIni();
	
	public Coordenada getCoordenadasFin();
	
}
