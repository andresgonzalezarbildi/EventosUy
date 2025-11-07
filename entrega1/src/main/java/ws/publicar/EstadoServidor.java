package ws.publicar;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

import java.time.LocalDateTime;

@WebService(serviceName = "EstadoService", portName = "EstadoPort")
public class EstadoServidor {

    private static final LocalDateTime fechaInicio = LocalDateTime.now();
    private Endpoint endpoint = null;

    @WebMethod(exclude = true)
    public void publicar(String url) {
      endpoint = Endpoint.publish(url, this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public String getFechaInicio() {
        return fechaInicio.toString();
    }

}
