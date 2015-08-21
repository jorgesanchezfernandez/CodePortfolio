package modelado.solucion;

import java.util.List;

import test.TestArbolNavidad2D;
import utiles.FactoriaDeColecciones;

import modelado.datos.DatosArbol;
import modelado.datos.DatosArbolNavidadImpl;

public class SolucionArbolNavidadImpl implements SolucionArbol {

	private List<DatosArbol> arbol;

	public SolucionArbolNavidadImpl (DatosArbol dat){
		
		arbol = FactoriaDeColecciones.creaList();
		arbol.add ( dat );

	}
	public SolucionArbolNavidadImpl (List<DatosArbol> lDat){

		arbol = FactoriaDeColecciones.creaList();
		arbol.addAll( lDat );
	
	}

	public List<DatosArbol> getArbol() {

		return arbol;
	}

	public int compareTo(Solucion arg0) {
		
		SolucionArbolNavidadImpl arb = (SolucionArbolNavidadImpl) arg0;
		
		Integer cmp = 0;
		
		//Compara el número de ramas del arbol
		if (arbol.size()< arb.getArbol().size()){
			cmp = -1;
		}
		if (arbol.size()> arb.getArbol().size()){
			cmp = 1;
		}
		
		//Comprobamos que todas las ramas de árbol esten en el árbol que nos pasan
		boolean fin;
	
		for (int i = 0; i <= arbol.size() && cmp == 0; i++ ){
			DatosArbolNavidadImpl datArbNav = (DatosArbolNavidadImpl)arbol.get(i);
			fin = false;
			
			for (int j = 0; j <= arb.getArbol().size() && fin == false ; j++ ){
				DatosArbolNavidadImpl datArbArg = (DatosArbolNavidadImpl)arb.getArbol().get(j);
				if ((cmp = datArbNav.compareTo(datArbArg))== 0){
					fin = true;
				}
			}
		}
		//Alguna rama no está, vemos cual es más grande por la suma de las alturas de las ramas
		if (cmp!=0){
			Double suma1 = 0.0;
			Double suma2 = 0.0;
			
			for (DatosArbol datArb: arbol){
				suma1 = suma1 + datArb.getAltura();
			}
			for (DatosArbol datArg: arb.getArbol()){
				suma2 = suma2 + datArg.getAltura();
			}
			suma1 = (suma2 - suma1);
			cmp = suma1.intValue();
		}

		
		return cmp;
	}
	
	public TestArbolNavidad2D dibujarArbol (){
		TestArbolNavidad2D app = new TestArbolNavidad2D(arbol);
		return app;
	}


}
