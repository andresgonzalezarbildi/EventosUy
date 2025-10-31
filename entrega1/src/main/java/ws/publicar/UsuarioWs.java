package ws.publicar;

import java.time.LocalDate;

import excepciones.PasswordIncorrectaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorUsuario;
import ws.exceptions.PasswordIncorrectaFault;
import ws.exceptions.UsuarioNoExisteFault;
import ws.exceptions.UsuarioRepetidoFault;

@WebService(serviceName = "UsuarioService", portName = "UsuarioPort")
public class UsuarioWs {

    private Endpoint endpoint = null;

    public UsuarioWs() {}

    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:9128/Servicios/UsuariosWS", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    private final IControladorUsuario ctrl = Fabrica.getInstance().getControladorUsuario();

    @WebMethod
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteFault {
        try {
            return ctrl.getUsuarios();
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataUsuario[] getOrganizadores() throws UsuarioNoExisteFault {
        try {
            return ctrl.getUsuarios();
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataUsuario[] getAsistentes() throws UsuarioNoExisteFault {
        try {
            return ctrl.getUsuarios();
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataUsuario getOrganizador(
        @WebParam(name = "nickname") String nickname
    ) throws UsuarioNoExisteFault {
        try {
            return ctrl.getOrganizador(nickname);
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataUsuario getAsistente(
        @WebParam(name = "nickname") String nickname
    ) throws UsuarioNoExisteFault {
        try {
            return ctrl.getAsistente(nickname);
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataUsuario login(
        @WebParam(name = "ident") String ident,
        @WebParam(name = "password") String password
    ) throws UsuarioNoExisteFault, PasswordIncorrectaFault {
        try {
            return ctrl.login(ident, password);
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        } catch (PasswordIncorrectaException e) {
            throw new PasswordIncorrectaFault(e.getMessage());
        }
    }

    @WebMethod
    public void altaUsuario(
        @WebParam(name = "nickname") String nickname,
        @WebParam(name = "nombre") String nombre,
        @WebParam(name = "correo") String correo,
        @WebParam(name = "imagen") String imagen,
        @WebParam(name = "password") String password,
        @WebParam(name = "tipo") String tipo,
        @WebParam(name = "descripcion") String descripcion,
        @WebParam(name = "link") String link,
        @WebParam(name = "apellido") String apellido,
        @WebParam(name = "fechaNac") String fechaNac
    ) throws UsuarioRepetidoFault {
        try {
        	LocalDate fechaNacii = LocalDate.parse(fechaNac);
            ctrl.altaUsuario(nickname, nombre, correo, imagen, password, tipo, descripcion, link, apellido, fechaNacii);
        } catch (UsuarioRepetidoException e) {
            throw new UsuarioRepetidoFault(e.getMessage());
        }
    }

    @WebMethod
    public void modificarUsuario(
        @WebParam(name = "nickname") String nickname,
        @WebParam(name = "nombre") String nombre,
        @WebParam(name = "descripcion") String descripcion,
        @WebParam(name = "imagen") String imagen,
        @WebParam(name = "link") String link,
        @WebParam(name = "apellido") String apellido,
        @WebParam(name = "fechaNac") String fechaNac
    ) throws UsuarioNoExisteFault {
        try {
        	 LocalDate fechaNacii = LocalDate.parse(fechaNac);
            ctrl.modificarUsuario(nickname, nombre, descripcion, imagen, link, apellido, fechaNacii);
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public DataUsuario verInfoUsuario(
        @WebParam(name = "nickname") String nickname
    ) throws UsuarioNoExisteFault {
        try {
            return ctrl.verInfoUsuario(nickname);
        } catch (UsuarioNoExisteException e) {
            throw new UsuarioNoExisteFault(e.getMessage());
        }
    }

    @WebMethod
    public void cambiarContrasenia(
        @WebParam(name = "nickname") String nickname,
        @WebParam(name = "nuevaPass") String nuevaPass
    ) {
        ctrl.cambiarContrasenia(nickname, nuevaPass);
    }
    
    @WebMethod
    public DataUsuario loginMovil(
        @WebParam(name = "ident") String ident,
        @WebParam(name = "password") String password
    )throws UsuarioNoExisteFault, PasswordIncorrectaFault{
      try {
        return ctrl.login(ident, password);
      } catch (UsuarioNoExisteException e) {
        throw new UsuarioNoExisteFault(e.getMessage());
      }catch (PasswordIncorrectaException e) {
        throw new PasswordIncorrectaFault(e.getMessage());
      }
    }
}
