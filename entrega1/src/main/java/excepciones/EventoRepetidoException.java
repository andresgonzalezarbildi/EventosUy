package excepciones;

@SuppressWarnings("serial")
public class EventoRepetidoException extends Exception {
    public EventoRepetidoException(String message) {
        super(message);
    }
}
