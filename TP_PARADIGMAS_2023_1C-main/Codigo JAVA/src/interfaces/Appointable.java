package interfaces;

public interface Appointable<T> {

	boolean canGo(T thing);
	boolean appoint(T thing);
	
}