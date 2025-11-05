package ws.publicar;

import java.time.LocalDate;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EdicionSinComenzarException;
import excepciones.EventoConEdicionesPedientesException;
import excepciones.EventoNoExisteException;
import excepciones.EventoRepetidoException;
import excepciones.TipoRegistroRepetidoException;
import excepciones.TransicionEstadoInvalidaException;
import excepciones.UsuarioNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.interfaces.IControladorEvento;
import ws.exceptions.CategoriaRepetidaFault;
import ws.exceptions.EdicionNoExisteFault;
import ws.exceptions.EventoConEdicionesPendientesFault;
import ws.exceptions.EventoNoExisteFault;
import ws.exceptions.EventoRepetidoFault;
import ws.exceptions.TipoRegistroRepetidoFault;
import ws.exceptions.TransicionEstadoInvalidaFault;
import ws.exceptions.UsuarioNoExisteFault;
import ws.exceptions.EdicionSinComenzarFault;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataTipoRegistro;

@WebService(serviceName = "EventosService", portName = "EventosPort")
public class EventosWs {

    private Endpoint endpoint = null;

    private final IControladorEvento ctrl = Fabrica.getInstance().getControladorEvento();

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:9128/Servicios/EventosWS", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }


    @WebMethod
    public void altaEvento(
        @WebParam(name = "nombre") String nombre,
        @WebParam(name = "descripcion") String descripcion,
        @WebParam(name = "sigla") String sigla,
        @WebParam(name = "nombresCategorias") List<String> nombresCategorias,
        @WebParam(name = "fechaAltaEnPlataforma") String fechaAltaStr,
        @WebParam(name = "imagen") String imagen
    ) throws EventoRepetidoFault {

        try {
            LocalDate fechaAlta = LocalDate.parse(fechaAltaStr);
            ctrl.altaEvento(nombre, descripcion, sigla, nombresCategorias, fechaAlta, imagen);
        } catch (EventoRepetidoException e) {
            throw new EventoRepetidoFault(e.getMessage());
        }
    }

    @WebMethod
    public void altaEdicionEvento(
        @WebParam(name = "nombreEvento") String nombreEvento,
        @WebParam(name = "nombreEdicion") String nombreEdicion,
        @WebParam(name = "sigla") String sigla,
        @WebParam(name = "ciudad") String ciudad,
        @WebParam(name = "pais") String pais,
        @WebParam(name = "fechaInicio") String fechaInicioStr,
        @WebParam(name = "fechaFin") String fechaFinStr,
        @WebParam(name = "fechaAltaEnPlataforma") String fechaAltaStr,
        @WebParam(name = "organizadorNick") String organizadorNick,
        @WebParam(name = "imagen") String imagen
    ) throws UsuarioNoExisteFault {

        try {
            LocalDate fInicio = LocalDate.parse(fechaInicioStr);
            LocalDate fFin = LocalDate.parse(fechaFinStr);
            LocalDate fAlta = LocalDate.parse(fechaAltaStr);
            ctrl.altaEdicionEvento(nombreEvento, nombreEdicion, sigla, ciudad, pais, fInicio, fFin, fAlta, organizadorNick, imagen);
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public void altaTipoRegistro(
        @WebParam(name = "nombreEvento") String nombreEvento,
        @WebParam(name = "nombreEdicion") String nombreEdicion,
        @WebParam(name = "nombreTipoRegistro") String nombreTipoRegistro,
        @WebParam(name = "descripcion") String descripcion,
        @WebParam(name = "costo") Integer costo,
        @WebParam(name = "cupos") Integer cupos
    ) throws TipoRegistroRepetidoFault {

        try {
            ctrl.altaTipoRegistro(nombreEvento, nombreEdicion, nombreTipoRegistro, descripcion, costo, cupos);
        } catch (TipoRegistroRepetidoException e) {
            throw new TipoRegistroRepetidoFault(e.getMessage());
        }
    }

    @WebMethod
    public void altaRegistro(
        @WebParam(name = "nombreEvento") String nombreEvento,
        @WebParam(name = "nombreEdicion") String nombreEdicion,
        @WebParam(name = "nombreTipoRegistro") String nombreTipoRegistro,
        @WebParam(name = "nombreAsistente") String nombreAsistente,
        @WebParam(name = "fecha") String fechaStr
    ) throws UsuarioNoExisteFault {

        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            ctrl.altaRegistro(nombreEvento, nombreEdicion, nombreTipoRegistro, nombreAsistente, fecha);
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public void altaCategoria(@WebParam(name = "nombre") String nombre)
        throws CategoriaRepetidaFault {

        try {
            ctrl.altaCategoria(nombre);
        } catch (CategoriaRepetidaException e) {
            throw new CategoriaRepetidaFault(e.getMessage());
        }
    }


    @WebMethod
    public DataEvento[] getEventosDTO() {
        return ctrl.getEventosDTO();
    }

    @WebMethod
    public DataEvento getUnEventoDTO(
        @WebParam(name = "nombre") String nombre
    ) throws EventoNoExisteFault {
        try {
            return ctrl.getUnEventoDTO(nombre);
        } catch (EventoNoExisteException e) {
            throw new EventoNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteFault {
        try {
            return ctrl.listarEventoExistentes();
        } catch (EventoNoExisteException e) {
            throw new EventoNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataTipoRegistro[] listarTiposRegistro(
        @WebParam(name = "nombreEvento") String nombreEvento,
        @WebParam(name = "nombreEdicion") String nombreEdicion
    ) {
        return ctrl.listarTiposRegistro(nombreEvento, nombreEdicion);
    }

    @WebMethod
    public DataTipoRegistro getTipoRegistro(
        @WebParam(name = "nombreEvento") String nombreEvento,
        @WebParam(name = "nombreEdicion") String nombreEdicion,
        @WebParam(name = "nombreTipo") String nombreTipo
    ) {
        return ctrl.getTipoRegistro(nombreEvento, nombreEdicion, nombreTipo);
    }

    @WebMethod
    public DataRegistro[] listarRegistrosDeEdicion(
        @WebParam(name = "nombreEdicion") String nombreEdicion
    ) {
        return ctrl.listarRegistrosDeEdicion(nombreEdicion);
    }

    @WebMethod
    public DataRegistro[] listarRegistrosDeUsuario(
        @WebParam(name = "nickname") String nickname
    ) {
        return ctrl.listarRegistrosDeUsuario(nickname);
    }

    @WebMethod
    public DataRegistro listarUnRegistroDeUsuario(
        @WebParam(name = "nombreEdicion") String nombreEdicion,
        @WebParam(name = "nickname") String nickname
    ) {
        return ctrl.listarUnRegistroDeUsuario(nombreEdicion, nickname);
    }

    @WebMethod
    public String getEventoDeUnaEdicion(
        @WebParam(name = "nombreEdicion") String nombreEdicion
    ) {
        return ctrl.getEventoDeUnaEdicion(nombreEdicion);
    }

    @WebMethod
    public DataEdicion getInfoEdicion(
        @WebParam(name = "idEdicion") String idEdicion
    ) throws EdicionNoExisteFault {
        try {
            return ctrl.getInfoEdicion(idEdicion);
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdiciones(
        @WebParam(name = "nombreEvento") String nombreEvento
    ) throws EventoNoExisteFault {
        try {
            return ctrl.listarEdiciones(nombreEvento);
        } catch (EventoNoExisteException e) {
            throw new EventoNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesOrganizador(
        @WebParam(name = "nombreOrganizador") String nombreOrganizador
    ) throws EdicionNoExisteFault {
        try {
            return ctrl.listarEdicionesOrganizador(nombreOrganizador);
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesAceptadas() throws EdicionNoExisteFault {
        try {
            return ctrl.listarEdicionesAceptadas();
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesAceptadasEvento(
        @WebParam(name = "nombreEvento") String nombreEvento
    ) throws EdicionNoExisteFault, EventoNoExisteFault {
        try {
            return ctrl.listarEdicionesAceptadasEvento(nombreEvento);
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        } catch (EventoNoExisteException e) {
            throw new EventoNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesOrganizadorAceptadas(
        @WebParam(name = "nombreOrganizador") String nombreOrganizador
    ) throws EdicionNoExisteFault {
        try {
            return ctrl.listarEdicionesOrganizadorAceptadas(nombreOrganizador);
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesIngresadas() throws EdicionNoExisteFault {
        try {
            return ctrl.listarEdicionesIngresadas();
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesIngresadasEvento(
        @WebParam(name = "nombreEvento") String nombreEvento
    ) throws EdicionNoExisteFault {
        try {
            return ctrl.listarEdicionesIngresadasEvento(nombreEvento);
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesRechazadas() throws EdicionNoExisteFault {
        try {
            return ctrl.listarEdicionesRechazadas();
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataEdicion[] listarEdicionesRechazadasEvento(
        @WebParam(name = "nombreEvento") String nombreEvento
    ) throws EdicionNoExisteFault {
        try {
            return ctrl.listarEdicionesRechazadasEvento(nombreEvento);
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        }
    }


    @WebMethod
    public void aceptarEdicion(
        @WebParam(name = "nombreEdicion") String nombreEdicion,
        @WebParam(name = "aceptada") Boolean aceptada
    ) throws EdicionNoExisteFault, TransicionEstadoInvalidaFault {
        try {
            ctrl.aceptarEdicion(nombreEdicion, aceptada);
        } catch (EdicionNoExisteException e) {
            throw new EdicionNoExisteFault(e.getMessage());
        } catch (TransicionEstadoInvalidaException e) {
            throw new TransicionEstadoInvalidaFault(e.getMessage());
        }
    }

    @WebMethod
    public void setCostoRegistro(
        @WebParam(name = "nickname") String nickname,
        @WebParam(name = "edicion") String edicion,
        @WebParam(name = "nombreTipo") String nombreTipo,
        @WebParam(name = "precio") int precio
    ) {
        ctrl.setCostoRegistro(nickname, edicion, nombreTipo, precio);
    }

    @WebMethod
    public List<String> listarCategorias() {
        return ctrl.listarCategorias();
    }
    
    public void finalizarEvento(String nombreEvento) throws EventoConEdicionesPendientesFault {
    	try {
        ctrl.finalizarEvento(nombreEvento);
      } catch (EventoConEdicionesPedientesException e) {
        throw new EventoConEdicionesPendientesFault(e.getMessage());
      }
    }
    
    public void confirmarAsistencia(String nombreEdicion, String nickname) throws EdicionSinComenzarFault {
    	try {
    		ctrl.confirmarAsistencia(nombreEdicion, nickname);
    	} catch(EdicionSinComenzarException e) {
    		throw new EdicionSinComenzarFault(e.getMessage());
    	}
    }
    
}
