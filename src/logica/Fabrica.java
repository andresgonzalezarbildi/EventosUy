package logica;

public class Fabrica {

    private static Fabrica instancia = null;
    private IControladorUsuario controladorUsuario;
    private IControladorEvento controladorEvento;

    private Fabrica() {
        
    }

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuario getControladorUsuario() {
        if (controladorUsuario == null) {
            controladorUsuario = new ControladorUsuario();
        }
        return controladorUsuario;
    }

    public IControladorEvento getControladorEvento() {
        if (controladorEvento == null) {
            controladorEvento = new ControladorEvento();
        }
        return controladorEvento;
    }
}


