package logica;

public class Fabrica {

    private static Fabrica instancia = null;
    private IControladorUsuario controladorUsuario;

    private Fabrica() {
        controladorUsuario = new ControladorUsuario();
    }

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuario getControladorUsuario() {
        return controladorUsuario;
    }
}
