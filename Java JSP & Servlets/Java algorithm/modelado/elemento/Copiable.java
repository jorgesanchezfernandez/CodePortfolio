package modelado.elemento;

public interface Copiable<T> extends Cloneable {
	T clone();
}
