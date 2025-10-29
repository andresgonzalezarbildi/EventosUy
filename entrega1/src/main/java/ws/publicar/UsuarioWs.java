package ws.publicar;

import java.time.LocalDate;

import excepciones.PasswordIncorrectaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorUsuario;
import ws.exceptions.PasswordIncorrectaFault;
import ws.exceptions.UsuarioNoExisteFault;
import ws.exceptions.UsuarioRepetidoFault;

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
  
  
  @WebMethod
  public DataUsuario[] getUsuarios() throws UsuarioNoExisteFault{
	  try {
	      return ctrl.getUsuarios();
	    } catch (UsuarioNoExisteException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }
  
  @WebMethod
  public DataUsuario[]  getOrganizadores() throws UsuarioNoExisteFault{
	  try {
	      return ctrl.getUsuarios();
	    } catch (UsuarioNoExisteException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }
  

  @WebMethod
  public DataUsuario[]  getAsistentes() throws UsuarioNoExisteFault{
	  try {
	      return ctrl.getUsuarios();
	    } catch (UsuarioNoExisteException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }

  @WebMethod
  public DataUsuario getOrganizador(String nickname) throws UsuarioNoExisteFault{
	  try {
	      return ctrl.getOrganizador(nickname);
	    } catch (UsuarioNoExisteException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }
  
  @WebMethod
  public DataUsuario getAsistente(String nickname) throws UsuarioNoExisteFault{
	  try {
	      return ctrl.getAsistente(nickname);
	    } catch (UsuarioNoExisteException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }
  
  @WebMethod
  public DataUsuario login(String ident, String password) throws UsuarioNoExisteFault, PasswordIncorrectaFault{
	  try {
	      return ctrl.login(ident,password);
	    } catch (UsuarioNoExisteException | PasswordIncorrectaException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }
  
  @WebMethod
  public void altaUsuario(String nickname, String nombre, String correo, String imagen,
	      String password, String tipo, String descripcion, String link, String apellido, 
	      LocalDate fechaNac) throws UsuarioRepetidoFault{
	  try {
	      ctrl.altaUsuario(nickname,nombre,correo,imagen,
	    	      password, tipo, descripcion, link, apellido, 
	    	      fechaNac);
	    } catch (UsuarioRepetidoException e) {
	      throw new UsuarioRepetidoFault( e.getMessage() );
	    }
  }
  
  
  @WebMethod
  public void modificarUsuario(String nickname, String nombre, String descripcion, String imagen,
	      String link, String apellido, LocalDate fechaNac) throws UsuarioNoExisteFault{
	  try {
	      ctrl.modificarUsuario(nickname, nombre, descripcion, imagen,
	    	      link,apellido,fechaNac);
	    } catch (UsuarioNoExisteException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }
  
  

  @WebMethod
  public  DataUsuario verInfoUsuario(String nickname) throws UsuarioNoExisteFault{
	  try {
	      return ctrl.verInfoUsuario(nickname);
	    } catch (UsuarioNoExisteException e) {
	      throw new UsuarioNoExisteFault( e.getMessage() );
	    }
  }
  
  public void cambiarContrasenia(String nickname, String nuevaPass) {
      ctrl.cambiarContrasenia(nickname,nuevaPass);
  	}
  }
