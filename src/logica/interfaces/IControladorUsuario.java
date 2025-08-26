package logica.interfaces;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.datatypes.DataUsuario;

public interface IControladorUsuario {

    void altaUsuario(String nickname, String nombre, String correo, String tipo,
                     String descripcion, String link, String apellido, String fechaNac)
                     throws UsuarioRepetidoException;

    DataUsuario verInfoUsuario(String ni) throws UsuarioNoExisteException;

    DataUsuario[] getUsuarios() throws UsuarioNoExisteException;

    void modificarUsuario(String nickname, String nombre, String tipo,
                          String descripcion, String link, String apellido, String fechaNac)
                          throws UsuarioNoExisteException;
}
