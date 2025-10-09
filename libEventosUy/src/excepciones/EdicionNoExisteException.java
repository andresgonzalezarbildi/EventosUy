package excepciones;

@SuppressWarnings("serial")
public class EdicionNoExisteException extends Exception {
    
    public EdicionNoExisteException(String mensaje) {
        super(mensaje);
    }
}
