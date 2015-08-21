package utiles;

public class CoordenandaImpl implements Coordenada {
 
	private Integer x;
	private Integer y;
	
	public CoordenandaImpl (Integer x,Integer y){
		this.x = x;
		this.y = y;
	}
	
	public Integer getX() {
		
		return x;
	}

	public Integer getY() {
		
		return y;	
	}
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof CoordenandaImpl) {
			CoordenandaImpl pme = (CoordenandaImpl) obj;
			iguales = ((x==pme.getX()) && (y==pme.getY()));
		}
		return iguales;
	}
}
