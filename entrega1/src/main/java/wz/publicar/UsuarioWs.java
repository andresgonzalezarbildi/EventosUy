package wz.publicar;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.interfaces.IControladorUsuario;

@WebService(serviceName="UsuarioService", portName="UsuarioWz")
public class UsuarioWs {
  private Endpoint endpoint = null;
  //Constructor
  public UsuarioWs(){}

  //Operaciones las cuales quiero publicar

  @WebMethod(exclude = true)
  public void publicar(){
       endpoint = Endpoint.publish("http://localhost:9128/Servicios/UsuariosWS", this);
  }
  
  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
          return endpoint;
  }
  
  private final IControladorUsuario ctrl =
      Fabrica.getInstance().getControladorUsuario();
}
