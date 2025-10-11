package excepciones;

@SuppressWarnings("serial")
public class PasswordIncorrectaException extends Exception {
    public PasswordIncorrectaException(String msg) { super(msg); }
}
