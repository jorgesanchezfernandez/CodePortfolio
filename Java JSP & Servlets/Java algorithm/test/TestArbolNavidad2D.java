package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import utiles.CoordenandaImpl;


import modelado.algoritmos.AlgoritmoDyVSinMemoriaImpl;
import modelado.datos.*;
import modelado.problema.ProblemaArbolNavidadImpl;
import modelado.solucion.SolucionArbolNavidadImpl;

import java.util.List;

 public class TestArbolNavidad2D extends JApplet  {
	 
	 /**
	 * Para que no de Warning ponemos la variable serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private List<DatosArbol> arbol;

	 public TestArbolNavidad2D(List<DatosArbol> arbol)
	 {
		 this.arbol = arbol;
	 }

	 public void paint( Graphics g )
	 {
		 g.setColor( Color.red );

		 //Si queremos que lo pinte mas lento quitamos el comentario a estas lineas
		 //int cont = 1;
		 for(DatosArbol datArbol: arbol){
			g.drawLine( datArbol.getCoordenadasIni().getX(), datArbol.getCoordenadasIni().getY(),
			 	datArbol.getCoordenadasFin().getX(), datArbol.getCoordenadasFin().getY() );
			 	
			//System.out.println("Para la rama " + cont + " "+ datArbol.toString() ); 	
			//cont ++;
		 }

	 }

	 
	 public static void main( String args[] )
	 {
		DatosArbolNavidadImpl dat1 = new DatosArbolNavidadImpl(10.0, 70, new CoordenandaImpl (400,600));
			
		ProblemaArbolNavidadImpl prbAN = new ProblemaArbolNavidadImpl(dat1);

			
		AlgoritmoDyVSinMemoriaImpl alg = new AlgoritmoDyVSinMemoriaImpl(prbAN);
		alg.ejecuta();
		System.out.println(alg.getMetricas().getTiempoDeEjecucionNS()*Math.pow(10, -9));
				
		SolucionArbolNavidadImpl solArbol = (SolucionArbolNavidadImpl)alg.getSolucion();
		
		

		 JFrame f = new JFrame("Arbol Fractal");
	     f.addWindowListener(new WindowAdapter() {
	          public void windowClosing(WindowEvent e) {System.exit(0);}
	      });
	     JApplet applet = new TestArbolNavidad2D (solArbol.getArbol());
	     f.getContentPane().add("Center", applet);
	     applet.init();
	     f.pack();
	     f.setSize(new Dimension(800,600));
	     f.show();
	 }
	 
 }