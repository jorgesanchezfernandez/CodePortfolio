package modelado.datos;

import modelado.elemento.Copiable;
//import modelado.solucion.SolucionArbolNavidadImpl;
import utiles.Coordenada;
import utiles.CoordenandaImpl;

public class DatosArbolNavidadImpl implements DatosArbol, Copiable<DatosArbolNavidadImpl> {

	private Double altura;
	private Integer angulo;
	private Coordenada coordenadaIni;
	private Coordenada coordenadaFin;
	
	public DatosArbolNavidadImpl(Double altura, Integer angulo, Coordenada cord1) {
		if (altura < 0.0){
			new IllegalArgumentException();				
		}

		this.altura = altura;
		this.angulo = angulo;
		this.coordenadaIni = cord1;
		this.coordenadaFin = coordFin();
		
	}
	
	public DatosArbolNavidadImpl(DatosArbol datArb){

		altura = datArb.getAltura();
		angulo = datArb.getAngulo();
		coordenadaIni = datArb.getCoordenadasIni();
		coordenadaFin = datArb.getCoordenadasFin();
		
		
	}

	private Coordenada coordFin() {
		Integer initX =  coordenadaIni.getX();
		Integer initY =  coordenadaIni.getY();
		
		//Pasamos de grados a radianes y posteriormente lo pasamos a un entero
		Double gradosARad = Math.PI/180;
		
		
		Double seno = Math.sin ( angulo * gradosARad ); 
		Double coseno = Math.cos  ( angulo * gradosARad ); 
		Double altXSeno = altura* 50 * seno;
		Double altXCoseno = altura * 50 * coseno;
		Integer xPos =  initX + altXSeno.intValue();
		Integer yPos = initY - altXCoseno.intValue();
		CoordenandaImpl nuevaCord  = new CoordenandaImpl (xPos, yPos);
		
		return nuevaCord;
	}

	public Double getAltura() {

		return altura;
	}

	public Integer getAngulo() {

		return angulo;
	}
	public void setAngulo(Integer angulo) {

		this.angulo = angulo;
	}
	
	public Coordenada getCoordenadasIni() {

		return coordenadaIni;
	}

	public void setCoordenadasIni(Coordenada cordIni) {

		coordenadaIni = cordIni;
	}
	
	public Coordenada getCoordenadasFin() {

		return coordenadaFin;
	}
	
	public void setCoordenadasFin(Coordenada cordFin) {

		coordenadaFin = cordFin;
	}
	
	public Integer compareTo(DatosArbol datosArbol){
		
		Integer cmp = 0;
		
		if ( datosArbol.getAltura() < altura ){
			cmp = -1;
		}
		if( datosArbol.getAltura() > altura ){
			cmp = 1;
		}
		
		return cmp;
	}
	
	public String toString(){
		return "las coordenadas son: (" + getCoordenadasIni().getX() + ","
					+ getCoordenadasIni().getY()+ ") del inicio y (" 
					+ getCoordenadasFin().getX() + "," 
					+ getCoordenadasFin().getY() + ")"	;
	}
	
	public DatosArbolNavidadImpl clone(){
		DatosArbolNavidadImpl obj=null;
        try{
            obj=(DatosArbolNavidadImpl)super.clone();
            obj.setCoordenadasIni(new CoordenandaImpl(coordenadaIni.getX(),coordenadaIni.getY()));
            obj.setCoordenadasFin(new CoordenandaImpl(coordenadaFin.getX(),coordenadaFin.getY()));
            
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
	
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof DatosArbolNavidadImpl) {
			DatosArbolNavidadImpl eg = (DatosArbolNavidadImpl) obj;

			if (altura==eg.getAltura()){
				if(angulo==eg.getAngulo()){
					iguales = coordenadaIni.equals(eg.coordenadaIni) && coordenadaFin.equals(eg.coordenadaFin);
				}
			}			
		}
		return iguales;
	}


}
