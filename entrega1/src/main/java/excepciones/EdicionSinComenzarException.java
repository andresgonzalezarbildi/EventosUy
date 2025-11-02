package excepciones;

@SuppressWarnings("serial")
public class EdicionSinComenzarException extends Exception {
	public EdicionSinComenzarException(String mensaje) {
		super(mensaje);
	}
}
