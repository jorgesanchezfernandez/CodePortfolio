package modelado.problema;

import java.util.List;
//import java.util.Random;

import utiles.Coordenada;
import utiles.CoordenandaImpl;
import utiles.FactoriaDeColecciones;

import modelado.datos.Datos;
import modelado.datos.DatosArbol;
import modelado.datos.DatosArbolNavidadImpl;
import modelado.solucion.Solucion;
import modelado.solucion.SolucionArbolNavidadImpl;

public class ProblemaArbolNavidadImpl implements ProblemaDyV {

	private DatosArbol datos;
	
	//Parametros necesarios para el algoritmo
	private Integer anguloRama;
	private DatosArbol rama;
	private Double altMin;
	private Coordenada cordIni;
	private Integer primProb; //Identifica con un 1 al primer problema

	//Constructor que solo se utiliza fuera de la clase para iniciar un problema
	public ProblemaArbolNavidadImpl(DatosArbolNavidadImpl d1) {
		DatosArbolNavidadImpl dat = new DatosArbolNavidadImpl(d1.getAltura(),0,d1.getCoordenadasIni());
		datos = dat;
		anguloRama= d1.getAngulo();
		
		rama = dat;

		altMin = 0.1;
		cordIni = d1.getCoordenadasIni();
		primProb = 1;
	}
	


	//Constructor que solo se utiliza dentro de la clase para la recursion
	private ProblemaArbolNavidadImpl(DatosArbolNavidadImpl d2, Integer angRama, DatosArbol rama,Coordenada cordIni) {
		datos = d2;
		anguloRama= angRama;
		
		this.rama = rama;

		altMin = 0.1;
		this.cordIni = cordIni;
		primProb = 0 ;
	}


	public boolean esCasoBase() {
		// El algoritmo para la recusión cuando llega a una altura mínima  (la que queramos, p.ej. 0.1)
		
		return (datos.getAltura() <= altMin);
	}


	public Solucion getSolucionCasoBase() {
		// Devuelve el cálculo de cada rama 

		Solucion  sol = new SolucionArbolNavidadImpl (rama);
		
		return sol;
	}

	//Dividimos el problema 
	public List<ProblemaDyV> getSubproblemas() {
		
		List<ProblemaDyV> lSubProb = FactoriaDeColecciones.creaList();
		
		//Lista de alturas de los puntos donde nacen las ramas y alturas de las nuevas ramas
		List<Double> lAlturasNacenRamas = FactoriaDeColecciones.creaList();
				
		//Alturas de cada division del tronco
		Double alt,h,sumH;
		alt = datos.getAltura();
		sumH = 0.0;
		
		if (primProb==1){
			DatosArbolNavidadImpl dPSol = new 	 DatosArbolNavidadImpl (0.0, 0, new CoordenandaImpl(0,0) );
			ProblemaArbolNavidadImpl  p1Sol = new ProblemaArbolNavidadImpl (dPSol,anguloRama,rama,cordIni);
			lSubProb.add(p1Sol);
		}
		
		while (alt > altMin){
			h = alt * 0.25;
			alt = alt - h;
			
			sumH = sumH + h;
			
			lAlturasNacenRamas.add(sumH);
			
		}
		
		//División del problema en dos subproblemas cada vez y cálculo de las coordenadas de cada uno
	
		Integer nuevoAngulo1, nuevoAngulo2;
		Integer anguloArbol = datos.getAngulo();
		
		Coordenada nuevaCord;
		
		Double altNuevaRama;
		
		for (Double altNaceRama : lAlturasNacenRamas){
			
			nuevaCord  = coordFin(datos.getCoordenadasIni(),datos.getAngulo(),altNaceRama); 
			
			//Calculo el nuevo ángulo

			altNuevaRama = (datos.getAltura()- altNaceRama)* 0.55 ;
			if (datos.getCoordenadasIni().getX()!=(cordIni.getX())){
				
				nuevoAngulo1 = anguloArbol + anguloRama;  //Rama de la derecha

				DatosArbolNavidadImpl d1 = new 	 DatosArbolNavidadImpl (altNuevaRama, nuevoAngulo1, nuevaCord );
				ProblemaArbolNavidadImpl  p1 = new ProblemaArbolNavidadImpl (d1,anguloRama,d1,cordIni);	
				lSubProb.add(p1);
				
				//Si no es caso base, hacemos que entre en caso base
				if(altNuevaRama >= altMin){
					DatosArbolNavidadImpl d1Sol = new 	 DatosArbolNavidadImpl (0.0, 0, nuevaCord );
					ProblemaArbolNavidadImpl  p1Sol = new ProblemaArbolNavidadImpl (d1Sol,anguloRama,d1,cordIni);
					lSubProb.add(p1Sol);
				}			
			}
			
			
			nuevoAngulo2 = anguloArbol - anguloRama; //Rama de la izquierda
			DatosArbolNavidadImpl d2 = new 	 DatosArbolNavidadImpl (altNuevaRama, nuevoAngulo2, nuevaCord );
			ProblemaArbolNavidadImpl  p2 = new ProblemaArbolNavidadImpl (d2,anguloRama,d2,cordIni);
			lSubProb.add(p2);
			
			//Si no es caso base, hacemos que entre en caso base
			if(altNuevaRama >= altMin){
				DatosArbolNavidadImpl d2Sol = new 	 DatosArbolNavidadImpl (0.0, 0, nuevaCord );
				ProblemaArbolNavidadImpl  p2Sol = new ProblemaArbolNavidadImpl (d2Sol,anguloRama,d2,cordIni);
				lSubProb.add(p2Sol);
			}
		
		}
		

		return lSubProb;
	}
	
	//Unimos los resultados
	public Solucion combinaSoluciones(List<Solucion> ls) {
		List<DatosArbol> lSol = FactoriaDeColecciones.creaList();
		
		DatosArbolNavidadImpl datInv ;
		CoordenandaImpl cordInvIni;
		CoordenandaImpl cordInvFin;
		
	

		for( Solucion s:ls){
			SolucionArbolNavidadImpl s1 = (SolucionArbolNavidadImpl)s;
			lSol.addAll(s1.getArbol());
		}
		
		if(primProb == 1){
			//Creamos los árboles del lado izquierdo respecto del eje de las Y
			List<DatosArbol> lSolParcial = FactoriaDeColecciones.creaList();
			lSolParcial.addAll(lSol);
			for(DatosArbol dat:lSolParcial){
				//Si son las coordenadas iniciales no la pinta, ya que repintaría el tronco
				Boolean b = dat.getCoordenadasIni().equals(cordIni);
				if(!b){
					datInv = new DatosArbolNavidadImpl (dat);
					datInv.setAngulo( 180 - dat.getAngulo());		//Cambio el ángulo, aunque que para pintar me es indiferente este dato
					cordInvIni = new CoordenandaImpl (cordIni.getX()+(cordIni.getX()- dat.getCoordenadasIni().getX()),dat.getCoordenadasIni().getY()); //Cambio las x
					cordInvFin = new CoordenandaImpl (cordIni.getX()+(cordIni.getX()- dat.getCoordenadasFin().getX()),dat.getCoordenadasFin().getY());
					datInv.setCoordenadasIni(cordInvIni);
					datInv.setCoordenadasFin(cordInvFin);
					
					lSol.add(datInv); //Añado este nuevo arbol a la solución
				}
			}
		}

		Solucion sol = new SolucionArbolNavidadImpl (lSol);	

	    return sol;	
	}
	
	
	public int hashCode() {
		return datos.hashCode();
	}

	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof ProblemaArbolNavidadImpl) {
			ProblemaDyV pme = (ProblemaDyV) obj;
			iguales = datos.equals(pme.getDatos());
		}
		return iguales;
	}

	public Datos getDatos() {

		return datos;
	}


	/**
	 * No podemos comparar la solución pasada por parámetros con una solución externa,
	 *  porque no tenemos otra forma de calcularlo	
	 */
	public Boolean esSolucion(Solucion s) {
		
		return null;
	}
	

	private Coordenada coordFin(Coordenada ini1,Integer angulo1,Double altura1) {
		Integer initX =  ini1.getX();
		Integer initY =  ini1.getY();
		
		//Pasamos de grados a radianes y posteriormente lo pasamos a un entero
		Double gradosARad = Math.PI/180;
		
		Double seno = Math.sin ( angulo1 * gradosARad  ); 
		Double coseno = Math.cos  ( angulo1 * gradosARad ); 
		Double altXSeno = altura1 * 50 * seno;
		Double altXCoseno = altura1* 50 * coseno;
		Integer xPos =  initX + altXSeno.intValue();
		Integer yPos = initY - altXCoseno.intValue();

		
		Coordenada nuevaCord  = new CoordenandaImpl (xPos, yPos);


		
		return nuevaCord;
	
	}
	
	

}
