package excepciones;

@SuppressWarnings("serial")
public class TipoRegistroRepetidoException extends Exception {
    public TipoRegistroRepetidoException(String message) {
        super(message);
    }
}