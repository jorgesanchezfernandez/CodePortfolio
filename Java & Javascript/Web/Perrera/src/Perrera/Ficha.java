package Perrera;
/**
 * Clase que se encarga del baremo y la medicion de puntos por usuario.
 */


public class Ficha {
	private int numHijos;
	private int edadMenor;
	private int M2Casa;
	private int M2Jardin;
	private int nAnimales;
	private int salarioAnual;
	
	public int getPuntos(){
		int dev=0;
		
		dev= numHijos + ( 12 - edadMenor) - nAnimales + (M2Casa / 100)*2 + (M2Jardin/75)*2 + (salarioAnual/1000);
		
		return dev;
	}
	
	public int getEdadMenor() {
		return edadMenor;
	}
	public void setEdadMenor(int edadMenor) {
		this.edadMenor = edadMenor;
	}
	public int getM2Casa() {
		return M2Casa;
	}
	public void setM2Casa(int casa) {
		M2Casa = casa;
	}
	public int getM2Jardin() {
		return M2Jardin;
	}
	public void setM2Jardin(int jardin) {
		M2Jardin = jardin;
	}
	public int getNAnimales() {
		return nAnimales;
	}
	public void setNAnimales(int animales) {
		nAnimales = animales;
	}
	public int getNumHijos() {
		return numHijos;
	}
	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}
	public int getSalarioAnual() {
		return salarioAnual;
	}
	public void setSalarioAnual(int salarioAnual) {
		this.salarioAnual = salarioAnual;
	}
	
}


