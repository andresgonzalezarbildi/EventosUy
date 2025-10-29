package wz.publicar;

import java.util.List;

import excepciones.EventoNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import logica.Fabrica;
import logica.interfaces.IControladorEvento;
import wz.exceptions.EventoNoExisteFault;
import logica.datatypes.DataEvento;
import jakarta.xml.ws.Endpoint;

@WebService(serviceName="EventosService", portName="EventosPort")
public class EventosWs {
  
  private Endpoint endpoint = null;
  //Constructor
  public EventosWs(){}

  //Operaciones las cuales quiero publicar

  @WebMethod(exclude = true)
  public void publicar(){
       endpoint = Endpoint.publish("http://localhost:9128/Servicios/EventosWS", this);
  }
  
  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
          return endpoint;
  }
  
  private final IControladorEvento ctrl =
      Fabrica.getInstance().getControladorEvento();
  
  @WebMethod
  public DataEvento[] listarEventoExistentes() throws EventoNoExisteFault {
    try {
      return ctrl.listarEventoExistentes();
    } catch (EventoNoExisteException e) {
      throw new EventoNoExisteFault( e.getMessage() );
    }
  } 
  
  @WebMethod
  public List<String> listarCategorias() {
    return ctrl.listarCategorias();
  }
}
