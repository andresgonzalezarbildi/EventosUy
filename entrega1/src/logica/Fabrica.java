package logica;

import logica.cargadatos.CargaDatos;
import logica.controladores.ControladorEvento;
import logica.controladores.ControladorUsuario;
import logica.interfaces.ICargaDatos;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

/**
 * Clase fábrica que provee instancias únicas de los controladores del sistema.
 * Implementa el patrón Singleton.
 */
public class Fabrica {
  private static Fabrica instancia = null;
  private IControladorUsuario controladorUsuario;
  private IControladorEvento controladorEvento;
  private ICargaDatos controladorCargaDatos;
  
  /**
   * Constructor privado de la fábrica.
   * Evita la creación de fabricas repetidas.
   */
  private Fabrica() {
      
  }
  
  /**
   * Devuelve la instancia única de la fábrica.
   *
   * @return instancia única de Fabrica
   */
  public static Fabrica getInstance() {
    if (instancia == null) {
      instancia = new Fabrica();
    }
    return instancia;
  }
  

  /**
   * Devuelve el controlador de usuarios.
   * Si no existe, lo crea.
   *
   * @return instancia del controlador de usuario
   */
  public IControladorUsuario getControladorUsuario() {
    if (controladorUsuario == null) {
      controladorUsuario = new ControladorUsuario();
    }
    return controladorUsuario;
  }
  
  /**
   * Devuelve el controlador de eventos.
   * Si no existe, lo crea.
   *
   * @return instancia del controlador de evento
   */
  public IControladorEvento getControladorEvento() {
    if (controladorEvento == null) {
      controladorEvento = new ControladorEvento();
    }
    return controladorEvento;
  }
  
  /**
   * Devuelve el cargador de datos.
   * Si no existe, lo crea con las instancias de los controladores.
   *
   * @return instancia de CargaDatos
   */
  public ICargaDatos getCargaDatos() {
    if (controladorCargaDatos == null) {
      IControladorUsuario instanciaControladorUsuario = getControladorUsuario();
      IControladorEvento instanciaControladorEvento = getControladorEvento();
      controladorCargaDatos = new CargaDatos(
          instanciaControladorUsuario, instanciaControladorEvento);
    }
    return controladorCargaDatos;
  }
}




