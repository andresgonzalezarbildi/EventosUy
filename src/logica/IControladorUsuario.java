package logica;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

/**
 * @author Grupo42
 *
 */
public interface IControladorUsuario {
    
    /**
     * Registra al usuario en el sistema.
     * @param ni Nickname del usuario.
     * @param no Nombre del usuario.
     * @param co Correo del usuario.
     * @param li Link del sitio del organizador.
     * @param desc Descripcion de organizador.
     * @param ap apellido del asistente.
     * @param fe fecha de nac del asistente.
     * @throws UsuarioRepetidoException Si la cédula del usuario se encuentra registrada en el sistema.
     */
    public abstract void altaUsuario(String ni, String no, String co) throws UsuarioRepetidoException;

    /**
     * Retorna la información de un usuario con el nickname indicada.
     * @param ni Nickname del usuario.
     * @return Información del usuario.
     * @throws UsuarioNoExisteException Si la cédula del usuario no está registrada en el sistema.
     */
    public abstract DataUsuario verInfoUsuario(String ni) throws UsuarioNoExisteException;

    /**
     * Retorna la información de todos los usuarios registrados en el sistema.
     * @return Información de los usuarios del sistema.
     * @throws UsuarioNoExisteException Si no existen usuarios registrados en el sistema.
     */
    public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;

    }
