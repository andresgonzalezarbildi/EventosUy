package logica.interfaces;

import java.time.LocalDate;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.DataUsuario;

public interface IControladorUsuario {
	public void altaUsuario(String nickname, String nombre, String correo, String tipo, String descripcion, String link, String apellido, LocalDate fechaNac) throws UsuarioRepetidoException ;
	DataUsuario verInfoUsuario(String ni) throws UsuarioNoExisteException;
    DataUsuario[] getUsuarios() throws UsuarioNoExisteException;
    DataUsuario[] getOrganizadores();
    public DataUsuario[] getAsistentes();
    public void modificarUsuario(String nickname, String nombre, String descripcion, String link, String apellido, LocalDate fechaNac) throws UsuarioNoExisteException ;
}
