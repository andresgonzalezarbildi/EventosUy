package logica;

import logica.CargaDatos.CargaDatos;
import logica.controladores.ControladorEvento;
import logica.controladores.ControladorUsuario;
import logica.interfaces.ICargaDatos;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

public class Fabrica {

    private static Fabrica instancia = null;
    private IControladorUsuario controladorUsuario;
    private IControladorEvento controladorEvento;
    private ICargaDatos ICD;

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
    public ICargaDatos getCargaDatos() {
        if (ICD == null) {
            IControladorUsuario cu = getControladorUsuario();
            IControladorEvento ce = getControladorEvento();
            ICD = new CargaDatos(cu, ce);
        }
        return ICD;
    }
}




