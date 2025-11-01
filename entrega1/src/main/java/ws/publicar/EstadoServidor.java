package ws.publicar;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.time.LocalDateTime;

@WebService
public class EstadoServidor {

    // Fecha y hora exactas en que el servidor se levantó
    private static final LocalDateTime fechaInicio = LocalDateTime.now();

    @WebMethod
    public String getFechaInicio() {
        // Devuelve la misma fecha mientras el servidor siga corriendo
        return fechaInicio.toString();
    }

    @WebMethod
    public String ping() {
        // Método simple para probar conectividad (opcional)
        return "OK";
    }
}
