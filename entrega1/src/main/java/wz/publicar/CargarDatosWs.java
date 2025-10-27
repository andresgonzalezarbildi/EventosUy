package wz.publicar;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.interfaces.ICargaDatos;

@WebService(serviceName="CargarDatosService", portName="CargarDatosPort")
public class CargarDatosWs {
  
  private Endpoint endpoint = null;
  //Constructor
  public CargarDatosWs(){}

  //Operaciones las cuales quiero publicar

  @WebMethod(exclude = true)
  public void publicar(){
       endpoint = Endpoint.publish("http://localhost:9128/Servicios/CargarDatosWS", this);
  }
  
  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
          return endpoint;
  }
  
  private final ICargaDatos ctrl =
      Fabrica.getInstance().getCargaDatos();

}
