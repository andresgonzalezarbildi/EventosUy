package excepciones;

@SuppressWarnings("serial")
public class EventoNoExisteException extends Exception {
    public EventoNoExisteException(String message) {
        super(message);
    }
}
