package Bazura;

import java.lang.Cloneable;

public class Enlazable implements Cloneable{
	private Object info=null;
	private Enlazable siguiente=null;
	
	public Object clone() throws CloneNotSupportedException{
		Object dev=super.clone();
		
		
		return dev;
	}
	
}
