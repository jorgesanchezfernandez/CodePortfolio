
import java.util.*;
import java.lang.reflect.*;

/**
 * Implementacion del TAD SearchTree utilizando 2 enlaces, uno para cada hijo
 */

public final class SearchTree implements SortedSet, Cloneable {

	/***************************************************************************
	 * Declaración de atributos
	 **************************************************************************/

	/** Referencias al nodo raiz del arbol */
	private Nodo root = null;

	/** Informa del número de nodos que tiene el arbol */
	private int elementCount = 0;

	/** Informa del número de modificaciones estructurales realizadas
	 * en el arbol desde que se construyo. Sólo aumenta, nunca 
	 * disminuye */
	private int modCount = 0;

	/** Almacena el objeto que implementa la interfaz Comparator */
	private Comparator comp;

	/***************************************************************************
	 * Contructores
	 **************************************************************************/

	/**
	 * Construye un arbol vacio, ordenado conforme a la forma natural de
	 * ordenación de los elementos insertados. Los elementos insertados deben
	 * implementar la interfaz <tt>Comparable</tt>. Más aún, la inserción de
	 * un elemento que no implemente esta interfaz provocará el lanzamiento de
	 * la excepción <tt>ClassCastException</tt>
	 * 
	 * @see Comparable
	 */
	public SearchTree() {
		root = null;
		elementCount = 0;
		modCount = 0;
		comp = null;
	}

	/**
	 * Construye un arbol vacio, ordenado conforme al comparador recibido. Los
	 * elementos insertados deben mutuamente poder ser comparados usando
	 * <tt>comparator.compare(e1,e2)</tt>. Más aún, la inserción de un
	 * elemento que no cumpla esta condición provocará el lanzamiento de la
	 * excepción <tt>ClassCastException</tt>
	 * 
	 * @see Comparable
	 * @param c
	 *            el comparator que se usará para ordenar Un valor <tt>null</tt>
	 *            indica que se usara la ordenacion "natural" de los elementos
	 */
	public SearchTree(Comparator c) {
		this();
		if (c != null){
			if (!(c instanceof Comparator))
				throw new ClassCastException("El objeto recibido no implementa Comparator");
			this.comp = c;
		}
	}

	/**
	 * Construye un arbol, ordenado conforme a la forma natural de ordenación de
	 * los elementos insertados. Los elementos insertados deben implementar la
	 * interfaz <tt>Comparable</tt>. Más aún, la inserción de un elemento que
	 * no implemente esta interfaz provocará el lanzamiento de la excepción
	 * <tt>ClassCastException</tt>
	 * 
	 * @see Comparable
	 * 
	 * @param c
	 *            Elementos a insertar.
	 */
	public SearchTree(Collection c) {
		this();
		addAll(c);
	}

	/***************************************************************************
	 * Medodos debidos a la implementación de la interfaz Cloneable
	 **************************************************************************/

	/**
	 * Devuelve la clonación del arbol sobre el que se aplica
	 * 
	 * @return La copia del arbol sobre el que se aplica.
	 * @exception CloneNotSupportedException
	 *                Si los elementos almacenados no tiene implementada la
	 *                interfaz cloneable o no es publica.
	 */
	public Object clone() throws CloneNotSupportedException {
		SearchTree clon=(SearchTree)super.clone();
		clon.root.clone();
		return clon;
	}

	/***************************************************************************
	 * Medodos heredados y refinados de Object
	 **************************************************************************/

	/**
	 * Devuelve un booleano indicando si el árbol sobre el que se invoca tiene
	 * los mismos elementos que x.
	 * 
	 * @param x
	 *            Objeto a comparar.
	 * @return Booleano indicando si los dos arboles son iguales.
	 */
	public boolean equals(Object x) {
		if (x == null || !(x instanceof SortedSet))
			return false;
		// Debo comprobar si tenemos los mismos elementos y
		// en el mismo orden
		boolean b = true;
		Iterator it1 = this.iterator();
		Iterator it2 = ((SortedSet) x).iterator();
		while (it1.hasNext() && it2.hasNext() && b)
			b = b && it1.next().equals(it2.next());
		if (it1.hasNext() || it2.hasNext())
			b = false;
		return b;
	}

	/**
	 * Nos devuelve la representación en una cadena de texto del conjunto
	 * ordenado
	 */
	public String toString() {
		String ret = "";
		Iterator it = this.iterator();
		if (it.hasNext())
			ret = it.next().toString();
		while (it.hasNext())
			ret = ret + "," + it.next().toString();
		return ret;
	}

	/***************************************************************************
	 * Medodos debidos a la implementación de la interfaz Collection
	 **************************************************************************/

	/**
	 * Devuelve <tt>true</tt> si contiene todos los elementos de la colección
	 * recibida
	 * 
	 * @param c
	 *            collection a chequear
	 * @throws ClassCastException
	 *             Si no es del tipo adecuado (Comparable)
	 * @throws NullPointerException
	 *             si algún elemento de la colección es null
	 * @throws NullPointerException
	 *             si c==null
	 * @see #contains(Object)
	 */
	public boolean containsAll(Collection c) {
		if (c == null)
			throw new NullPointerException("Collection c == null");
		boolean ret = true;
		Iterator it = c.iterator();
		while (it.hasNext() && ret) {
			Object obj = it.next();
			if (obj == null)
				throw new NullPointerException("Elemento de Collection c == null");
			ret = ret && this.contains(obj);
		}
		return ret;
	}

	/**
	 * Elimina todos los elementos de la coleccion recibida
	 * 
	 * @param c
	 *            collection a eliminar
	 * @throws ClassCastException
	 *             Si no es del tipo adecuado (Comparable)
	 * @throws NullPointerException
	 *             si algún elemento de la colección es null
	 * @throws NullPointerException
	 *             si c==null
	 * @see #remove(Object)
	 */
	public boolean removeAll(Collection c) {
		if (c == null)
			throw new NullPointerException("Collection c == null");
		boolean ret = false;
		Iterator it = c.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (obj == null)
				throw new NullPointerException("Elemento de Collection c == null");
			ret = ret | this.remove(obj);
		}
		return ret;
	}

	/**
	 * Devuelve un array que contiene todos los elementos del conjunto
	 * 
	 * @return array con todos los elementos.
	 */
	public Object[] toArray() {
		Object[] a = new Object[size()];
		Iterator it = this.iterator();
		int cursor = 0;
		while (it.hasNext()) {
			a[cursor++] = it.next();
		}
		return a;
	}

	/**
	 * Devuelve un array que contiene todos los elementos del conjunto dentro
	 * del array que se recibe como parametro
	 * 
	 * @param a
	 *            array donde se almacenarán los elementos
	 * @return array con todos los elementos.
	 * @throws NullPointerException
	 *             si el array==null.
	 */
	public Object[] toArray(Object a[]) {
		int size = this.size();
		if (a.length < size)
			a = (Object[]) java.lang.reflect.Array.newInstance(a.getClass()
					.getComponentType(), size);
		Iterator it = this.iterator();
		int cursor = 0;
		while (it.hasNext())
			a[cursor++] = it.next();
		if (a.length > size)
			a[size] = null;
		return a;
	}

	/**
	 * Mantiene sólo los elementos que especificados en la colección recibida
	 * como parámetro.
	 * 
	 * @param c
	 *            collection a mantener
	 * @return <tt>true</tt> si el conjunto se modifica
	 * @throws ClassCastException
	 *             si los elementos no puede compararse
	 * @throws NullPointerException
	 *             si el conjunto es c==null
	 * @see #remove(Object)
	 */
	public boolean retainAll(Collection c) {
		if (c == null)
			throw new NullPointerException("Collection c == null");
		boolean ret = false;
		Iterator it = this.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (!c.contains(obj)){
				it.remove();
				ret = true;
			}
		}
		return ret;
	}

	/***************************************************************************
	 * Medodos debidos a la implementación de la interfaz Set
	 **************************************************************************/

	/**
	 * Añade el elemento recibido en el conjunto. Si el elemento ya existe se
	 * devuelve <tt>false</tt>
	 * 
	 * @param o
	 *            elemento a añadir
	 * @return <tt>true</tt> si el elemento no estaba ya incluido
	 * 
	 * @throws ClassCastException
	 *             Si el elmento no soporta comparaciones
	 * @throws NullPointerException
	 *             si el elemento recibido == null
	 */
	public boolean add(Object x) {
		boolean encontrado=false;
		
		if(x==null)
			throw new NullPointerException("Objeto nulo");
		if(root==null){
			root=new Nodo(x);
			encontrado=true;
		}else{
			Nodo actual=root;
			int cmp;
			//Recorremos el arbol.
			while(actual!=null & !encontrado){
				cmp= compare(x,actual.info);
				if(cmp==0){
					actual=null;
				}else{
					if(cmp<0){
						if(actual.izq==null){
							actual.izq=new Nodo(x);
							encontrado=true;
						}else{
							actual=actual.izq;
						}
					}else{
						if(actual.der==null){
							actual.der=new Nodo(x);
							encontrado=true;
						}else{
							actual=actual.der;
						}
					}
				}
			}
		}
		if(encontrado){
			elementCount++;
			modCount++;
		}
		
		return encontrado;
	}

	/**
	 * Añade todos los elementos recibido en la coleccion. Devuelve
	 * <tt>false</tt> sino se modifica el conjunto
	 * 
	 * @param c
	 *            elementos a añadir
	 * @return <tt>true</tt> si se añaden elementos
	 * 
	 * @throws ClassCastException
	 *             Si algún elemento no soporta comporaciones
	 * @throws NullPointerException
	 *             Si algún elemento recibido == null
	 */
	public boolean addAll(Collection c) {
		if (c == null)
			throw new NullPointerException("Collection c == null");
		boolean dev = false;
		Iterator it = c.iterator();
		while (it.hasNext()){
			Object obj = it.next();
			if (obj == null)
				throw new NullPointerException("Elemento de Collection c == null");
			if (this.add(obj))
				dev = true;
		}
		return dev;
	}

	/**
	 * Elimina todos los elementos
	 */
	public void clear() {
		root = null;
		elementCount = 0;
		modCount++;
	}

	/**
	 * Devuelve el comparador usado en el conjunto ordenado
	 */
	public Comparator comparator() {
		return comp;
	}

	/**
	 * Devuelve <tt>true</tt> si el conjunto incluye el objeto recibido como
	 * parámetro
	 * 
	 * @param x
	 *            objeto que buscamos
	 * @return <tt>true</tt> si el objeto se encuentra
	 * 
	 * @throws ClassCastException
	 *             si el objeto no puede comparase
	 */
	public boolean contains(Object x) {
		Nodo actual=root;
		boolean encontrado=false;
		while(actual!=null & !encontrado){
			int cmp= compare(x,actual.info);
			if(cmp==0){
				encontrado=true;
			}else{
				if(cmp<0){
						actual=actual.izq;
				}else{
						actual=actual.der;
				}
			}
		}
		return encontrado;
	}

	/**
	 * Retorna el primer elemento del conjunto ordenado.
	 * 
	 * @return el elemento más bajo del conjunto
	 * @throws NoSuchElementException
	 *             si el conjunto esta vacio
	 */
	public Object first() {
		Object ret = null;
		if (this.root != null) {
			// Si existen elementos en el arbol
			Nodo actual = this.root;
			while (actual != null) {
				ret = actual.info;
				actual = actual.izq;
			}
		} else
			throw new NoSuchElementException("Conjunto vacio");
		return ret;
	}

	/**
	 * Retorna una vista de un rango de elementos del conjunto, hasta
	 * <tt>toElement</tt>, excluido. Los cambios en el conjunto ordenado
	 * origen afectan a la vista y viceversa
	 * 
	 * @param toElement
	 *            limite superior de la vista
	 * @return una vista de la porción de elementos que van hasta
	 *         <tt>toElement</tt>, excluido.
	 * @throws ClassCastException
	 *             Si <tt>toElement</tt> no puede compararse.
	 */
	public SortedSet headSet(Object toElement) {
		return subSet(null, toElement);
	}

  /**
   * Retorna el hash code del conjunto: Suma de todos los hash code de los 
   * elementos almacenados
   * @return valor hash code del conjunto ordenado
   */
  public int hashCode() {
  	int h = 0;
  	Iterator i = this.iterator();
 	  while (i.hasNext()) {
 				Object obj = i.next();
         	if (obj != null)
         		h += obj.hashCode();
    }
  	return h;
  }

	/**
	 * Devuelve <tt>true</tt> si el conjunto esta vacío.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Retorna el iterador inOrden, por defecto en los SearchTree
	 * 
	 * @return el iterador por defecto.
	 */
	public Iterator iterator() {
		return new InOrderIterator();
	}

	/**
	 * Retorna elemento más alto del conjunto
	 * 
	 * @return el elemento mayor del conjunto.
	 * @throws NoSuchElementException
	 *             Si el conjunto esta vacio
	 */
	public Object last() {
		Object ret = null;
		if (this.root != null) {
			// Si existen elementos en el arbol
			Nodo actual = this.root;
			while (actual != null) {
				ret = actual.info;
				actual = actual.der;
			}
		} else
			throw new NoSuchElementException("Conjunto vacio");
		return ret;
	}

	/**
	 * Elimina el elemento especificado del conjunto
	 * 
	 * @param o
	 *            object a eliminar
	 * @return <tt>true</tt> Si el conjunto se modifica
	 * 
	 * @throws ClassCastException
	 *             Si no es del tipo adecuado (Comparable)
	 */

	public boolean remove(Object x) {
		boolean eliminado = false;
		if (x == null)
			throw new NullPointerException("Object x == null");
		if (this.root != null) {
			// Si existen elementos en el arbol
			Nodo actual = this.root;
			Nodo padreActual = null;
			while (actual != null && !eliminado) {
				int cmp = this.compare(x, actual.info);
				if (cmp == 0) {
					// Si es un nodo hoja (sin hijos)
					if (actual.izq == null && actual.der == null) {
						if (padreActual != null) {
							if (padreActual.izq == actual) {
								// Si es un hijo izquierdo
								//		O
								//	X
								padreActual.izq = null;
							} else if (padreActual.der == actual) {
								// Si es un hijo derecho
								//		O
								//			X
								padreActual.der = null;
							}
						} else {
							// Es el unico nodo
							//		X
							root = null;
						}
					}
					// Si tiene un nodo hijo izquierdo
					else if (actual.izq != null && actual.der == null) {
						if (padreActual != null) {
							if (padreActual.izq == actual)
								// Si es un hijo izquierdo
								//			O
								//		X
								//	O
								//	^
								padreActual.izq = actual.izq;
							else if (padreActual.der == actual)
								// Si es un hijo derecho
								//	O
								//		X
								//	O
								//	^
								padreActual.der = actual.izq;
						} else {
							// Es la root
							//		X
							//	O
							//	^
							root = actual.izq;
						}
					}
					// Si tiene un nodo hijo derecho
					else if (actual.izq == null && actual.der != null) {
						if (padreActual != null) {
							if (padreActual.izq == actual)
								// Si es un hijo izquierdo
								//			O
								//		X
								//			O
								//			^
								padreActual.izq = actual.der;
							else if (padreActual.der == actual)
								// Si es un hijo derecho
								//	O
								//		X
								//			O
								//			^
								padreActual.der = actual.der;
						} else {
							// Es la root
							//		X
							//			O
							//			^
							root = actual.der;
						}
					}
					// Si tiene un nodo hijo izquierdo y otro derecho
					else if (actual.izq != null && actual.der != null) {
						// Casos posibles
						//			O			o bien			O			o bien (es nodo raiz)		  
						//		X									X									X
						//	O		O							O		O							O		O
						//	^		^							^		^							^		^
						// Almaceno a quien voy a eliminar
						Nodo nodoEliminado = actual;
						// Debo buscar el nodo mas a la derecha del subarbol
						// izq del actual para sustituir al actual
						padreActual = actual;
						actual = actual.izq;
						while (actual.der != null) {
							padreActual = actual;
							actual = actual.der;
						}
						// Cambiamos la información del nodoEliminado por el mayor de la izquierda
						nodoEliminado.info = actual.info;
						if (padreActual != nodoEliminado) {
							// Si hemos navegado hacia la derecha al menos una vez
							//				O
							//			X	<-(nodoEliminado, ahora con la info de actual)
							//		O	
							//	O		O	<-(padreActual)
							// ...			O	<-(actual)
							//			O	^		
							//		   ...			
							padreActual.der = actual.izq;
						} else{
							// Si no hemos navegado hacia la derecha al menos una vez
							//				O
							//			X	<-(nodoEliminado, ahora con la info de actual)(padreActual)
							//		O	<-(actual)
							//	O	^		
							// ...			
							nodoEliminado.izq = actual.izq;
						}
					}
					// Si llego aqui ya lo he eliminado del arbol
					elementCount--;
					eliminado = true;
					modCount++;
				} else {
					// Actualizo padreActual y actual
					padreActual = actual;
					if (cmp < 0)
						actual = actual.izq;
					else
						// x debe ir a la derecha
						actual = actual.der;
				}
			}
		}
		return eliminado;
	}

	/**
	 * Retorna el número de elementos del conjunto ordenado
	 */
	public int size() {
		return elementCount;
	}

	/**
	 * Retorna una vista de un rango de elementos del conjunto, desde
	 * <tt>fromElement</tt>, inclusive, hasta <tt>toElement</tt>,
	 * excluido. Si ambos limites son iguales el conjunto es vacio. Los cambios
	 * en el conjunto ordenado origen afectan a la vista y viceversa
	 * 
	 * La vista creada lanzará la excepción <tt>IllegalArgumentException</tt>
	 * si se intenta hacer alguna operación fuera de los limites establecidos
	 * 
	 * @param fromElement
	 *            limite inferior de la vista
	 * @param toElement
	 *            limite superior de la vista
	 * @return una vista de la porción de elementos que van desde
	 *         <tt>fromElement</tt>, inclusive, hasta <tt>toElement</tt>,
	 *         excluido.
	 * @throws ClassCastException
	 *             Si <tt>fromElement</tt> o <tt>toElement</tt> no pueden
	 *             compararse.
	 * @throws IllegalArgumentException
	 *             Si <tt>fromElement</tt> es mayor que <tt>toElement</tt>.
	 */
	public SortedSet subSet(Object fromElement, Object toElement) {
		if (fromElement != null && toElement != null
				&& compare(fromElement, toElement) > 0)
			throw new IllegalArgumentException(
					"IllegalArgumentException, fromElement > toElement");
		SortedSet ss = new SearchTree(comp);
		Iterator it = this.iterator();
		while(it.hasNext()){
			Object o = it.next();
			if ( (fromElement == null || compare(o, fromElement) >= 0) && 
					 (toElement == null || compare(o, toElement) < 0) )
	   		ss.add(o);
		}
		return ss;
	}

	/**
	 * Retorna una vista de un rango de elementos del conjunto, desde
	 * <tt>fromElement</tt>, incluido. Los cambios en el conjunto ordenado
	 * origen afectan a la vista y viceversa
	 * 
	 * @param fromElement
	 *            limite inferior de la vista
	 * @return una vista de la porción de elementos que van desde
	 *         <tt>fromElement</tt>, incluido.
	 * @throws ClassCastException
	 *             Si <tt>fromElement</tt> no puede compararse.
	 */
	public SortedSet tailSet(Object fromElement) {
		return subSet(fromElement, null);
	}

	/***************************************************************************
	 * Medodos generados expresamente para SearchTree
	 **************************************************************************/

	/**
	 * Nos devuelve la representación en una cadena de texto del arbol
	 * 
	 * @return Un <code>String</code> que representa al arbol.
	 */
	public String treeToString() {
		return auxToString(0, this.root);
	}

	private String auxToString(int i, Nodo t) {
		String str = new String();
		for (int j = 1; j <= i; j++)
			str += " ";
		if (t != null) {
			str += t.info;
			str += "\n" + auxToString(i + 1, t.izq);
			str += "\n" + auxToString(i + 1, t.der);
		} else
			str += "()";
		return str;
	}

	/**
	 * Permite comparar dos objetos conforme a los parametros que se recibieron
	 * en el constructor del conjunto ordenado
	 */
	private int compare(Object o1, Object o2) {
		int ret = 0;
		try{
			if (this.comp != null) {
				ret = comp.compare(o1, o2);
			} else {
				ret = ((Comparable) o1).compareTo(o2);
			}
		}catch(ClassCastException ex){
			throw new ClassCastException("El objeto recibido como parámetro no implementa la interfaz Comparable" + ex.getMessage() +ex.getCause());			
		}
		return ret;
	}

	/***************************************************************************
	 * Declaración de Clases Internas
	 **************************************************************************/

	/**
	 * Cada uno de los nodos será una instancia de esta clase
	 */

	class Nodo implements Cloneable{

		/** Información almacenada */
		Object info;

		/** Referencias a los nodos izqdo y dcho */
		Nodo izq, der;

		/**
		 * Construye un nodo árbol de búsqueda a partir del objeto info.
		 * 
		 * @param info
		 *            Objeto que se almacenará
		 */
		public Nodo(Object info) {
			this(info, null, null);
		}

		/**
		 * Construye un nodo árbol de búsqueda a partir del objeto info, el
		 * subarbol izquierdo y el derecho.
		 * 
		 * @param info
		 *            Objeto que se almacenará
		 * @param izq
		 *            Referencia al arbol izquierdo
		 * @param der
		 *            Referencia al arbol derecho
		 */
		public Nodo(Object info, Nodo izq, Nodo der) {
			this.info = info;
			this.izq = izq;
			this.der = der;
		}

		/**
		 * Devuelve la clonación del Nodo sobre el que se aplica
		 * 
		 * @return La copia del nodo sobre el que se aplica.
		 * @exception CloneNotSupportedException
		 */
		public Object clone() throws CloneNotSupportedException {
			Nodo clon=(Nodo)super.clone();
			
			clon.info=getClone(this.info);
			if(this.izq!=null)
				clon.izq=(Nodo)this.izq.clone();
			if(this.der!=null)	
				clon.der=(Nodo)this.der.clone();
			return clon;
		}

		/**
		 * Devuelve un clon del objeto recibido como parametro (ver practica 2)
		 */
		private Object getClone(Object o) throws CloneNotSupportedException {
			Object clon = null;
			try {
				Method method = o.getClass().getMethod("clone", null);
				clon = method.invoke(o, null);
			} catch (IllegalAccessException ex1) {
				throw new CloneNotSupportedException(
						"El metodo clone no es publico (" + ex1.toString() + ")");
			} catch (NoSuchMethodException ex2) {
				throw new CloneNotSupportedException(
						"El objeto no implementa la interfaz Cloneable ("
								+ ex2.toString() + ")");
			} catch (InvocationTargetException ex3) {
				throw new CloneNotSupportedException(
						"Error inesperado al llamar al metodo clone ("
								+ ex3.toString() + ")");
			}
			return clon;
		}

	}

	/**
	 * Cada uno de los recorridos InOrder será una instancia de esta clase
	 */

	class InOrderIterator implements Iterator {

		/**
		 * Lista con los objetos del arbol
		 */
		LinkedList listaAux;

		/**
		 * Iterador para recorrer los elementos de la lista auxiliar
		 */
		Iterator it; 
		
		/**
		 * Ultimo objeto devuelto por el iterador
		 */
		Object lastRet; 

		/**
		 * Contador de operaciones de cambio sobre el arbol. Almacenamos el
		 * número de cambios esperado en el conjunto ordenado. El campo modCount
		 * es propio de SearchTree
		 */
		int expectedModCount;

		/**
		 * Método para construir el array auxiliar que almacena los nodos
		 * 
		 * @param t
		 *            Nodo root
		 */
		private void recorridoInOrder(Nodo t) {
			if (t != null) {
				recorridoInOrder(t.izq);
				listaAux.add(t.info);
				recorridoInOrder(t.der);
			}
		}

		/**
		 * Constructor sin parametros. Crea un lista con todos los elementos.
		 */
		public InOrderIterator() {
			if(root!=null){
				listaAux=new LinkedList();
				recorridoInOrder(root);
				it=listaAux.iterator();
				lastRet=null;
				this.expectedModCount = modCount;
			}
		}

		/**
		 * Permite obtener el siguiente elemento
		 * 
		 * @return Objeto que devolvemos
		 */
		public Object next() {
			checkForComodification();
			try{
				lastRet=it.next();
				return lastRet;
			}catch(IndexOutOfBoundsException e){
				throw new NoSuchElementException("Elemento no encontrado");
			}
		}

		/**
		 * Informa si quedan más elementos
		 * 
		 * @return Un booleano que indica si hay más elementos por procesar
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * Permite eliminar el ultimo elemento devuelto
		 */
		public void remove() {
			checkForComodification();
			try {
				it.remove(); // No debe haber problemas pq nadie 
				// toca la lista auxiliar excepto el iterador
			} catch (IllegalStateException e) {
				throw new IllegalStateException(
						"Ha intentado eliminar dos veces el mismo elemento" +
						"o nunca ha llamado al metodo next");
			}
			try {
				SearchTree.this.remove(lastRet);
			} catch (IndexOutOfBoundsException e) {
				throw new ConcurrentModificationException(
						"No existe el elemento a eliminar");
			}
			this.expectedModCount = modCount;
		}

		/**
		 * Comprueba si ha habido comodificaciones en el conjunto (accesos
		 * concurrentes)
		 */
		final void checkForComodification() {
			if (modCount != this.expectedModCount) {
				throw new ConcurrentModificationException(
						"Se ha modificado el conjunto mientras se usaba este iterador");
			}
		}

	}

}